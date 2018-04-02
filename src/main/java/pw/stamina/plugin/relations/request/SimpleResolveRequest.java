package pw.stamina.plugin.relations.request;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.ResolutionContext;
import pw.stamina.plugin.relations.ResolvedRelationProcessor;
import pw.stamina.plugin.relations.resolvers.RelationResolver;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//TODO: Javadoc
final class SimpleResolveRequest implements ResolveRequest {
    private final Entity entity;
    private final ResolutionContext context;
    private final String requester;

    SimpleResolveRequest(Entity entity,
                         ResolutionContext context,
                         String requester) {
        this.entity = entity;
        this.context = context;
        this.requester = requester;
    }

    @Override
    public Entity entity() {
        return entity;
    }

    @Override
    public ResolutionContext context() {
        return context;
    }

    @Override
    public Optional<String> requester() {
        return Optional.ofNullable(requester);
    }

    @Override
    public CompleteResolveRequest complete(
            List<RelationResolver> resolvers,
            List<ResolvedRelationProcessor> processors) {
        Objects.requireNonNull(resolvers, "resolvers");
        Objects.requireNonNull(processors, "processors");

        return new SimpleCompleteResolveRequest(
                this,
                resolvers,
                processors);
    }
}
