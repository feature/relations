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

package pw.stamina.plugin.relations.resolvers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Animal;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.impl.HorseContextIgnoringRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;
import pw.stamina.plugin.relations.result.ResolutionCallbackType;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class HorseContextIgnoringRelationResolverTest
        extends AbstractRelationResolverTest{
    private Player player;

    @Before
    public void setupPlayer() {
        this.player = mock(Player.class);
        when(this.player.getUniqueID()).thenReturn(UUID.randomUUID());
    }

    @Test
    public void horseWithoutRiderTest() {
        Horse horse = mock(Horse.class);

        this.testResolution(horse, Relation.PASSIVE);
    }

    @Test
    public void horseRiddenByPlayerTest() {
        Horse horseRiddenByPlayer = mock(Horse.class);
        when(horseRiddenByPlayer.getRider()).thenReturn(this.player);

        this.testResolution(horseRiddenByPlayer, Relation.IGNORED);
    }

    @Test
    public void horseRiddenByOther() {
        Horse horseRiddenByOther = mock(Horse.class);
        Entity rider = mock(Entity.class);
        when(horseRiddenByOther.getRider()).thenReturn(rider);

        ResolutionCallback callback = this.resolver
                .resolveRelation(horseRiddenByOther, null);

        assertEquals(callback.getType(), ResolutionCallbackType.NESTED_RESOLVE);
        assertEquals(callback.getNestedResolveTarget(), rider);
    }

    @Test
    public void canResolveTrueTest() {
        assertTrue(this.resolver.canResolve(Horse.class));
    }

    @Test
    public void canResolveFalseTest() {
        assertFalse(this.resolver.canResolve(Animal.class));
        assertFalse(this.resolver.canResolve(Player.class));
        assertFalse(this.resolver.canResolve(Monster.class));
        assertFalse(this.resolver.canResolve(ZombiePigman.class));
        assertFalse(this.resolver.canResolve(Tamable.class));
        assertFalse(this.resolver.canResolve(Wolf.class));
    }

    @Ignore
    @Override
    protected RelationResolver supplyResolver() {
        return new HorseContextIgnoringRelationResolver(() -> this.player);
    }
}
