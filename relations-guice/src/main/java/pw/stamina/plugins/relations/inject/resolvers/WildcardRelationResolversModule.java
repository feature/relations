package pw.stamina.plugins.relations.inject.resolvers;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.resolvers.impl.wildcard.AnimalWildcardRelationResolver;
import pw.stamina.plugins.relations.resolvers.impl.wildcard.MonsterWildcardRelationResolver;

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
