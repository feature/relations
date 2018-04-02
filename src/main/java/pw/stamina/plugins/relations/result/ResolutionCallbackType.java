package pw.stamina.plugins.relations.result;

import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.select.RelationSelectorService;

/**
 * An enumeration of available {@link ResolutionCallback#getType()
 * types}. This is used to relay to the {@link RelationSelectorService}
 * how the return {@link ResolutionCallback} should be handled.
 */
public enum ResolutionCallbackType {

    /**
     * Indicates that the resolution was successful and
     * a {@link Relation} has been resolved. This value
     * means that the {@link ResolutionCallback#getResult()}
     * method may be used to return to result.
     */
    SUCCESSFUL,
    /**
     * Indicates that the resolver was unable to resolve
     * a {@link Relation}, and the {@link RelationSelectorService}
     * should continue to the next resolver(s).
     */
    FAILED,
    /**
     * Requests the {@link RelationSelectorService} to do
     * a nested resolve of an entity. This indicates that
     * the {@link ResolutionCallback#getNestedResolveTarget()}
     * may be used to return the entity to perform the
     * nested resolve on.
     */
    NESTED_RESOLVE
}
