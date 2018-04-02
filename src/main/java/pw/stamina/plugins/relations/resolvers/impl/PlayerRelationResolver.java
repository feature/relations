package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO: Javadoc
public final class PlayerRelationResolver extends AbstractRelationResolver {

    private final Provider<Player> localPlayerProvider;

    @Inject
    PlayerRelationResolver(Provider<Player> localPlayerProvider) {
        this.localPlayerProvider = localPlayerProvider;
    }

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Player player = (Player) request.entity();

        if (isLocalPlayer(player)) {
            return ResolutionCallback.success(Relation.IGNORED);
        } else {
            return ResolutionCallback.success(Relation.PLAYER);
        }
    }

    private boolean isLocalPlayer(Player player) {
        Player localPlayer = localPlayerProvider.get();

        return player == localPlayer;
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Player.class.isAssignableFrom(entityType);
    }
}
