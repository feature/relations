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

package pw.stamina.plugin.relations.result;

import org.junit.Before;
import org.junit.Test;
import pw.stamina.plugin.relations.Relation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SuccessfulResolutionCallbackTests {

    private static ResolutionCallback successful;

    private Relation result = Relation.UNRECOGNIZED;

    @Before
    public void setupFailed() {
        successful = ResolutionCallback.success(result);
    }

    @Test
    public void getResult_shouldReturnSameResultAsSpecifiedInFactoryMethod() {
        assertThat(successful.getResult(), is(result));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getNestedResolveTarget_shouldThrowUnsupportedOperationException() {
        successful.getNestedResolveTarget();
    }

    @Test
    public void getType_shouldReturnFailed() {
        assertThat(
                successful.getType(),
                is(ResolutionCallbackType.SUCCESSFUL));
    }
}
