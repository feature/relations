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

package pw.stamina.plugins.relations.resolvers.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.stamina.minecraftapi.entity.animal.Animal;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.item.Boat;
import pw.stamina.minecraftapi.entity.item.Minecart;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class VehicleRelationResolverTest
        extends AbstractRelationResolverTest {
    @Mock
    private Player player;

    @Mock
    private Boat boatRiddenByPlayer;
    @Mock
    private Minecart minecartRiddenByPlayer;

    @Before
    public void setupPlayerRiddenVehicles() {
        when(boatRiddenByPlayer.getRider()).thenReturn(player);
        when(minecartRiddenByPlayer.getRider()).thenReturn(player);
    }

    @Test
    public void canResolveTrueTest() {
        assertTrue(resolver.canResolve(Boat.class));
        assertTrue(resolver.canResolve(Minecart.class));
    }

    @Test
    public void resolveRelationRiddenByPlayer() {
        testResolution(boatRiddenByPlayer, Relation.IGNORED);
        testResolution(minecartRiddenByPlayer, Relation.IGNORED);
    }

    @Test
    public void resolveRelationNotRidden() {
        Boat boat = mock(Boat.class);
        Minecart minecart = mock(Minecart.class);

        assertSame(resolve(boat), ResolutionCallback.failed());
        assertSame(resolve(minecart), ResolutionCallback.failed());
    }

    @Test
    public void canResolveFalseTest() {
        assertFalse(resolver.canResolve(Animal.class));
        assertFalse(resolver.canResolve(Horse.class));
        assertFalse(resolver.canResolve(Player.class));
        assertFalse(resolver.canResolve(Monster.class));
        assertFalse(resolver.canResolve(ZombiePigman.class));
        assertFalse(resolver.canResolve(Tamable.class));
        assertFalse(resolver.canResolve(Wolf.class));
    }

    @Ignore
    @Override
    protected RelationResolver supplyResolver() {
        return new VehicleRelationResolver(() -> player);
    }
}
