package pw.stamina.plugin.relations.resolvers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pw.stamina.plugin.relations.resolvers.impl.*;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.AnimalWildcardRelationResolverTest;
import pw.stamina.plugin.relations.resolvers.impl.wildcard.MonsterWildcardRelationResolverTest;

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