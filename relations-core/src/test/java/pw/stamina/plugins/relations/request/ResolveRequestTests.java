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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugins.relations.ResolutionContext;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public final class ResolveRequestTests {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private static final ResolutionContext DUMMY_CONTEXT =
            ResolutionContext.getInstance("dummy");

    @Mock private Entity entity;
    @Mock private String requester;//TODO

    @Test
    public void anonymous_entityNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("entity");

        ResolveRequest.anonymous(null, DUMMY_CONTEXT);
    }

    @Test
    public void anonymous_contextNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("context");

        ResolveRequest.anonymous(entity, null);
    }

    @Test
    public void identified_entityNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("entity");

        ResolveRequest.identified(null, DUMMY_CONTEXT, requester);
    }

    @Test
    public void identified_contextNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("context");

        ResolveRequest.identified(entity, null, requester);
    }

    @Test
    public void identified_requesterNullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("requester");

        ResolveRequest.identified(entity, DUMMY_CONTEXT, null);
    }

    @Test
    public void anonymous_validArguments_shouldReturnResolveRequestWithNoRequester() {
        ResolveRequest request = ResolveRequest.anonymous(entity, DUMMY_CONTEXT);

        assertThat(request.requester(), is(Optional.empty()));
    }

    @Test
    public void identified_validArguments_shouldReturnResolveRequestRequesterSameAsSpecifiedRequesterName() {
        ResolveRequest request = ResolveRequest.identified(entity, DUMMY_CONTEXT, requester);

        assertThat(request.requester().get(), is(requester.getName()));
    }
}
