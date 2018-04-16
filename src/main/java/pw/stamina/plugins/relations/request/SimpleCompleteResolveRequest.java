package pw.stamina.plugins.relations.request;

import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

import java.util.List;

//TODO: Javadoc
//#resolvers and #processors are not wrapped in an unmodifiable decoration,
// as it is expected to be instantiated with unmodifiable lists.
final class SimpleCompleteResolveRequest
        implements CompleteResolveRequest {

    private final ResolveRequest request;
    private final List<RelationResolver> resolvers;
    private final List<ResolvedRelationProcessor> processors;

    SimpleCompleteResolveRequest(ResolveRequest request,
                                 List<RelationResolver> resolvers,
                                 List<ResolvedRelationProcessor> processors) {
        this.request = request;
        this.resolvers = resolvers;
        this.processors = processors;
    }

    @Override
    public ResolveRequest request() {
        return request;
    }

    @Override
    public List<RelationResolver> resolvers() {
        return resolvers;
    }

    @Override
    public List<ResolvedRelationProcessor> processors() {
        return processors;
    }
}
