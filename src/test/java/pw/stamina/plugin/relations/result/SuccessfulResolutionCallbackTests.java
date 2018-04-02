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
