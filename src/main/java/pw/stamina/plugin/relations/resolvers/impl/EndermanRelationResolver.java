package pw.stamina.plugin.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.monster.Enderman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

//TODO: Javadoc
final class EndermanRelationResolver extends AbstractRelationResolver {

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Enderman enderman = (Enderman) request.entity();

        if (enderman.isScreaming()) {
            return success(Relation.HOSTILE);
        } else {
            return success(Relation.NEUTRAL);
        }
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Enderman.class.isAssignableFrom(entityType);
    }
}
