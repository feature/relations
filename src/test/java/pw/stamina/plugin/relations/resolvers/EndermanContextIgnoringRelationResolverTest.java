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

import org.junit.Test;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Enderman;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.impl.EndermanContextIgnoringRelationResolver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class EndermanContextIgnoringRelationResolverTest
        extends AbstractRelationResolverTest{

    @Test
    public void resolveRelationNeutralTest() {
        Enderman enderman = mock(Enderman.class);

        assertEquals(this.resolver.resolveRelation(enderman, null),
                Relation.NEUTRAL);
    }

    @Test
    public void resolveRelationHostileTest() {
        Enderman screamingEnderman = mock(Enderman.class);
        when(screamingEnderman.isScreaming()).thenReturn(true);

        assertEquals(this.resolver.resolveRelation(screamingEnderman, null),
                Relation.HOSTILE);
    }

    @Test(expected = ClassCastException.class)
    public void resolveRelationFailTest() {
        Wolf wolf = mock(Wolf.class);

        this.resolver.resolveRelation(wolf, null);
    }

    @Test
    public void caResolveTrueTest() {
        assertTrue(this.resolver.canResolve(Enderman.class));
    }

    @Test
    public void caResolveFalseTest() {
        assertFalse(this.resolver.canResolve(Tamable.class));
        assertFalse(this.resolver.canResolve(Wolf.class));
        assertFalse(this.resolver.canResolve(Horse.class));
        assertFalse(this.resolver.canResolve(Player.class));
        assertFalse(this.resolver.canResolve(Monster.class));
        assertFalse(this.resolver.canResolve(ZombiePigman.class));
    }

    @Override
    protected RelationResolver supplyResolver() {
        return new EndermanContextIgnoringRelationResolver();
    }
}
