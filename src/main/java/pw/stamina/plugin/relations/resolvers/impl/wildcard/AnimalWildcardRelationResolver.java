package pw.stamina.plugin.relations.resolvers.impl.wildcard;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Animal;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

//TODO: Javadoc
final class AnimalWildcardRelationResolver
        extends WildcardRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        return success(Relation.PASSIVE);
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Animal.class.isAssignableFrom(entityType)
                && !Monster.class.isAssignableFrom(entityType);
    }
}
