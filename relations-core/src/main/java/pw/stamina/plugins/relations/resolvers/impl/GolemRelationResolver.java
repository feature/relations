package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.living.Golem;
import pw.stamina.minecraftapi.entity.living.IronGolem;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

//TODO: Javadoc
public final class GolemRelationResolver extends AbstractRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Golem golem = (Golem) request.entity();

        if (golem instanceof IronGolem) {
            return resolveIronGolemRelation((IronGolem) golem);
        }

        return ResolutionCallback.success(Relation.PASSIVE);
    }

    private ResolutionCallback resolveIronGolemRelation(IronGolem ironGolem) {
        if (isIronGolemPassive(ironGolem)) {
            return ResolutionCallback.success(Relation.PASSIVE);
        } else {
            return ResolutionCallback.success(Relation.NEUTRAL);
        }
    }

    private boolean isIronGolemPassive(IronGolem ironGolem) {
        return ironGolem.isPlayerCreated();
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Golem.class.isAssignableFrom(entityType);
    }
}
