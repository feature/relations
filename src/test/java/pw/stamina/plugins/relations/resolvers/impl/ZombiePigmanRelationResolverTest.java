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

import org.junit.Ignore;
import org.junit.Test;
import pw.stamina.minecraftapi.entity.animal.Horse;
import pw.stamina.minecraftapi.entity.animal.Tamable;
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Golem;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.entity.monster.Monster;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class ZombiePigmanRelationResolverTest
        extends AbstractRelationResolverTest {

    @Test
    public void resolveRelationNeutralZombiePigmanTest() {
        ZombiePigman zombiePigman = mock(ZombiePigman.class);

        testResolution(zombiePigman, Relation.NEUTRAL);
    }

    @Test
    public void resolveRelationAngryZombiePigmanTest() {
        ZombiePigman angryZombiePigman = mock(ZombiePigman.class);
        when(angryZombiePigman.isAngry()).thenReturn(true);

        testResolution(angryZombiePigman, Relation.HOSTILE);
    }

    @Test(expected = ClassCastException.class)
    public void resolveRelationFailTest() {
        Player player = mock(Player.class);

        resolve(player);
    }

    @Test
    public void canResolveTrueTest() {
        assertTrue(resolver.canResolve(ZombiePigman.class));
    }

    @Test
    public void canResolveFalseTest() {
        assertFalse(resolver.canResolve(Tamable.class));
        assertFalse(resolver.canResolve(Wolf.class));
        assertFalse(resolver.canResolve(Horse.class));
        assertFalse(resolver.canResolve(Player.class));
        assertFalse(resolver.canResolve(Monster.class));
        assertFalse(resolver.canResolve(Golem.class));
    }

    @Ignore
    @Override
    protected RelationResolver supplyResolver() {
        return new ZombiePigmanRelationResolver();
    }
}