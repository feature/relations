package pw.stamina.plugin.relations.request;

import pw.stamina.plugin.relations.ResolvedRelationProcessor;
import pw.stamina.plugin.relations.resolvers.RelationResolver;

import java.util.List;

//TODO: Javadoc
public interface CompleteResolveRequest {

    ResolveRequest request();

    List<RelationResolver> resolvers();

    List<ResolvedRelationProcessor> processors();
}
