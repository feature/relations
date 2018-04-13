package pw.stamina.plugins.relations;

import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.request.ResolveRequest;
import pw.stamina.plugins.relations.resolvers.RelationResolver;
import pw.stamina.plugins.relations.select.RelationSelectorService;

import java.util.List;

/**
 * This class handles registration of
 * {@link RelationResolver resolvers}, and acts as an
 * entry point to find {@link Relation relations} to
 * {@link Entity entities}.
 */
public interface RelationManager {

    /**
     * Finds a {@link Relation relation} to the specified
     * {@link Entity entity} in a {@link ResolutionContext#ATTACK
     * attack} context. If no relation could be resolved a
     * {@link Relation#UNRECOGNIZED} relation is returned instead.
     *
     * @param entity the specified entity getting the relation to
     * @return the resolved relation, or {@link Relation#UNRECOGNIZED}
     * if none is found.
     */
    Relation findAttackRelation(Entity entity);

    /**
     * Finds a {@link Relation relation} to the specified
     * {@link Entity entity} in a {@link ResolutionContext#RENDER
     * render} context. If no relation could be resolved a
     * {@link Relation#UNRECOGNIZED} relation is returned instead.
     *
     * @param entity the specified entity getting the relation to
     * @return the resolved relation, or {@link Relation#UNRECOGNIZED}
     * if none is found.
     */
    Relation findRenderRelation(Entity entity);

    //TODO: Javadoc
    Relation findRelation(ResolveRequest request);

    /**
     * Registers the specified <tt>resolver</tt> into a {@link List}
     * of resolvers, at the appropriate index according to its {@link
     * RelationResolver#getPriority()}. If the registration is successful
     * the {@link RelationSelectorService#notifyResolverChange(RelationResolver)}
     * must be called with the specified <tt>resolver</tt>.
     *
     * @param resolver the resolver to register
     * @return <tt>true</tt> if the resolver was not already registered,
     * and if the resolver was successfully registered, otherwise
     * returns <tt>false</tt>
     */
    boolean registerResolver(RelationResolver resolver);

    /**
     * Unregisters a <tt>resolver</tt> from the list of resolvers. If the
     * <tt>resolver</tt> was successfully unregistered the {@link
     * RelationSelectorService#notifyResolverChange(RelationResolver)}
     * method must be called with the specified <tt>resolver</tt>.
     *
     * @param resolver the resolver to unregister
     * @return <tt>true</tt> if the resolver was registered, and
     * successfully unregistered, otherwise returns <tt>false</tt>
     */
    boolean unregisterResolver(RelationResolver resolver);

    /**
     * Finds an unmodifiable {@link List} of resolvers.
     *
     * @return an unmodifiable {@link List} of resolvers
     */
    List<RelationResolver> findResolvers();

    /**
     * Registers an a <tt>processor</tt> to the list of
     * processors. Processors are kept in the order they
     * are registered.
     *
     * @param processor the processor to register
     * @return <tt>true</tt> if the processor was not already registered,
     * and if the processor was successfully registered, otherwise
     * returns <tt>false</tt>
     */
    boolean registerProcessor(ResolvedRelationProcessor processor);

    /**
     * Unregisters a <tt>processor</tt> from the list of processors.
     *
     * @param processor the processor to unregister
     * @return <tt>true</tt> if the processor was registered, and
     * successfully unregistered, otherwise returns <tt>false</tt>
     */
    boolean unregisterProcessor(ResolvedRelationProcessor processor);

    /**
     * Finds an unmodifiable {@link List} of processors.
     *
     * @return an unmodifiable {@link List} of processors
     */
    List<ResolvedRelationProcessor> findProcessors();
}
