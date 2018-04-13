package pw.stamina.plugins.relations.resolvers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pw.stamina.plugin.relations.resolvers.impl.*;
import pw.stamina.plugins.relations.resolvers.impl.wildcard.AnimalWildcardRelationResolverTest;
import pw.stamina.plugins.relations.resolvers.impl.wildcard.MonsterWildcardRelationResolverTest;
import pw.stamina.plugins.relations.resolvers.impl.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EndermanRelationResolverTest.class,
        GolemRelationResolverTest.class,
        HorseRelationResolverTest.class,
        PlayerRelationResolverTest.class,
        TamableRelationResolverTest.class,
        VehicleRelationResolverTest.class,
        ZombiePigmanRelationResolverTest.class,

        AnimalWildcardRelationResolverTest.class,
        MonsterWildcardRelationResolverTest.class
})
public final class RelationResolverTestSuite {}