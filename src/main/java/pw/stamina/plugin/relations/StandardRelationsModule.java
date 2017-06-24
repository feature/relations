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

import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import pw.stamina.plugin.relations.resolvers.impl.StandardRelationResolversModule;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.WildcardRelationResolversModule;
import pw.stamina.plugin.relations.select.CachingRelationSelectorService;
import pw.stamina.plugin.relations.select.RelationSelectorService;

public final class StandardRelationsModule extends PrivateModule {

    @Override
    protected void configure() {
        install(new StandardRelationResolversModule());
        install(new WildcardRelationResolversModule());

        bind(RelationSelectorService.class).to(CachingRelationSelectorService.class);

        bind(RelationManager.class)
                .to(CopyOnWriteRelationManager.class)
                .in(Singleton.class);

        expose(RelationManager.class);
    }
}
