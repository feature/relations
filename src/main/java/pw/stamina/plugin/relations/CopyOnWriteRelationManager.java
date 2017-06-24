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

import com.google.inject.Inject;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.request.ResolveRequest;
import pw.stamina.plugin.relations.resolvers.RelationResolver;
import pw.stamina.plugin.relations.select.RelationSelectorService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

//TODO: Javadoc
public final class CopyOnWriteRelationManager
        implements RelationManager {

    private final CopyOnWriteArrayList<RelationResolver> resolvers;
    private final CopyOnWriteArrayList<ResolvedRelationProcessor> processors;
    private final RelationSelectorService selectorService;

    /**
     * Constructs a new <tt>CopyOnWriteRelationManager</tt>
     * with the specified default resolvers, and the
     * specified <tt>selectorService</tt>.
     *
     * @param defaultResolvers set of default resolvers
     * @param selectorService  relation selector service
     */
    @Inject
    public CopyOnWriteRelationManager(
            Set<RelationResolver> defaultResolvers,
            RelationSelectorService selectorService) {
        Objects.requireNonNull(selectorService, "selectorService");

        this.resolvers = new CopyOnWriteArrayList<>();
        this.processors = new CopyOnWriteArrayList<>();
        this.selectorService = selectorService;

        if (isDefaultResolverNotNullOrEmpty(defaultResolvers)) {
            this.resolvers.addAll(
                    defaultResolvers.stream()
                            .sorted()
                            .collect(Collectors.toList()));
        }
    }

    private boolean isDefaultResolverNotNullOrEmpty(
            Set<RelationResolver> defaultResolvers) {
        return (defaultResolvers != null)
                && !defaultResolvers.isEmpty();
    }

    @Override
    public Relation findAttackRelation(Entity entity) {
        return findRelation(entity, ResolutionContext.ATTACK);
    }

    @Override
    public Relation findRenderRelation(Entity entity) {
        return findRelation(entity, ResolutionContext.RENDER);
    }

    private Relation findRelation(Entity entity,
                                  ResolutionContext context) {
        return findRelation(ResolveRequest.anonymous(entity, context));
    }

    @Override
    public Relation findRelation(ResolveRequest request) {
        return selectorService.select(
                request.complete(
                        findResolvers(),
                        findProcessors()));
    }

    @Override
    public boolean registerResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        //TODO: Find insertion index instead of sorting
        if (resolvers.addIfAbsent(resolver)) {
            Collections.sort(resolvers);
            selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unregisterResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        if (resolvers.remove(resolver)) {
            selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<RelationResolver> findResolvers() {
        return Collections.unmodifiableList(resolvers);
    }

    @Override
    public boolean registerProcessor(ResolvedRelationProcessor processor) {
        return processors.addIfAbsent(processor);
    }

    @Override
    public boolean unregisterProcessor(ResolvedRelationProcessor processor) {
        return processors.remove(processor);
    }

    @Override
    public List<ResolvedRelationProcessor> findProcessors() {
        return Collections.unmodifiableList(processors);
    }
}
