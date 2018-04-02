package pw.stamina.plugin.relations.result;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public final class ResolveResolutionCallbackInputFactoryMethodsInvalidInputTests {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void nestedResolve_nullEntityInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("nestedResolveTarget");

        ResolutionCallback.nestedResolve(null);
    }

    @Test
    public void successful_nullRelationInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("relation");

        ResolutionCallback.success(null);
    }
}
