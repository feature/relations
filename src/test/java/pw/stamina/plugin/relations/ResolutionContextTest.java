package pw.stamina.plugin.relations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
//TODO: Rename to a more accurate test name
public final class ResolutionContextTest {

    @Parameters(name = "ResolutionContext.getInstance({0}) == {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"attack", ResolutionContext.ATTACK},
                {"aTtAcK", ResolutionContext.ATTACK},
                {"AtTaCk", ResolutionContext.ATTACK},
                {"AtTaCk", ResolutionContext.ATTACK},

                {"render", ResolutionContext.RENDER},
                {"rEnDeR", ResolutionContext.RENDER},
                {"ReNdEr", ResolutionContext.RENDER},
                {"RENDER", ResolutionContext.RENDER},

                {"test", TEST_CONTEXT},
                {"tEsT", TEST_CONTEXT},
                {"TeSt", TEST_CONTEXT},
                {"TEST", TEST_CONTEXT},
        });
    }

    private static final ResolutionContext TEST_CONTEXT = ResolutionContext.getInstance("test");

    private final String input;
    private final ResolutionContext expected;

    public ResolutionContextTest(String input,
                                 ResolutionContext expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void getInstance_knownNonNullInput_shouldReturnExpectedValueFromString() {
        ResolutionContext context = ResolutionContext.getInstance(input);

        assertThat(context, is(expected));
    }
}
