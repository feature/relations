package pw.stamina.plugins.relations.resolvers.impl;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.item.Boat;
import pw.stamina.minecraftapi.entity.item.Minecart;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO: Javadoc
public final class VehicleRelationResolver extends AbstractRiddenEntityRelationResolver {

    @Inject
    VehicleRelationResolver(Provider<Player> localPlayerProvider) {
        super(localPlayerProvider);
    }

    @Override
    protected ResolutionCallback resolveUnriddenEntity(ResolveRequest request) {
        return ResolutionCallback.failed();
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return isEntityVehicle(entityType);
    }

    private boolean isEntityVehicle(Class<? extends Entity> entityType) {
        return Boat.class.isAssignableFrom(entityType)
                || Minecart.class.isAssignableFrom(entityType);
    }
}
