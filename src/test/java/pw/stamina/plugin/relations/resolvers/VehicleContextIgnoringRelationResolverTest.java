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
import pw.stamina.minecraftapi.entity.animal.Animal;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.item.Boat;
import pw.stamina.minecraftapi.entity.item.Minecart;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.impl.VehicleContextIgnoringRelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class VehicleContextIgnoringRelationResolverTest
        extends AbstractRelationResolverTest {
    private Player player;

    @Before
    public void setupAndPlayer() {
        this.player = mock(Player.class);
        when(this.player.getUniqueID()).thenReturn(UUID.randomUUID());
    }

    @Test
    public void canResolveTrueTest() {
        assertTrue(this.resolver.canResolve(Boat.class));
        assertTrue(this.resolver.canResolve(Minecart.class));
    }

    @Test
    public void resolveRelationRiddenByPlayer() {
        Boat boatRiddenByPlayer = mock(Boat.class);
        Minecart minecartRiddenByPlayer = mock(Minecart.class);

        when(boatRiddenByPlayer.getRider()).thenReturn(this.player);
        when(minecartRiddenByPlayer.getRider()).thenReturn(this.player);

        this.testResolution(boatRiddenByPlayer, Relation.IGNORED);
        this.testResolution(minecartRiddenByPlayer, Relation.IGNORED);
    }

    @Test
    public void resolveRelationNotRiddenByPlayer() {
        Boat boat = mock(Boat.class);
        Minecart minecart = mock(Minecart.class);

        assertEquals(this.resolver.resolveRelation(boat, null), ResolutionCallback.failed());
        assertEquals(this.resolver.resolveRelation(minecart, null), ResolutionCallback.failed());
    }

    @Test
    public void canResolveFalseTest() {
        assertFalse(this.resolver.canResolve(Animal.class));
        assertFalse(this.resolver.canResolve(Horse.class));
        assertFalse(this.resolver.canResolve(Player.class));
        assertFalse(this.resolver.canResolve(Monster.class));
        assertFalse(this.resolver.canResolve(ZombiePigman.class));
        assertFalse(this.resolver.canResolve(Tamable.class));
        assertFalse(this.resolver.canResolve(Wolf.class));
    }

    @Ignore
    @Override
    protected RelationResolver supplyResolver() {
        return new VehicleContextIgnoringRelationResolver(() -> this.player);
    }
}
