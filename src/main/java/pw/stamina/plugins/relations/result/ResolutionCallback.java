package pw.stamina.plugins.relations.result;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.select.RelationSelectorService;

/**
 * This class is used for {@link RelationResolver}s to
 * signal to the {@link RelationSelectorService} how their
 * resolution should be handled.
 */
public interface ResolutionCallback {

    //TODO: Javadoc
    default Relation getResult() {
        throw new UnsupportedOperationException();
    }

    //TODO: Javadoc
    default Entity getNestedResolveTarget() {
        throw new UnsupportedOperationException();
    }

    //TODO: Javadoc
    ResolutionCallbackType getType();

    /**
     * Returns a new successful {@link ResolutionCallback},
     * with the specified relation as its result. The
     * {@link #getResult()} may be on on this this
     * {@link ResolutionCallback} instance.
     *
     * @param relation the resolve relation
     * @return a new successful {@link ResolutionCallback},
     * with the specified relation as its result
     */
    static ResolutionCallback success(Relation relation) {
        return SuccessfulResolutionCallback.with(relation);
    }

    /**
     * Returns a {@link ResolutionCallback} indicating
     * the resolution was successful.
     *
     * @return a failed {@link ResolutionCallback}
     */
    static ResolutionCallback failed() {
        return FailedResolutionCallback.INSTANCE;
    }

    /**
     * Returns a new {@link ResolutionCallback} requested
     * a nested relation resolve on the specified entity. The
     * {@link #getNestedResolveTarget()} may be on on this
     * this {@link ResolutionCallback} instance.
     *
     * @param entity entity requesting the nested resolve for
     * @return a new {@link ResolutionCallback} requesting
     * a nested relation resolve
     */
    static ResolutionCallback nestedResolve(Entity entity) {
        return NestedResolveResolutionCallback.of(entity);
    }
}
