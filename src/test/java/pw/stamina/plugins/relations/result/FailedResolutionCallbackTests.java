package pw.stamina.plugins.relations.result;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class FailedResolutionCallbackTests {

    private static ResolutionCallback failed;

    @BeforeClass
    public static void setupFailed() {
        failed = ResolutionCallback.failed();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getResult_shouldThrowUnsupportedOperationException() {
        failed.getResult();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getNestedResolveTarget_shouldThrowUnsupportedOperationException() {
        failed.getNestedResolveTarget();
    }

    @Test
    public void getType_shouldReturnFailed() {
        assertThat(
                failed.getType(),
                is(ResolutionCallbackType.FAILED));
    }
}
