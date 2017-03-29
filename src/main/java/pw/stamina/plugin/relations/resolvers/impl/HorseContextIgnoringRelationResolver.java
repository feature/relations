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
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.ContextIgnoringRelationResolver;

public final class HorseContextIgnoringRelationResolver
        extends ContextIgnoringRelationResolver {
    private final Provider<Player> playerProvider;

    @Inject
    public HorseContextIgnoringRelationResolver(Provider<Player> playerProvider) {
        this.playerProvider = playerProvider;
    }

    @Override
    protected Relation resolveRelation(Entity entity) {
        Entity rider = entity.getRider();

        if (rider == null) {
            return Relation.PASSIVE;
        }

        Player player = this.playerProvider.get();
        if (rider == player) {
            return Relation.IGNORED;
        } else {
            return Relation.NEUTRAL;
        }
    }

    @Override
    public boolean canResolve(Class<? extends Entity> entityType) {
        return Horse.class.isAssignableFrom(entityType);
    }
}
