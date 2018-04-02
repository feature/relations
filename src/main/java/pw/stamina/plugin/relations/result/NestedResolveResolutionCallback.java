package pw.stamina.plugin.relations.result;

import pw.stamina.minecraftapi.entity.Entity;

import java.util.Objects;

//TODO: Javadoc
final class NestedResolveResolutionCallback
        implements ResolutionCallback {
    private final Entity nestedResolveTarget;

    private NestedResolveResolutionCallback(Entity nestedResolveTarget) {
        this.nestedResolveTarget = nestedResolveTarget;
    }

    @Override
    public Entity getNestedResolveTarget() {
        return nestedResolveTarget;
    }

    @Override
    public ResolutionCallbackType getType() {
        return ResolutionCallbackType.NESTED_RESOLVE;
    }

    static ResolutionCallback of(Entity nestedResolveTarget) {
        Objects.requireNonNull(nestedResolveTarget, "nestedResolveTarget");
        return new NestedResolveResolutionCallback(nestedResolveTarget);
    }
}
