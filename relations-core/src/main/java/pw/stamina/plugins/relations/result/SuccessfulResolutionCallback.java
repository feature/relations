package pw.stamina.plugins.relations.result;

import pw.stamina.plugins.relations.Relation;

import java.util.Objects;

//TODO: Javadoc
final class SuccessfulResolutionCallback implements ResolutionCallback {
    private final Relation result;

    private SuccessfulResolutionCallback(Relation result) {
        this.result = result;
    }

    @Override
    public Relation getResult() {
        return result;
    }

    @Override
    public ResolutionCallbackType getType() {
        return ResolutionCallbackType.SUCCESSFUL;
    }

    static ResolutionCallback with(Relation relation) {
        Objects.requireNonNull(relation, "relation");
        return new SuccessfulResolutionCallback(relation);
    }
}
