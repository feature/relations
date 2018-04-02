package pw.stamina.plugins.relations.result;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.stamina.minecraftapi.entity.Entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public final class NestedResolveResolutionCallbackTests {

    @Mock private Entity entity;

    private ResolutionCallback nestedResolve;

    @Before
    public void setupNestedResolve() {
        nestedResolve = NestedResolveResolutionCallback.of(entity);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getResult_shouldThrowUnsupportedOperationException() {
        nestedResolve.getResult();
    }

    @Test
    public void getNestedResolveTarget_shouldReturnEntitySpecifiedInFactoryMethod() {
        assertThat(nestedResolve.getNestedResolveTarget(), is(entity));
    }

    @Test
    public void getType_shouldReturnFailed() {
        assertThat(
                nestedResolve.getType(),
                is(ResolutionCallbackType.NESTED_RESOLVE));
    }
}
