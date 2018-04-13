package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

//TODO: Javadoc
public final class ZombiePigmanRelationResolver extends AbstractRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        ZombiePigman pigman = (ZombiePigman) request.entity();

        if (pigman.isAngry()) {
            return ResolutionCallback.success(Relation.HOSTILE);
        } else {
            return ResolutionCallback.success(Relation.NEUTRAL);
        }
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return ZombiePigman.class.isAssignableFrom(entityType);
    }
}
