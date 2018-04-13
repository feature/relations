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
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.minecraftapi.entity.animal.Animal;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.item.Boat;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;
import pw.stamina.plugins.relations.result.ResolutionCallbackType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class TamableRelationResolverTest
        extends AbstractRelationResolverTest{
    @Mock
    private Player localPlayer;
    @Mock
    private Entity otherOwner;

    @Mock
    private Tamable tamedTamable;
    @Mock
    private Wolf untamedWolf;
    @Mock
    private Wolf angryUntamedWolf;

    @Mock
    private Wolf wolfTamedByLocalPlayer;
    @Mock
    private Wolf wolfTamedByOther;
    @Mock
    private Wolf wolfTamedByUnknown;

    @Before
    public void setupPlayerIdAndMockedTamables() {
        when(tamedTamable.isTamed()).thenReturn(true);
        when(angryUntamedWolf.isAngry()).thenReturn(true);

        setupWolfTamedByOther();
        setupWolfTamedByLocalPlayer();
        setupWolfTamedByUnknown();
    }

    private void setupWolfTamedByOther() {
        when(wolfTamedByOther.isTamed()).thenReturn(true);
        when(wolfTamedByOther.getOwner()).thenReturn(otherOwner);
    }

    private void setupWolfTamedByLocalPlayer() {
        when(wolfTamedByLocalPlayer.isTamed()).thenReturn(true);
        when(wolfTamedByLocalPlayer.getOwner()).thenReturn(localPlayer);
    }

    private void setupWolfTamedByUnknown() {
        when(wolfTamedByUnknown.isTamed()).thenReturn(true);
        when(wolfTamedByUnknown.getOwner()).thenReturn(null);
    }

    @Test
    public void testTamable() {
        testResolution(tamedTamable, Relation.PASSIVE);
    }

    @Test
    public void testUntamedWolf() {
        testResolution(untamedWolf, Relation.PASSIVE);
        testResolution(angryUntamedWolf, Relation.HOSTILE);
    }

    @Test
    public void testWolfOwnedByOther() {
        ResolutionCallback callback = resolver
                .resolveRelation(ResolveRequest
                        .anonymous(wolfTamedByOther, DUMMY_CONTEXT));

        assertSame(callback.getType(), ResolutionCallbackType.NESTED_RESOLVE);
        assertSame(callback.getNestedResolveTarget(), otherOwner);
    }

    @Test
    public void testWolfOwnedByUnknown() {
        testResolution(wolfTamedByUnknown, Relation.HOSTILE);
    }

    @Test
    public void testOwnedWolf() {
        testResolution(wolfTamedByLocalPlayer, Relation.FRIENDLY);
    }

    @Test(expected = ClassCastException.class)
    public void testFailedResolve() {
        Boat boat = mock(Boat.class);

        resolve(boat);
    }

    @Test
    public void canResolveTrueTest() {
        assertTrue(resolver.canResolve(Tamable.class));
        assertTrue(resolver.canResolve(Wolf.class));
    }

    @Test
    public void canResolveFalseTest() {
        assertFalse(resolver.canResolve(Animal.class));
        assertFalse(resolver.canResolve(Player.class));
        assertFalse(resolver.canResolve(Horse.class));
        assertFalse(resolver.canResolve(Monster.class));
        assertFalse(resolver.canResolve(ZombiePigman.class));
    }

    @Ignore
    @Override
    protected RelationResolver supplyResolver() {
        return new TamableRelationResolver(() -> localPlayer);
    }
}