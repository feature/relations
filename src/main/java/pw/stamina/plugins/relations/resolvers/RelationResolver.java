package pw.stamina.plugins.relations.resolvers;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.ResolutionContext;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.result.ResolutionCallback;
import pw.stamina.plugins.relations.select.RelationSelectorService;

/**
 * Basic interface for resolve {@link Entity entity}
 * {@link Relation relations}.
 */
public interface RelationResolver extends Comparable<RelationResolver> {

    /**
     * Tries to resolve a relation to the specified {@link
     * Entity entity} within the specified {@link ResolutionContext
     * context}. This method returns a {@link ResolutionCallback},
     * this object is used to signal to the {@link RelationSelectorService}
     * how the result of this method should be handled.
     *
     * @param entity the entity resolving the relation to
     * @param context the resolution context
     * @return a {@link ResolutionCallback} which indicates
     * how the result of this resolver should be handled
     */
    ResolutionCallback resolveRelation(ResolveRequest request);

    /**
     * Indicates if this resolver should try to resolve a relation
     * to the entity of the specified <tt>entityType</tt>. This
     * method should be stateless, meaning the method must always
     * return the same value for equal <tt>entityType</tt>s.
     *
     * @param entityType the entity type to check
     * @return <tt>true</tt> if this resolver should try to resolve
     * a relation for this entity type, otherwise returns <tt>false</tt>
     */
    boolean canResolve(Class<? extends Entity> entityType);

    /**
     * The priority of this resolver. The priority determines in
     * which order resolvers should be tried. By default this
     * method returns {@link Priority#NORMAL}.
     *
     * @return {@link Priority#NORMAL} by default, otherwise the
     * specified value.
     */
    default Priority getPriority() {
        return Priority.NORMAL;
    }

    /**
     * Compares the {@link RelationResolver#getPriority()}
     * as described in Javadoc of the {@link Enum#compareTo(Enum)}
     * method.
     *
     * @param o other {@link RelationResolver}
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    @Override
    int compareTo(RelationResolver o);
}
