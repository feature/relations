package pw.stamina.plugins.relations.resolvers;

import java.util.Comparator;

//TODO: Javadoc
public abstract class AbstractRelationResolver implements RelationResolver {
    private static final Comparator<RelationResolver> PRIORITY_COMPARING_RELATION_RESOLVER_COMPARATOR =
            Comparator.comparing(RelationResolver::getPriority);

    @Override
    public final int compareTo(RelationResolver o) {
        return PRIORITY_COMPARING_RELATION_RESOLVER_COMPARATOR.compare(this, o);
    }
}
