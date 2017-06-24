/*
 * Copyright Abstraction 2017
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package pw.stamina.plugin.relations.resolvers.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import static pw.stamina.plugin.relations.result.ResolutionCallback.nestedResolve;
import static pw.stamina.plugin.relations.result.ResolutionCallback.success;

//TODO: Javadoc
final class TamableRelationResolver
        extends AbstractRelationResolver {
    private final Provider<Player> localPlayerProvider;

    @Inject
    public TamableRelationResolver(Provider<Player> localPlayerProvider) {
        this.localPlayerProvider = localPlayerProvider;
    }

    @Override
    public ResolutionCallback resolveRelation(ResolveRequest request) {
        Tamable tamable = (Tamable) request.entity();

        if (doesLocalPlayerOwnTamable(tamable)) {
            return success(Relation.FRIENDLY);
        }

        if (tamable instanceof Wolf) {
            Wolf wolf = (Wolf) tamable;
            return resolveWolfRelation(wolf);
        } else {
            return success(Relation.PASSIVE);
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
            return success(Relation.HOSTILE);
        } else if (wolf.isTamed()) {
            return resolveTamedWolfRelation(wolf);
        } else {
            return success(Relation.PASSIVE);
        }
    }

    private ResolutionCallback resolveTamedWolfRelation(Wolf wolf) {
        Entity owner = wolf.getOwner();

        if (owner != null) {
            return nestedResolve(owner);
        } else {
            //TODO: Integrate in documentation
            //Tamed wolfes are never angry, therefore their
            // hostility state cannot be determined, and should
            // be treated as hostile.

            return success(Relation.HOSTILE);
        }
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Tamable.class.isAssignableFrom(entityType);
    }
}
