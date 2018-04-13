package pw.stamina.plugins.relations.resolvers.impl.wildcard;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Animal;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.result.ResolutionCallback;

//TODO: Javadoc
public final class AnimalWildcardRelationResolver extends WildcardRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        return ResolutionCallback.success(Relation.PASSIVE);
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Animal.class.isAssignableFrom(entityType)
                && !Monster.class.isAssignableFrom(entityType);
    }
}
