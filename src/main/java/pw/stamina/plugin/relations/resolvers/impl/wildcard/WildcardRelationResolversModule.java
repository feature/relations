package pw.stamina.plugin.relations.resolvers.impl.wildcard;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import pw.stamina.plugin.relations.resolvers.RelationResolver;

import java.util.stream.Stream;

public final class WildcardRelationResolversModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<RelationResolver> defaultResolversBinder =
                Multibinder.newSetBinder(binder(),
                        RelationResolver.class);

        Stream.of(
                AnimalWildcardRelationResolver.class,
                MonsterWildcardRelationResolver.class
        ).forEach(resolver -> defaultResolversBinder.addBinding().to(resolver));
    }
}
