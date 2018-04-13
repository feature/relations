package pw.stamina.plugins.relations;

import pw.stamina.plugins.relations.request.ResolveRequest;

/**
 * This interface is used to process relations
 * after they have been resolved. This allows
 * developers to replace a relation based on
 * the entity's resolved relation.
 */
public interface ResolvedRelationProcessor {

    /**
     * Determines if the <tt>original</tt> resolved
     * relation should be replaced or not. If so a
     * new {@link Relation} is return, otherwise null
     * is returned to indicate the original should be
     * kept. If null is returned the next processed
     * will be tried.
     *
     * @param original the original relation
     * @param entity the entity the relation was resolved for
     * @param context the resolution context
     * @return a new {@link Relation}, or null if none was found.
     */
    //TODO: Javadoc
    Relation process(ResolveRequest request,
                     Relation original);
}
