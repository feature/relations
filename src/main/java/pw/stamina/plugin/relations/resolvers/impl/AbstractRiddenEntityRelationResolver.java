package pw.stamina.plugin.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import javax.inject.Provider;

import static pw.stamina.plugin.relations.result.ResolutionCallback.nestedResolve;
import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

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
            return success(Relation.IGNORED);
        } else {
            return nestedResolve(rider);
        }
    }

    private boolean isRiderLocalPlayer(Entity rider) {
        Player localPlayer = localPlayerProvider.get();

        return rider == localPlayer;
    }

    protected abstract ResolutionCallback resolveUnriddenEntity(ResolveRequest request);
}
