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

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class SimpleRelationSelectorService implements RelationSelectorService {

    @Override
    public Relation select(List<RelationResolver> externalResolvers,
                           List<RelationResolver> defaultResolvers,
                           Entity entity,
                           ResolutionContext context) {

        return Stream.concat(externalResolvers.stream(), defaultResolvers.stream())
                .map(resolver -> resolver
                        .resolveRelation(entity, context))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(Relation.UNRECOGNIZED);
    }
}
