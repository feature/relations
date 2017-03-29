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

import com.google.auto.service.AutoService;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import pw.stamina.plugin.relations.resolvers.DefaultResolvers;
import pw.stamina.plugin.relations.resolvers.RelationResolver;
import pw.stamina.plugin.relations.resolvers.impl.*;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.AnimalWildcardContextIgnoringRelationResolver;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.MonsterWildcardContextIgnoringRelationResolver;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.PlayerWildcardContextIgnoringRelationResolver;

import java.util.*;

@AutoService(Module.class)
public final class RelationsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RelationManager.class)
                .to(SimpleRelationManager.class)
                .in(Singleton.class);

        bind(RelationSelectorService.class)
                .to(CachingRelationSelectorService.class);

        this.registerDefaultResolvers();
    }

    private void registerDefaultResolvers() {
        Multibinder<RelationResolver> defaultResolversBinder =
                Multibinder.newSetBinder(binder(),
                        RelationResolver.class,
                        DefaultResolvers.class);

        Arrays.asList(
                EndermanContextIgnoringRelationResolver.class,
                GolemContextIgnoringRelationResolver.class,
                HorseContextIgnoringRelationResolver.class,
                TamableContextIgnoringRelationResolver.class,
                VehicleContextIgnoringRelationResolver.class,
                ZombiePigmanContextIgnoringRelationResolver.class,

                //Wildcard resolvers
                AnimalWildcardContextIgnoringRelationResolver.class,
                MonsterWildcardContextIgnoringRelationResolver.class,
                PlayerWildcardContextIgnoringRelationResolver.class
        ).forEach(resolver -> defaultResolversBinder.addBinding().to(resolver));
    }

    @Provides
    @DefaultResolvers
    List<RelationResolver> provideNormalizedDefaultResolverAsList(
            @DefaultResolvers
            Set<RelationResolver> defaultResolvers) {
        return Collections.unmodifiableList(new ArrayList<>(defaultResolvers));
    }
}
