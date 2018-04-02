package pw.stamina.plugins.relations.request;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.ResolutionContext;
import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//TODO: Javadoc
public interface ResolveRequest {

    Entity entity();

    ResolutionContext context();

    Optional<String> requester();

    CompleteResolveRequest complete(List<RelationResolver> resolvers,
                                    List<ResolvedRelationProcessor> processors);

    static ResolveRequest anonymous(Entity entity, ResolutionContext context) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(context, "context");

        return new SimpleResolveRequest(entity, context, null);
    }

    static ResolveRequest identified(Entity entity,
                                     ResolutionContext context,
                                     String requester) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(context, "context");
        Objects.requireNonNull(requester, "requester");

        return new SimpleResolveRequest(entity, context, requester);
    }
}
