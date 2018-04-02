package pw.stamina.plugins.relations.resolvers.impl.wildcard;

import pw.stamina.plugins.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugins.relations.resolvers.Priority;

//TODO: Javadoc
abstract class WildcardRelationResolver extends AbstractRelationResolver {

    @Override
    public final Priority getPriority() {
        return Priority.LAST;
    }
}
