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

package pw.stamina.plugin.relations;

import pw.stamina.plugin.relations.request.ResolveRequest;

/**
 * This interface is used to process relations
 * after they have been resolved. This allows
 * developers to replace a relation based on
 * the entity's resolved relation.
 */
public interface ResolvedRelationProcessor {

    /**
     * Determines if the <tt>original</tt> resolved
     * relation should be replaced or not. If so a
     * new {@link Relation} is return, otherwise null
     * is returned to indicate the original should be
     * kept. If null is returned the next processed
     * will be tried.
     *
     * @param original the original relation
     * @param entity the entity the relation was resolved for
     * @param context the resolution context
     * @return a new {@link Relation}, or null if none was found.
     */
    //TODO: Javadoc
    Relation process(ResolveRequest request,
                     Relation original);
}
