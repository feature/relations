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

package pw.stamina.plugin.relations.request;

import com.google.common.base.Strings;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.ResolutionContext;
import pw.stamina.plugin.relations.ResolvedRelationProcessor;
import pw.stamina.plugin.relations.resolvers.RelationResolver;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//TODO: Javadoc
public interface ResolveRequest {

    Entity entity();

    ResolutionContext context();

    Optional<String> requester();

    CompleteResolveRequest complete(List<RelationResolver> resolvers,
                                    List<ResolvedRelationProcessor> processors);

    static ResolveRequest anonymous(Entity entity, ResolutionContext context) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(context, "context");

        return new SimpleResolveRequest(entity, context, null);
    }

    static ResolveRequest identified(Entity entity,
                                     ResolutionContext context,
                                     String requester) {
        Objects.requireNonNull(entity, "entity");
        Objects.requireNonNull(context, "context");

        if (Strings.nullToEmpty(requester).trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "request must not be null or empty");
        }

        return new SimpleResolveRequest(entity, context, requester);
    }
}
