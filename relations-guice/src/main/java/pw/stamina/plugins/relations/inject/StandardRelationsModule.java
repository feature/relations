package pw.stamina.plugins.relations.inject;

import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import pw.stamina.plugins.relations.RelationManager;
import pw.stamina.plugins.relations.StandardRelationManager;
import pw.stamina.plugins.relations.inject.resolvers.StandardRelationResolversModule;
import pw.stamina.plugins.relations.inject.resolvers.WildcardRelationResolversModule;
import pw.stamina.plugins.relations.select.CachingRelationSelectorService;
import pw.stamina.plugins.relations.select.RelationSelectorService;

public final class StandardRelationsModule extends PrivateModule {

    @Override
    protected void configure() {
        install(new StandardRelationResolversModule());
        install(new WildcardRelationResolversModule());

        bind(RelationSelectorService.class).to(CachingRelationSelectorService.class);

        bind(RelationManager.class)
                .to(StandardRelationManager.class)
                .in(Singleton.class);

        expose(RelationManager.class);
    }
}
