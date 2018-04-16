package pw.stamina.plugins.relations;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.select.RelationSelectorService;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

//TODO: Javadoc
public final class StandardRelationManager implements RelationManager {

    private final CopyOnWriteArrayList<RelationResolver> resolvers;
    private final CopyOnWriteArrayList<ResolvedRelationProcessor> processors;
    private final RelationSelectorService selectorService;

    /**
     * Constructs a new <tt>StandardRelationManager</tt>
     * with the specified default resolvers, and the
     * specified <tt>selectorService</tt>.
     *
     * @param defaultResolvers set of default resolvers
     * @param selectorService  relation selector service
     */
    @Inject
    StandardRelationManager(
            Set<RelationResolver> defaultResolvers,
            RelationSelectorService selectorService) {
        Objects.requireNonNull(selectorService, "selectorService");

        this.resolvers = new CopyOnWriteArrayList<>();
        this.processors = new CopyOnWriteArrayList<>();
        this.selectorService = selectorService;

        if (isDefaultResolverNotNullOrEmpty(defaultResolvers)) {
            this.resolvers.addAll(
                    defaultResolvers.stream()
                            .sorted()
                            .collect(Collectors.toList()));
        }
    }

    private boolean isDefaultResolverNotNullOrEmpty(
            Set<RelationResolver> defaultResolvers) {
        return (defaultResolvers != null)
                && !defaultResolvers.isEmpty();
    }

    @Override
    public Relation findAttackRelation(Entity entity) {
        return findRelation(entity, ResolutionContext.ATTACK);
    }

    @Override
    public Relation findRenderRelation(Entity entity) {
        return findRelation(entity, ResolutionContext.RENDER);
    }

    private Relation findRelation(Entity entity,
                                  ResolutionContext context) {
        return findRelation(ResolveRequest.anonymous(entity, context));
    }

    @Override
    public Relation findRelation(ResolveRequest request) {
        return selectorService.select(
                request.complete(
                        findResolvers(),
                        findProcessors()));
    }

    @Override
    public boolean registerResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        //TODO: Find insertion index instead of sorting
        if (resolvers.addIfAbsent(resolver)) {
            Collections.sort(resolvers);
            selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unregisterResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        if (resolvers.remove(resolver)) {
            selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<RelationResolver> findResolvers() {
        return Collections.unmodifiableList(resolvers);
    }

    @Override
    public boolean registerProcessor(ResolvedRelationProcessor processor) {
        return processors.addIfAbsent(processor);
    }

    @Override
    public boolean unregisterProcessor(ResolvedRelationProcessor processor) {
        return processors.remove(processor);
    }

    @Override
    public List<ResolvedRelationProcessor> findProcessors() {
        return Collections.unmodifiableList(processors);
    }
}
