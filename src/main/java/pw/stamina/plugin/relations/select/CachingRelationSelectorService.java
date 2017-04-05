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

package pw.stamina.plugin.relations.select;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.Relation;
import pw.stamina.plugin.relations.ResolutionContext;
import pw.stamina.plugin.relations.ResolvedRelationProcessor;
import pw.stamina.plugin.relations.resolvers.RelationResolver;
import pw.stamina.plugin.relations.result.ResolutionCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO: Javadoc
public final class CachingRelationSelectorService
        extends AbstractRelationSelectorService {

    private final Map<Class<? extends Entity>, List<RelationResolver>>
            cachedResolvers = new HashMap<>();

    @Override
    public Relation select(List<RelationResolver> resolvers,
                           List<ResolvedRelationProcessor> processors,
                           Entity entity,
                           ResolutionContext context) {

        List<RelationResolver> cachedResolvers = this.findCachedResolvers(
                entity, resolvers);

        for (RelationResolver resolver : cachedResolvers) {
            ResolutionCallback callback = resolver
                    .resolveRelation(entity, context);

            switch (callback.getType()) {
                case SUCCESSFUL:
                    return this.processRelation(
                            processors,
                            callback.getResult(),
                            entity,
                            context);
                case NESTED_RESOLVE:
                    return this.select(
                            resolvers,
                            processors,
                            callback.getNestedResolveTarget(),
                            context);
                case FAILED:
                    break;
            }
        }

        return Relation.UNRECOGNIZED;
    }

    private List<RelationResolver> findCachedResolvers(Entity entity,
                                                       List<RelationResolver> resolvers) {
        Class<? extends Entity> entityType = entity.getClass();

        return this.cachedResolvers
                .computeIfAbsent(entityType, key -> resolvers.stream()
                        .filter(resolver -> resolver.canResolve(key))
                        .collect(Collectors.toList()));
    }

    /**
     * Delegates the <tt>resolver</tt> to the {@link
     * #invalidateCache(RelationResolver)} method.
     *
     * @param resolver the resolver changed
     */
    @Override
    public void notifyResolverChange(RelationResolver resolver) {
        this.invalidateCache(resolver);
    }

    /**
     * Removes the cache entries by key if the key
     * is accepted by the {@link RelationResolver#canResolve(Class)}
     * method of the specified <tt>resolver</tt>.
     *
     * @param resolver resolver invalidating the cache for
     */
    private void invalidateCache(RelationResolver resolver) {
        this.cachedResolvers.keySet()
                .removeIf(resolver::canResolve);
    }
}
