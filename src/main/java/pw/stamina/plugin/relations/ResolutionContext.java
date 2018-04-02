package pw.stamina.plugin.relations;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A {@code ResolutionContext} defines the context in which a
 * {@link Relation} is being resolved. This class provides two
 * common default contexts, {@link #ATTACK} and {@link #RENDER}.
 * <p>
 *
 * If you want to create your own context, you may do so by using
 * the {@link #getInstance(String) getInstance} method. Names are
 * case insensitive, meaning {@literal test} and {@literal TEST} will
 * return the exact same context object.
 *
 * @implSpec this class is instance controlled using the {@link #getInstance(String)} method
 */
public final class ResolutionContext {
    private static final Map<String, ResolutionContext> INSTANCES = new ConcurrentHashMap<>();

    /**
     * The {@link Relation} is requested in
     * regards attacking the entity.
     */
    public static final ResolutionContext ATTACK = getInstance("attack");
    /**
     * The {@link Relation} is requested in
     * regards to rendering.
     */
    public static final ResolutionContext RENDER = getInstance("render");

    private final String name;

    private ResolutionContext(String name) {
        this.name = name;
    }

    /**
     * Returns the {@code name} of this context.
     *
     * @return the name of this context
     */
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name();
    }

    //TODO: Javadoc
    public static ResolutionContext getInstance(String name)
            throws IllegalArgumentException {
        Objects.requireNonNull(name, "name");

        name = normalizeName(name);
        return INSTANCES.computeIfAbsent(name, ResolutionContext::new);
    }

    private static String normalizeName(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }
}
