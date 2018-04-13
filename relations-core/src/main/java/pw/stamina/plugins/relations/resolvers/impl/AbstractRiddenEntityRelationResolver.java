package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Provider;

abstract class AbstractRiddenEntityRelationResolver extends AbstractRelationResolver {

    private final Provider<Player> localPlayerProvider;

    AbstractRiddenEntityRelationResolver(Provider<Player> localPlayerProvider) {
        this.localPlayerProvider = localPlayerProvider;
    }

    @Override
    public final ResolutionCallback resolveRelation(ResolveRequest request) {
        Entity rider = request.entity().getRider();

        if (rider == null) {
            return resolveUnriddenEntity(request);
        }

        if (isRiderLocalPlayer(rider)) {
            return ResolutionCallback.success(Relation.IGNORED);
        } else {
            return ResolutionCallback.nestedResolve(rider);
        }
    }

    private boolean isRiderLocalPlayer(Entity rider) {
        Player localPlayer = localPlayerProvider.get();

        return rider == localPlayer;
    }

    protected abstract ResolutionCallback resolveUnriddenEntity(ResolveRequest request);
}
