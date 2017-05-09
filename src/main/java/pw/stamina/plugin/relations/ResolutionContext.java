/*
 * Copyright Abstraction 2017
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package pw.stamina.plugin.relations;

import com.google.common.base.Strings;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class defines the context for when a
 * {@link Relation} is requested to be resolved.
 *
 * This class is instance controlled using the
 * {@link #getInstance(String)} method.
 */
public final class ResolutionContext {
    private static final Map<String, ResolutionContext>
            INSTANCES = new ConcurrentHashMap<>();

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
     * Returns the <tt>name</tt> of this context.
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
    public static ResolutionContext getInstance(String name) {
        if (Strings.nullToEmpty(name).trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "name must not be null or empty");
        }

        name = normalizeName(name);
        return INSTANCES.computeIfAbsent(name, ResolutionContext::new);
    }

    private static String normalizeName(String name) {
        return name.toLowerCase(Locale.ROOT);
    }
}
