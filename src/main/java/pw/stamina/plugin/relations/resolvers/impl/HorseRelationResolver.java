package pw.stamina.plugin.relations.resolvers.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

//TODO: Javadoc
final class HorseRelationResolver
        extends AbstractRiddenEntityRelationResolver {

    @Inject
    public HorseRelationResolver(Provider<Player> localPlayerProvider) {
        super(localPlayerProvider);
    }

    @Override
    protected ResolutionCallback resolveUnriddenEntity(ResolveRequest request) {
        return success(Relation.PASSIVE);
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Horse.class.isAssignableFrom(entityType);
    }
}
