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

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.resolvers.RelationResolver;

import java.util.Collection;
import java.util.List;

/**
 * RelationManager handles registration of {@link RelationResolver
 * resolvers}, and acts as an entry point to find {@link Relation
 * relations} to {@link Entity entities}.
 */
public interface RelationManager {

    /**
     * Finds a {@link Relation} to the specified {@link Entity}
     * entity in a attack context. If no relation has been
     * resolved a {@link Relation#UNRECOGNIZED} relation is
     * returned.
     *
     * @param entity the specified entity getting the relation to
     * @return the resolved relation, or {@link Relation#UNRECOGNIZED}
     * if none is found.
     */
    Relation findAttackRelation(Entity entity);

    /**
     * Finds a {@link Relation} to the specified {@link Entity}
     * entity in a render context. If no relation has been
     * resolved a {@link Relation#UNRECOGNIZED} relation is
     * returned.
     *
     * @param entity the specified entity getting the relation to
     * @return the resolved relation, or {@link Relation#UNRECOGNIZED}
     * if none is found.
     */
    Relation findRenderRelation(Entity entity);

    /**
     * Registers an a resolver to the list of resolvers.
     *
     * @param resolver the resolver to register
     * @return <tt>true</tt> if the resolver was not already registered,
     * and if the resolver was successfully registered, otherwise
     * returns <tt>false</tt>
     */
    boolean registerResolver(RelationResolver resolver);

    /**
     * Unregisters a resolver from the list of resolvers.
     *
     * @param resolver the resolver to unregister
     * @return <tt>true</tt> if the resolver was registered, and
     * successfully unregistered, otherwise returns <tt>false</tt>
     */
    boolean unregisterResolver(RelationResolver resolver);

    /**
     * Finds an unmodifiable {@link Collection} of resolvers.
     *
     * @return an unmodifiable {@link Collection} of resolvers
     */
    List<RelationResolver> findResolvers();

    //TODO: Javadoc
    boolean registerProcessor(ResolvedRelationProcessor processor);

    boolean unregisterProcessor(ResolvedRelationProcessor processor);

    List<ResolvedRelationProcessor> findProcessors();
}
