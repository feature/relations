package pw.stamina.plugins.relations.select;

import pw.stamina.plugins.relations.Relation;
import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.request.ResolveRequest;

import java.util.List;

//TODO: Javadoc
public abstract class AbstractRelationSelectorService
        implements RelationSelectorService {

    protected final Relation processRelation(
            List<ResolvedRelationProcessor> processors,
            ResolveRequest request,
            Relation original) {

        if (processors.isEmpty()) {
            return original;
        }

        return processors.stream()
                .map(processor -> processor.process(request, original))
                .findFirst()
                .orElse(original);
    }
}
