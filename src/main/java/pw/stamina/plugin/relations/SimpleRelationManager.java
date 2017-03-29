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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SimpleRelationManager implements RelationManager {
    private final List<RelationResolver> externalResolvers;
    private final List<RelationResolver> defaultResolvers;
    private final RelationSelectorService selectorService;

    @Inject
    public SimpleRelationManager(
            @DefaultResolvers
                    List<RelationResolver> defaultResolvers,
            RelationSelectorService selectorService) {
        Objects.requireNonNull(defaultResolvers, "defaultResolvers");
        Objects.requireNonNull(selectorService, "selectorService");

        this.externalResolvers = new ArrayList<>();
        this.defaultResolvers = new ArrayList<>(defaultResolvers);
        this.selectorService = selectorService;
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
                this.findExternalRelationResolvers(),
                this.getDefaultRelationResolvers(),
                entity, context);
    }

    @Override
    public boolean registerResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        if (!this.externalResolvers.contains(resolver)
                && this.externalResolvers.add(resolver)) {
            this.selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean unregisterResolver(RelationResolver resolver) {
        Objects.requireNonNull(resolver, "resolver");

        if (this.externalResolvers.remove(resolver)) {
            this.selectorService.notifyResolverChange(resolver);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<RelationResolver> findExternalRelationResolvers() {
        return (this.externalResolvers.isEmpty())
                ? Collections.emptyList()
                : Collections.unmodifiableList(this.externalResolvers);
    }

    @Override
    public List<RelationResolver> getDefaultRelationResolvers() {
        return Collections.unmodifiableList(this.defaultResolvers);
    }

    @Override
    public void clearResolvers() {
        this.externalResolvers.forEach(
                this.selectorService::notifyResolverChange);

        this.externalResolvers.clear();
    }
}
