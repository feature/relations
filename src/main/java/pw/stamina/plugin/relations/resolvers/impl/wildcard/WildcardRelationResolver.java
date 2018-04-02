package pw.stamina.plugin.relations.resolvers.impl.wildcard;

import pw.stamina.plugin.relations.resolvers.AbstractRelationResolver;
import pw.stamina.plugin.relations.resolvers.Priority;

//TODO: Javadoc
abstract class WildcardRelationResolver extends AbstractRelationResolver {

    @Override
    public final Priority getPriority() {
        return Priority.LAST;
    }
}
