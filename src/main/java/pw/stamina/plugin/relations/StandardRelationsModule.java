package pw.stamina.plugin.relations;

import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import pw.stamina.plugin.relations.resolvers.impl.StandardRelationResolversModule;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.WildcardRelationResolversModule;
import pw.stamina.plugin.relations.select.CachingRelationSelectorService;
import pw.stamina.plugin.relations.select.RelationSelectorService;

public final class StandardRelationsModule extends PrivateModule {

    @Override
    protected void configure() {
        install(new StandardRelationResolversModule());
        install(new WildcardRelationResolversModule());

        bind(RelationSelectorService.class).to(CachingRelationSelectorService.class);

        bind(RelationManager.class)
                .to(CopyOnWriteRelationManager.class)
                .in(Singleton.class);

        expose(RelationManager.class);
    }
}
