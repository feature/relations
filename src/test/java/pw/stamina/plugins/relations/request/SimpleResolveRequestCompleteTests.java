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

package pw.stamina.plugins.relations.request;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

public final class SimpleResolveRequestCompleteTests {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private SimpleResolveRequest request;

    private List<RelationResolver> resolvers = Collections.emptyList();
    private List<ResolvedRelationProcessor> processors = Collections.emptyList();

    @Before
    public void setupRequest() {
        request = new SimpleResolveRequest(null, null, null);
    }

    @Test
    public void complete_resolversNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("resolvers");

        request.complete(null, processors);
    }

    @Test
    public void complete_processorsNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("processors");

        request.complete(resolvers, null);
    }

    @Test
    public void complete_validNonNullInputs_shouldCreateCompleteResolveRequest() {
        CompleteResolveRequest complete = request.complete(resolvers, processors);

        assertThat(complete, isA(CompleteResolveRequest.class));
    }
}
