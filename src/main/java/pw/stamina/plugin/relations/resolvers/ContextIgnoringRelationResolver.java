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

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.ResolutionContext;
import pw.stamina.plugin.relations.result.ResolutionCallback;
import pw.stamina.plugin.relations.select.RelationSelectorService;

/**
 * Abstract {@link ResolutionContext context} ignoring
 * {@link RelationResolver}. This class is used to
 * indicate that a resolver return the same resolution
 * regardless of context.
 */
public abstract class ContextIgnoringRelationResolver
        extends AbstractRelationResolver {

    /**
     * Tries to resolve a relation to the specified {@link
     * Entity entity}. This method returns a {@link ResolutionCallback},
     * this object is used to signal to the
     * {@link RelationSelectorService} how the result of
     * this method should be handled.
     *
     * @param entity the entity resolving the relation to
     * @return a {@link ResolutionCallback} which indicates
     * how the result of this resolver should be handled
     */
    protected abstract ResolutionCallback resolveRelation(Entity entity);

    /**
     * Delegates to the {@link #resolveRelation(Entity)}
     * ignoring the specified <tt>context</tt>.
     *
     * @param entity the entity resolving the relation to
     * @param context ignored
     * @return a {@link ResolutionCallback} which indicates
     * how the result of this resolver should be handled
     */
    @Override
    public final ResolutionCallback resolveRelation(Entity entity,
                                                    ResolutionContext context) {
        return this.resolveRelation(entity);
    }
}
