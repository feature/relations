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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Golem;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Enderman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.impl.TamableContextIgnoringRelationResolver;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class TamableContextIgnoringRelationResolverTest {
    private TamableContextIgnoringRelationResolver resolver;
    private Player player;

    @Before
    public void setupResolverAndPlayer() {
        this.player = mock(Player.class);
        when(this.player.getUniqueID()).thenReturn(UUID.randomUUID());
        this.resolver = new TamableContextIgnoringRelationResolver(() -> this.player);
    }

    @After
    public void unsetResolverAndPlayer() {
        this.resolver = null;
        this.player = null;
    }

    @Test
    public void testFailedResolve() {
        Golem golem = mock(Golem.class);
        Enderman enderman = mock(Enderman.class);

        assertEquals(this.resolver.resolveRelation(golem, null), null);
        assertEquals(this.resolver.resolveRelation(enderman, null), null);
    }

    @Test
    public void testTamable() {
        Tamable tamedTamable = mock(Tamable.class);
        when(tamedTamable.isTamed()).thenReturn(true);

        assertEquals(this.resolver.resolveRelation(tamedTamable, null), Relation.PASSIVE);
    }

    @Test
    public void testUntamedWolf() {
        Wolf untamedWolf = mock(Wolf.class);
        Wolf angryUntamedWolf = mock(Wolf.class);
        when(angryUntamedWolf.isAngry()).thenReturn(true);

        assertEquals(this.resolver.resolveRelation(untamedWolf, null), Relation.PASSIVE);
        assertEquals(this.resolver.resolveRelation(angryUntamedWolf, null), Relation.HOSTILE);
    }

    @Test
    public void testTamedWolf() {
        Wolf tamedWolf = mock(Wolf.class);
        when(tamedWolf.isTamed()).thenReturn(true);

        assertEquals(this.resolver.resolveRelation(tamedWolf, null), Relation.NEUTRAL);
    }

    @Test
    public void testOwnedWolf() {
        Wolf ownedWolf = mock(Wolf.class);

        UUID playerId = this.player.getUniqueID();
        when(ownedWolf.isTamed()).thenReturn(true);
        when(ownedWolf.getOwner()).thenReturn(playerId);

        assertEquals(this.resolver.resolveRelation(ownedWolf, null), Relation.FRIENDLY);
    }
}