package pw.stamina.plugins.relations;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * A relation is an entity classification, which indicates
 * how an entity generally should be treated.
 */
public enum Relation {
    /**
     * The entity is considered friendly.
     */
    FRIENDLY,
    /**
     * The entity is passive, and will not try to
     * attack the player.
     */
    PASSIVE,
    /**
     * The entity is not sure to attack the player,
     * but it may if provoked.
     */
    NEUTRAL,
    /**
     * The entity will try to attack the player,
     * regards of whether or not they have been
     * provoked.
     */
    HOSTILE,
    /**
     * Indicates that the entity is a player. This
     * is intended to clarify that the entity is a
     * player, without any other specific relation.
     */
    PLAYER,
    /**
     * The entity is explicitly declared by a
     * resolver to be considered an enemy.
     */
    ENEMY,
    /**
     * Indicates that the system should ignore
     * the entity entirely.
     */
    IGNORED,
    /**
     * A suitable relation was unable to be found for
     * the specific entity. Generally entities with
     * this relation should be ignored.
     */
    UNRECOGNIZED;

    /**
     * Retrieves a {@link Relation} case insensitive
     * by its {@link #name name}. The result in
     * wrapped in an {@link Optional} to clearly
     * indicate if no relation was found.
     *
     * @param input {@link #name} of the {@link Relation}
     *                           to retrieve
     * @return the result wrapped in an {@link Optional}
     * @throws NullPointerException if <tt>input</tt> is null
     */
    public static Optional<Relation> from(String input)
            throws NullPointerException {
        Objects.requireNonNull(input, "input");

        return Arrays.stream(Relation.values())
                .filter(relation -> relation.name().equalsIgnoreCase(input))
                .findAny();
    }
}
