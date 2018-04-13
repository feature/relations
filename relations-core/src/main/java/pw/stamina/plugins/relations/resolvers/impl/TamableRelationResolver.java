package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO: Javadoc
public final class TamableRelationResolver extends AbstractRelationResolver {

    private final Provider<Player> localPlayerProvider;

    @Inject
    public TamableRelationResolver(Provider<Player> localPlayerProvider) {
        this.localPlayerProvider = localPlayerProvider;
    }

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Tamable tamable = (Tamable) request.entity();

        if (doesLocalPlayerOwnTamable(tamable)) {
            return ResolutionCallback.success(Relation.FRIENDLY);
        }

        if (tamable instanceof Wolf) {
            Wolf wolf = (Wolf) tamable;
            return resolveWolfRelation(wolf);
        } else {
            return ResolutionCallback.success(Relation.PASSIVE);
        }
    }

    private boolean doesLocalPlayerOwnTamable(Tamable tamable) {
        if (!tamable.isTamed()) {
            return false;
        }

        Player localPlayer = localPlayerProvider.get();
        Entity owner = tamable.getOwner();

        return owner == localPlayer;
    }

    private ResolutionCallback resolveWolfRelation(Wolf wolf) {
        if (wolf.isAngry()) {
            return ResolutionCallback.success(Relation.HOSTILE);
        } else if (wolf.isTamed()) {
            return resolveTamedWolfRelation(wolf);
        } else {
            return ResolutionCallback.success(Relation.PASSIVE);
        }
    }

    private ResolutionCallback resolveTamedWolfRelation(Wolf wolf) {
        Entity owner = wolf.getOwner();

        if (owner != null) {
            return ResolutionCallback.nestedResolve(owner);
        } else {
            //TODO: Include in documentation
            //Tamed wolfes are never angry, therefore their
            // hostility state cannot be determined, and should
            // be treated as hostile.

            return ResolutionCallback.success(Relation.HOSTILE);
        }
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Tamable.class.isAssignableFrom(entityType);
    }
}
