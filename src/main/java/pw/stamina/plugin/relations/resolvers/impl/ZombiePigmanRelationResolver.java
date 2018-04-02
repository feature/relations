package pw.stamina.plugin.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

//TODO: Javadoc
final class ZombiePigmanRelationResolver
        extends AbstractRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        ZombiePigman pigman = (ZombiePigman) request.entity();

        if (isZombiePigmanHostile(pigman)) {
            return success(Relation.HOSTILE);
        } else {
            return success(Relation.NEUTRAL);
        }
    }

    private boolean isZombiePigmanHostile(ZombiePigman pigman) {
        return pigman.isAngry();
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return ZombiePigman.class.isAssignableFrom(entityType);
    }
}
