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

package pw.stamina.plugin.relations.request;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pw.stamina.euclid.traits.Named;
import pw.stamina.minecraftapi.entity.Entity;
import pw.stamina.plugin.relations.ResolutionContext;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public final class ResolveRequestTest {

    @Mock
    private Entity entity;

    private static final ResolutionContext DUMMY_CONTEXT =
            ResolutionContext.getInstance("dummy");

    @Mock
    private Named requester;

    @Before
    public void setupRequester() {
        Mockito.when(requester.getName()).thenReturn("dummy_requester");
    }

    @Test
    public void assertAnonymousAndIdentifiedAreSameImplementationTest() {
        ResolveRequest anonymous = ResolveRequest.anonymous(entity, DUMMY_CONTEXT);
        ResolveRequest identified = ResolveRequest.identified(entity, DUMMY_CONTEXT, requester);

        assertSame(anonymous.getClass(), identified.getClass());
    }

    @Test(expected = NullPointerException.class)
    public void anonymousEntityNullInputTest() {
        ResolveRequest.anonymous(null, DUMMY_CONTEXT);
    }

    @Test(expected = NullPointerException.class)
    public void anonymousContextNullInputTest() {
        ResolveRequest.anonymous(entity, null);
    }

    @Test(expected = NullPointerException.class)
    public void identifiedEntityNullInputTest() {
        ResolveRequest.identified(null, DUMMY_CONTEXT, requester);
    }

    @Test(expected = NullPointerException.class)
    public void identifiedContextNullInputTest() {
        System.out.println(requester);
        System.out.println(requester.getName());
        ResolveRequest.identified(entity, null, requester);
    }

    @Test(expected = NullPointerException.class)
    public void identifiedRequesterNullInputTest() {
        ResolveRequest.identified(entity, DUMMY_CONTEXT, null);
    }

    @Test
    public void assertAnonymousRequestIsEmptyOptional() {
        ResolveRequest request = ResolveRequest.anonymous(entity, DUMMY_CONTEXT);

        assertThat(request.requester(), is(Optional.empty()));
    }

    @Test
    public void assertIdentifiedRequestIsSameAsSpecifiedRequesterTest() {
        ResolveRequest request = ResolveRequest.identified(entity, DUMMY_CONTEXT, requester);
        Named requester = request.requester().get();

        assertThat(requester, is(this.requester));
    }
}
