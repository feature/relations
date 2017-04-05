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

package pw.stamina.plugin.relations.result;

import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.select.RelationSelectorService;

/**
 * An enumeration of available {@link ResolutionCallback#getType()
 * types}. This is used to relay to the {@link RelationSelectorService}
 * how the return {@link ResolutionCallback} should be handled.
 */
public enum ResolutionCallbackType {

    /**
     * Indicates that the resolution was successful and
     * a {@link Relation} has been resolved. This value
     * means that the {@link ResolutionCallback#getResult()}
     * method may be used to return to result.
     */
    SUCCESSFUL,
    /**
     * Indicates that the resolver was unable to resolve
     * a {@link Relation}, and the {@link RelationSelectorService}
     * should continue to the next resolver(s).
     */
    FAILED,
    /**
     * Requests the {@link RelationSelectorService} to do
     * a nested resolve of an entity. This indicates that
     * the {@link ResolutionCallback#getNestedResolveTarget()}
     * may be used to return the entity to perform the
     * nested resolve on.
     */
    NESTED_RESOLVE
}
