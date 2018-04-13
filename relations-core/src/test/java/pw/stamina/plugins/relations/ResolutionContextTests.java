package pw.stamina.plugins.relations;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.equalToIgnoringCase;

public final class ResolutionContextTests {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void name_shouldReturnSameValueAsSpecified() {
        String name = "dummy";

        ResolutionContext context = ResolutionContext.getInstance(name);
        Assert.assertThat(context.name(), equalToIgnoringCase(name));
    }

    @Test
    public void getInstance_nullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("name");

        ResolutionContext.getInstance(null);
    }
}
