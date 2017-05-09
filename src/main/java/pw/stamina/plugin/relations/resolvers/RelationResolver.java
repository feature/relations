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
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.result.ResolutionCallback;
import pw.stamina.plugin.relations.select.RelationSelectorService;

/**
 * Basic interface for resolve {@link Entity entity}
 * {@link Relation relations}.
 */
public interface RelationResolver extends Comparable<RelationResolver> {

    /**
     * Tries to resolve a relation to the specified {@link
     * Entity entity} within the specified {@link ResolutionContext
     * context}. This method returns a {@link ResolutionCallback},
     * this object is used to signal to the {@link RelationSelectorService}
     * how the result of this method should be handled.
     *
     * @param entity the entity resolving the relation to
     * @param context the resolution context
     * @return a {@link ResolutionCallback} which indicates
     * how the result of this resolver should be handled
     */
    ResolutionCallback resolveRelation(ResolveRequest request);

    /**
     * Indicates if this resolver should try to resolve a relation
     * to the entity of the specified <tt>entityType</tt>. This
     * method should be stateless, meaning the method must always
     * return the same value for equal <tt>entityType</tt>s.
     *
     * @param entityType the entity type to check
     * @return <tt>true</tt> if this resolver should try to resolve
     * a relation for this entity type, otherwise returns <tt>false</tt>
     */
    boolean canResolve(Class<? extends Entity> entityType);

    /**
     * The priority of this resolver. The priority determines in
     * which order resolvers should be tried. By default this
     * method returns {@link Priority#NORMAL}.
     *
     * @return {@link Priority#NORMAL} by default, otherwise the
     * specified value.
     */
    default Priority getPriority() {
        return Priority.NORMAL;
    }

    /**
     * Compares the {@link RelationResolver#getPriority()}
     * as described in Javadoc of the {@link Enum#compareTo(Enum)}
     * method.
     *
     * @param o other {@link RelationResolver}
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    @Override
    int compareTo(RelationResolver o);
}
