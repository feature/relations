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
import pw.stamina.minecraftapi.entity.animal.Wolf;
import pw.stamina.minecraftapi.entity.living.Golem;
import pw.stamina.minecraftapi.entity.monster.Enderman;
import pw.stamina.minecraftapi.entity.monster.ZombiePigman;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.MonsterWildcardContextIgnoringRelationResolver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public final class MonsterWildcardContextIgnoringRelationResolverTest {

    @Test
    public void testResolveRelation() {
        RelationResolver resolver = new MonsterWildcardContextIgnoringRelationResolver();

        Wolf wolf = mock(Wolf.class);
        Golem golem = mock(Golem.class);
        Enderman enderman = mock(Enderman.class);
        ZombiePigman zombiePigman = mock(ZombiePigman.class);

        assertEquals(resolver.resolveRelation(wolf, null), null);
        assertEquals(resolver.resolveRelation(golem, null), null);
        assertEquals(resolver.resolveRelation(enderman, null), Relation.HOSTILE);
        assertEquals(resolver.resolveRelation(zombiePigman, null), Relation.HOSTILE);
    }

}