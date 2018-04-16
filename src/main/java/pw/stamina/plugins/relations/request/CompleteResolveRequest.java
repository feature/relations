package pw.stamina.plugins.relations.request;

import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

import java.util.List;

//TODO: Javadoc
public interface CompleteResolveRequest {

    ResolveRequest request();

    List<RelationResolver> resolvers();

    List<ResolvedRelationProcessor> processors();
}
