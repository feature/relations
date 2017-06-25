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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
//TODO: Rename to a more accurate test name
public final class ResolutionContextTest {

    @Parameters(name = "ResolutionContext.getInstance({0}) == {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"attack", ResolutionContext.ATTACK},
                {"aTtAcK", ResolutionContext.ATTACK},
                {"AtTaCk", ResolutionContext.ATTACK},
                {"AtTaCk", ResolutionContext.ATTACK},

                {"render", ResolutionContext.RENDER},
                {"rEnDeR", ResolutionContext.RENDER},
                {"ReNdEr", ResolutionContext.RENDER},
                {"RENDER", ResolutionContext.RENDER},

                {"test", TEST_CONTEXT},
                {"tEsT", TEST_CONTEXT},
                {"TeSt", TEST_CONTEXT},
                {"TEST", TEST_CONTEXT},
        });
    }

    private static final ResolutionContext TEST_CONTEXT = ResolutionContext.getInstance("test");

    private final String input;
    private final ResolutionContext expected;

    public ResolutionContextTest(String input,
                                 ResolutionContext expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void getInstance_knownNonNullInput_shouldReturnExpectedValueFromString() {
        ResolutionContext context = ResolutionContext.getInstance(input);

        assertThat(context, is(expected));
    }
}
