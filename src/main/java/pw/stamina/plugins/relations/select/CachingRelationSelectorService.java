package pw.stamina.plugins.relations.select;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.request.CompleteResolveRequest;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//TODO: Javadoc
public final class CachingRelationSelectorService
        extends AbstractRelationSelectorService {

    private final Map<Class<? extends Entity>, List<RelationResolver>> cachedResolvers;

    @Inject
    CachingRelationSelectorService() {
        this.cachedResolvers = new ConcurrentHashMap<>();
    }

    @Override
    public Relation select(CompleteResolveRequest completeRequest) {
        return new SelectionProcess(completeRequest).select();
    }

    /**
     * Delegates the <tt>resolver</tt> to the {@link
     * #invalidateCache(RelationResolver)} method.
     *
     * @param resolver the resolver changed
     */
    @Override
    public void notifyResolverChange(RelationResolver resolver) {
        invalidateCache(resolver);
    }

    /**
     * Removes the cached entries by key if the key
     * is accepted by the {@link RelationResolver#canResolve(Class)}
     * method of the specified <tt>resolver</tt>.
     *
     * @param resolver resolver invalidating the cache for
     */
    private void invalidateCache(RelationResolver resolver) {
        cachedResolvers.keySet()
                .removeIf(resolver::canResolve);
    }

    private List<RelationResolver> findCachedResolvers(Entity entity,
                                                       List<RelationResolver> resolvers) {
        Class<? extends Entity> entityType = entity.getClass();

        return cachedResolvers
                .computeIfAbsent(entityType, key -> resolvers.stream()
                        .filter(resolver -> resolver.canResolve(key))
                        .collect(Collectors.toList()));
    }

    private final class SelectionProcess {
        private final ResolveRequest request;
        private final List<RelationResolver> resolvers;
        private final List<ResolvedRelationProcessor> processors;

        private SelectionProcess(CompleteResolveRequest completeRequest) {
            this.request = completeRequest.request();
            this.resolvers = completeRequest.resolvers();
            this.processors = completeRequest.processors();
        }

        private Relation select() {
            return select(request.entity());
        }

        private Relation select(Entity entity) {
            List<RelationResolver> cachedResolvers =
                    findCachedResolvers(entity, resolvers);

            for (RelationResolver resolver : cachedResolvers) {
                ResolutionCallback callback = resolver
                        .resolveRelation(request);

                switch (callback.getType()) {
                    case SUCCESSFUL:
                        return successful(callback.getResult());
                    case NESTED_RESOLVE:
                        return select(callback.getNestedResolveTarget());
                    case FAILED:
                        break;
                }
            }

            return Relation.UNRECOGNIZED;
        }

        private Relation successful(Relation result) {
            return processRelation(processors, request, result);
        }
    }
}
