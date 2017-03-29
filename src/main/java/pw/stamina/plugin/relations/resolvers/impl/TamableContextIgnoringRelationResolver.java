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
import pw.stamina.plugin.relations.resolvers.ContextIgnoringRelationResolver;

import java.util.UUID;

public final class TamableContextIgnoringRelationResolver
        extends ContextIgnoringRelationResolver {
    private final Provider<Player> playerProvider;

    @Inject
    public TamableContextIgnoringRelationResolver(Provider<Player> playerProvider) {
        this.playerProvider = playerProvider;
    }

    @Override
    protected Relation resolveRelation(Entity entity) {
        Tamable tamable = (Tamable) entity;

        if (this.doesPlayerOwnTamable(tamable)) {
            return Relation.FRIENDLY;
        }

        if (tamable instanceof Wolf) {
            Wolf wolf = (Wolf) tamable;
            return this.resolveWolfRelation(wolf);
        } else {
            return Relation.PASSIVE;
        }
    }

    private Relation resolveWolfRelation(Wolf wolf) {
        if (wolf.isAngry()) {
            return Relation.HOSTILE;
        } else if (wolf.isTamed()) {
            return Relation.NEUTRAL;
        } else {
            return Relation.PASSIVE;
        }
    }

    private boolean doesPlayerOwnTamable(Tamable tamable) {
        Player player = this.playerProvider.get();
        if (player == null) {
            return false;
        }

        if (!tamable.isTamed()) {
            return false;
        }

        UUID owner = tamable.getOwner();
        return player.getUniqueID().equals(owner);
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Tamable.class.isAssignableFrom(entityType);
    }
}
