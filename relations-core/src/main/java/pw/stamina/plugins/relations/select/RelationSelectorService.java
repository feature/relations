package pw.stamina.plugins.relations.select;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.request.CompleteResolveRequest;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

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
    //TODO: update
    Relation select(CompleteResolveRequest completeRequest);

    /**
     * Notifies this {@link RelationSelectorService} that a
     * resolver has been registered or unregistered. This is
     * primarily used for invalidating cached resolvers.
     *
     * @param resolver resolver changed
     */
    void notifyResolverChange(RelationResolver resolver);
}
