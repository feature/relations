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
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.ResolutionContext;

public interface RelationResolver {

    /**
     *
     * @param entity the entity resolving the relation to
     * @param context the resolution context
     * @return the resolved {@link Relation}, or <tt>null</tt>
     * if none was resolved.
     */
    Relation resolveRelation(Entity entity,
                             ResolutionContext context);

    /**
     * Indicates if this resolver should try to resolve a relation
     * of the specified <tt>entityType</tt>.
     *
     * @param entityType the entity type to check
     * @return <tt>true</tt> if this resolver should try to resolve
     * a relation, otherwise returns <tt>false</tt>
     */
    boolean canResolve(Class<? extends Entity> entityType);
}
