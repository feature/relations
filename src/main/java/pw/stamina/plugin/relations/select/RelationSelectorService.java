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

import java.util.List;

//TODO: Javadoc
public interface RelationSelectorService {

    /**
     * Selects a {@link Relation relation} for the
     * specified {@link Entity entity}, based on the
     * provides <tt>resolvers</tt> nad <tt>processors</tt>.
     * If no relation was selected, a {@link Relation#UNRECOGNIZED}
     * will be returned instead.
     *
     * @param resolvers the resolvers used for resolving a relation
     * @param processors the processors
     * @param entity the entity selecting the relation to
     * @param context the resolution context for the resolvers
     * @return the selected relation, or <tt>null</tt>
     * in none was selected.
     */
    Relation select(List<RelationResolver> resolvers,
                    List<ResolvedRelationProcessor> processors,
                    Entity entity,
                    ResolutionContext context);

    /**
     * Notifies this {@link RelationSelectorService} that a
     * resolver has been registered or unregistered. This is
     * primarily used for invalidating cached resolvers.
     *
     * @param resolver resolver changed
     */
    void notifyResolverChange(RelationResolver resolver);
}
