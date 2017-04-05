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

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.resolvers.RelationResolver;
import pw.stamina.plugin.relations.select.RelationSelectorService;

/**
 * This class is used for {@link RelationResolver}s to
 * signal to the {@link RelationSelectorService} how their
 * resolution should be handled.
 */
public interface ResolutionCallback {

    default Relation getResult() {
        throw new UnsupportedOperationException();
    }

    default Entity getNestedResolveTarget() {
        throw new UnsupportedOperationException();
    }

    ResolutionCallbackType getType();

    /**
     * Returns a new successful {@link ResolutionCallback},
     * with the specified relation as its result. The
     * {@link #getResult()} may be on on this this
     * {@link ResolutionCallback} instance.
     *
     * @param relation the resolve relation
     * @return a new successful {@link ResolutionCallback},
     * with the specified relation as its result
     */
    static ResolutionCallback success(Relation relation) {
        return SuccessfulResolutionCallback.with(relation);
    }

    /**
     * Returns a {@link ResolutionCallback} indicating
     * the resolution was successful.
     *
     * @return a failed {@link ResolutionCallback}
     */
    static ResolutionCallback failed() {
        return FailedResolutionCallback.INSTANCE;
    }

    /**
     * Returns a new {@link ResolutionCallback} requested
     * a nested relation resolve on the specified entity. The
     * {@link #getNestedResolveTarget()} may be on on this
     * this {@link ResolutionCallback} instance.
     *
     * @param entity entity requesting the nested resolve for
     * @return a new {@link ResolutionCallback} requesting
     * a nested relation resolve
     */
    static ResolutionCallback nestedResolve(Entity entity) {
        return NestedResolveResolutionCallback.of(entity);
    }
}
