package pw.stamina.plugins.relations.resolvers.impl.wildcard;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.result.ResolutionCallback;

//TODO: Javadoc
public final class MonsterWildcardRelationResolver extends WildcardRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        return ResolutionCallback.success(Relation.HOSTILE);
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Monster.class.isAssignableFrom(entityType);
    }
}
