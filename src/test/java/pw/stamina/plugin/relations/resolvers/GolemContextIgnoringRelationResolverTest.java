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

import org.junit.Ignore;
import org.junit.Test;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Golem;
import pw.stamina.minecraftapi.entity.living.IronGolem;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.impl.GolemContextIgnoringRelationResolver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class GolemContextIgnoringRelationResolverTest
        extends AbstractRelationResolverTest {

    @Test
    public void resolveRelationGolemTest() {
        Golem golem = mock(Golem.class);

        this.testResolution(golem, Relation.PASSIVE);
    }

    @Test
    public void resolveRelationIronGolemTest() {
        IronGolem ironGolem = mock(IronGolem.class);
        this.testResolution(ironGolem, Relation.NEUTRAL);
    }

    @Test
    public void resolveRelationPlayerCreatedIronGolemTest() {
        IronGolem playerCreatedIronGolem = mock(IronGolem.class);
        when(playerCreatedIronGolem.isPlayerCreated()).thenReturn(true);

        this.testResolution(playerCreatedIronGolem, Relation.PASSIVE);
    }

    @Test(expected = ClassCastException.class)
    public void resolveRelationFailTest() {
        Wolf wolf = mock(Wolf.class);

        this.resolver.resolveRelation(wolf, null);
    }

    @Test
    public void canResolveTrueTest() {
        assertTrue(this.resolver.canResolve(Golem.class));
        assertTrue(this.resolver.canResolve(IronGolem.class));
    }

    @Test
    public void canResolveFalseTest() {
        assertFalse(this.resolver.canResolve(Tamable.class));
        assertFalse(this.resolver.canResolve(Wolf.class));
        assertFalse(this.resolver.canResolve(Horse.class));
        assertFalse(this.resolver.canResolve(Player.class));
        assertFalse(this.resolver.canResolve(Monster.class));
        assertFalse(this.resolver.canResolve(ZombiePigman.class));
    }

    @Ignore
    @Override
    protected RelationResolver supplyResolver() {
        return new GolemContextIgnoringRelationResolver();
    }
}