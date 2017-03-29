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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SuppressWarnings("ConstantConditions")
public final class RelationTest {

    @Test
    public void testFriendlyFrom() {
        assertEquals(Relation.from("friendly").get(), Relation.FRIENDLY);
        assertEquals(Relation.from("fRiEnDlY").get(), Relation.FRIENDLY);
        assertEquals(Relation.from("FrIeNdLy").get(), Relation.FRIENDLY);
        assertEquals(Relation.from("FRIENDLY").get(), Relation.FRIENDLY);
    }

    @Test
    public void testPassiveFrom() {
        assertEquals(Relation.from("passive").get(), Relation.PASSIVE);
        assertEquals(Relation.from("pAsSiVe").get(), Relation.PASSIVE);
        assertEquals(Relation.from("PaSsIvE").get(), Relation.PASSIVE);
        assertEquals(Relation.from("PASSIVE").get(), Relation.PASSIVE);
    }

    @Test
    public void testNeutralFrom() {
        assertEquals(Relation.from("neutral").get(), Relation.NEUTRAL);
        assertEquals(Relation.from("nEuTrAl").get(), Relation.NEUTRAL);
        assertEquals(Relation.from("NeUtRaL").get(), Relation.NEUTRAL);
        assertEquals(Relation.from("NEUTRAL").get(), Relation.NEUTRAL);
    }

    @Test
    public void testHostileFrom() {
        assertEquals(Relation.from("hostile").get(), Relation.HOSTILE);
        assertEquals(Relation.from("hOsTiLe").get(), Relation.HOSTILE);
        assertEquals(Relation.from("HoStIlE").get(), Relation.HOSTILE);
        assertEquals(Relation.from("HOSTILE").get(), Relation.HOSTILE);
    }

    @Test
    public void testPlayerFrom() {
        assertEquals(Relation.from("player").get(), Relation.PLAYER);
        assertEquals(Relation.from("pLaYeR").get(), Relation.PLAYER);
        assertEquals(Relation.from("PlAyEr").get(), Relation.PLAYER);
        assertEquals(Relation.from("PLAYER").get(), Relation.PLAYER);
    }

    @Test
    public void testIgnoredFrom() {
        assertEquals(Relation.from("ignored").get(), Relation.IGNORED);
        assertEquals(Relation.from("iGnOrEd").get(), Relation.IGNORED);
        assertEquals(Relation.from("IgNoReD").get(), Relation.IGNORED);
        assertEquals(Relation.from("IGNORED").get(), Relation.IGNORED);
    }

    @Test
    public void testUnrecognizedFrom() {
        assertEquals(Relation.from("unrecognized").get(), Relation.UNRECOGNIZED);
        assertEquals(Relation.from("uNrEcOgNiZeD").get(), Relation.UNRECOGNIZED);
        assertEquals(Relation.from("UnReCoGnIzEd").get(), Relation.UNRECOGNIZED);
        assertEquals(Relation.from("UNRECOGNIZED").get(), Relation.UNRECOGNIZED);
    }

    @Test
    public void testFailedFrom() {
        assertFalse(Relation.from("Blah").isPresent());
        assertFalse(Relation.from("bLaH").isPresent());
        assertFalse(Relation.from("foo").isPresent());
        assertFalse(Relation.from("bar").isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void testFromNullInput() {
        Relation.from(null);
    }
}
