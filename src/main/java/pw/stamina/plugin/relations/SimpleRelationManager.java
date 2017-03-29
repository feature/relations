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
import pw.stamina.plugin.relations.resolvers.DefaultResolvers;
import pw.stamina.plugin.relations.resolvers.RelationResolver;
import pw.stamina.plugin.relations.select.RelationSelectorService;

import java.util.*;

public final class SimpleRelationManager implements RelationManager {
    private final List<RelationResolver> resolvers;
    private final List<ResolvedRelationProcessor> processors;

    private final RelationSelectorService selectorService;

    @Inject
    public SimpleRelationManager(
            @DefaultResolvers
                    Set<RelationResolver> defaultResolvers,
            RelationSelectorService selectorService) {
        Objects.requireNonNull(defaultResolvers, "defaultResolvers");
        Objects.requireNonNull(selectorService, "selectorService");

        this.resolvers = new ArrayList<>();
        this.resolvers.addAll(defaultResolvers);
        Collections.sort(this.resolvers);

        this.selectorService = selectorService;
        this.processors = new ArrayList<>();
    }

    @Override
    public Relation findAttackRelation(Entity entity) {
        return this.findRelation(entity, ResolutionContext.ATTACK);
    }

    @Override
    public Relation findRenderRelation(Entity entity) {
        return this.findRelation(entity, ResolutionContext.RENDER);
    }

    private Relation findRelation(Entity entity,
                                  ResolutionContext context) {
        return this.selectorService.select(
                this.findResolvers(),
                this.findProcessors(),
                entity, context);
    }

    @Override
    public boolean registerResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        if (!this.resolvers.contains(resolver)
                && this.resolvers.add(resolver)) {
            Collections.sort(this.resolvers);
            this.selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unregisterResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        if (this.resolvers.remove(resolver)) {
            this.selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<RelationResolver> findResolvers() {
        return Collections.unmodifiableList(this.resolvers);
    }

    @Override
    public boolean registerProcessor(ResolvedRelationProcessor processor) {
        return !this.processors.contains(processor)
                && this.processors.add(processor);
    }

    @Override
    public boolean unregisterProcessor(ResolvedRelationProcessor processor) {
        return this.processors.remove(processor);
    }

    @Override
    public List<ResolvedRelationProcessor> findProcessors() {
        return Collections.unmodifiableList(this.processors);
    }
}
