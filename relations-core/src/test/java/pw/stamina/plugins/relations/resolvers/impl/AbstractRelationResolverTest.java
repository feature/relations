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

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.ResolutionContext;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.result.ResolutionCallback;

import static org.junit.Assert.assertSame;

public abstract class AbstractRelationResolverTest {
    protected final RelationResolver resolver = supplyResolver();
    protected static final ResolutionContext DUMMY_CONTEXT
            = ResolutionContext.getInstance("dummy");

    protected final void testResolution(Entity entity, Relation expectedRelation) {
        assertSame(expectedRelation, resolve(entity).getResult());
    }

    protected final ResolutionCallback resolve(Entity entity) {
        return resolver.resolveRelation(ResolveRequest.anonymous(entity, DUMMY_CONTEXT));
    }

    protected abstract RelationResolver supplyResolver();
}
