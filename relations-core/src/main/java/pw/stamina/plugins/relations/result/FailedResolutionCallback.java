package pw.stamina.plugins.relations.result;

//TODO: Javadoc
enum FailedResolutionCallback implements ResolutionCallback {
    INSTANCE;

    @Override
    public ResolutionCallbackType getType() {
        return ResolutionCallbackType.FAILED;
    }
}
