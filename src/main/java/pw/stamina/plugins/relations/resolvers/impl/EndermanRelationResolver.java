package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.monster.Enderman;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Inject;

//TODO: Javadoc
public final class EndermanRelationResolver extends AbstractRelationResolver {

    @Inject
    EndermanRelationResolver() {}

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Enderman enderman = (Enderman) request.entity();

        if (enderman.isScreaming()) {
            return ResolutionCallback.success(Relation.HOSTILE);
        } else {
            return ResolutionCallback.success(Relation.NEUTRAL);
        }
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Enderman.class.isAssignableFrom(entityType);
    }
}
