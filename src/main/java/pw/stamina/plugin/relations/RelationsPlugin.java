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

import com.github.zafarkhaja.semver.Version;
import com.google.inject.Inject;
import pw.stamina.euclid.plugin.AbstractPlugin;
import pw.stamina.euclid.plugin.PluginLoadException;

import java.util.Objects;

//TODO: Javadoc
public final class RelationsPlugin extends AbstractPlugin {
    private final Version minecraftVersion;
    private final RelationManager relationManager;

    @Inject
    public RelationsPlugin(Version minecraftVersion,
                           RelationManager relationManager) {
        Objects.requireNonNull(minecraftVersion, "minecraftVersion");
        Objects.requireNonNull(relationManager, "relationManager");

        this.minecraftVersion = minecraftVersion;
        this.relationManager = relationManager;
    }

    @Override
    protected void onLoad() throws PluginLoadException {
        super.onLoad();
    }

    public RelationManager getRelationManager() {
        return relationManager;
    }
}
