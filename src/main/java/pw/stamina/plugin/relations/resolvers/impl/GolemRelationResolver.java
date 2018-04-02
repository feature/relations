package pw.stamina.plugin.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.living.Golem;
import pw.stamina.minecraftapi.entity.living.IronGolem;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

//TODO: Javadoc
final class GolemRelationResolver
        extends AbstractRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Golem golem = (Golem) request.entity();

        if (golem instanceof IronGolem) {
            IronGolem ironGolem = (IronGolem) golem;

            if (isIronGolemPassive(ironGolem)) {
                return success(Relation.PASSIVE);
            } else {
                return success(Relation.NEUTRAL);
            }
        }

        return success(Relation.PASSIVE);
    }

    private boolean isIronGolemPassive(IronGolem ironGolem) {
        return ironGolem.isPlayerCreated();
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Golem.class.isAssignableFrom(entityType);
    }
}
