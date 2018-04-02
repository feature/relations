package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO: Javadoc
public final class HorseRelationResolver extends AbstractRiddenEntityRelationResolver {

    @Inject
    public HorseRelationResolver(Provider<Player> localPlayerProvider) {
        super(localPlayerProvider);
    }

    @Override
    protected ResolutionCallback resolveUnriddenEntity(ResolveRequest request) {
        return ResolutionCallback.success(Relation.PASSIVE);
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Horse.class.isAssignableFrom(entityType);
    }
}
