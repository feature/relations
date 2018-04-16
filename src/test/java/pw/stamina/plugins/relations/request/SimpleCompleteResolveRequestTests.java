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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.stamina.plugins.relations.ResolvedRelationProcessor;
import pw.stamina.plugins.relations.resolvers.RelationResolver;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public final class SimpleCompleteResolveRequestTests {

    @Mock private ResolveRequest request;
    private List<RelationResolver> resolvers = Collections.emptyList();
    private List<ResolvedRelationProcessor> processors = Collections.emptyList();

    private SimpleCompleteResolveRequest completeRequest;

    @Before
    public void setupCompleteRequest() {
        completeRequest = new SimpleCompleteResolveRequest(
                request, resolvers, processors);
    }

    @Test
    public void request_shouldReturnSameAsRequestSpecifiedInConstructor() {
        assertThat(completeRequest.request(), is(request));
    }

    @Test
    public void resolvers_shouldReturnSameAsResolversSpecifiedInConstructor() {
        assertThat(completeRequest.resolvers(), is(resolvers));
    }

    @Test
    public void processors_shouldReturnSameAsProcessorsSpecifiedInConstructor() {
        assertThat(completeRequest.processors(), is(processors));
    }
}
