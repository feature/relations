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

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public final class ResolutionContextTest {

    @Test
    public void getInstanceAttackConstantTest() {
        String[] inputs = new String[]{
                "attack",
                "aTtAcK",
                "AtTaCk",
                "AtTaCk"};

        Stream.of(inputs).forEach(input ->
                assertEquals(String.format("'%s' did not resolve to the correct" +
                                "ATTACK ReoslutionContext instance", input),
                        ResolutionContext.ATTACK,
                        ResolutionContext.getInstance(input)));
    }

    @Test
    public void getInstanceRenderConstantTest() {
        String[] inputs = new String[]{
                "render",
                "rEnDeR",
                "ReNdEr",
                "RENDER"};

        Stream.of(inputs).forEach(input ->
                assertEquals(String.format("'%s' did not resolve to the correct" +
                                "RENDER ReoslutionContext instance", input),
                        ResolutionContext.RENDER,
                        ResolutionContext.getInstance(input)));
    }

    @Test
    public void getInstanceNewContextTest() {
        ResolutionContext testContext = ResolutionContext.getInstance("test");

        String[] inputs = new String[]{
                "test",
                "tEsT",
                "TeSt",
                "TEST"};

        Stream.of(inputs).forEach(input ->
                assertEquals(String.format("'%s' did not resolve to the correct" +
                                "test ReoslutionContext instance", input),
                        testContext, ResolutionContext.getInstance(input)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceEmptyInputFailTest() {
        ResolutionContext.getInstance("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceEmptyTrimmedInputFailTest() {
        ResolutionContext.getInstance("   ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceNullInputFailTest() {
        ResolutionContext.getInstance(null);
    }
}
