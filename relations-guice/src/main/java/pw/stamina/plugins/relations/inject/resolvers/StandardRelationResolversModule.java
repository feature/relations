package pw.stamina.plugins.relations.inject.resolvers;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.resolvers.impl.*;

import java.util.stream.Stream;

public final class StandardRelationResolversModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<RelationResolver> defaultResolversBinder =
                Multibinder.newSetBinder(binder(),
                        RelationResolver.class);

        Stream.of(
                EndermanRelationResolver.class,
                GolemRelationResolver.class,
                HorseRelationResolver.class,
                PlayerRelationResolver.class,
                TamableRelationResolver.class,
                VehicleRelationResolver.class,
                ZombiePigmanRelationResolver.class
        ).forEach(resolver -> defaultResolversBinder.addBinding().to(resolver));
    }
}
