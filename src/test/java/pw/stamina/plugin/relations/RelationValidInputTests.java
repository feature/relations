package pw.stamina.plugin.relations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public final class RelationValidInputTests {

    @Parameters(name = "Relations.from({0}) == {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"friendly", Relation.FRIENDLY},
                {"fRiEnDlY", Relation.FRIENDLY},
                {"FrIeNdLy", Relation.FRIENDLY},
                {"FRIENDLY", Relation.FRIENDLY},


                {"passive", Relation.PASSIVE},
                {"pAsSiVe", Relation.PASSIVE},
                {"PaSsIvE", Relation.PASSIVE},
                {"PASSIVE", Relation.PASSIVE},

                {"neutral", Relation.NEUTRAL},
                {"nEuTrAl", Relation.NEUTRAL},
                {"NeUtRaL", Relation.NEUTRAL},
                {"NEUTRAL", Relation.NEUTRAL},

                {"hostile", Relation.HOSTILE},
                {"hOsTiLe", Relation.HOSTILE},
                {"HoStIlE", Relation.HOSTILE},
                {"HOSTILE", Relation.HOSTILE},

                {"player", Relation.PLAYER},
                {"pLaYeR", Relation.PLAYER},
                {"PlAyEr", Relation.PLAYER},
                {"PLAYER", Relation.PLAYER},

                {"enemy", Relation.ENEMY},
                {"eNeMy", Relation.ENEMY},
                {"EnEmY", Relation.ENEMY},
                {"ENEMY", Relation.ENEMY},

                {"ignored", Relation.IGNORED},
                {"iGnOrEd", Relation.IGNORED},
                {"IgNoReD", Relation.IGNORED},
                {"IGNORED", Relation.IGNORED},

                {"unrecognized", Relation.UNRECOGNIZED},
                {"uNrEcOgNiZeD", Relation.UNRECOGNIZED},
                {"UnReCoGnIzEd", Relation.UNRECOGNIZED},
                {"UNRECOGNIZED", Relation.UNRECOGNIZED},
        });
    }

    private final String input;
    private final Relation expected;

    public RelationValidInputTests(String input,
                                   Relation expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void from_knownNonNullInput_shouldReturnExpectedValueFromString() {
        Optional<Relation> from = Relation.from(input);

        if (!from.isPresent()) {
            fail("Relation.from returned an empty Optional");
        }

        Relation relation = from.get();
        assertThat(relation, is(expected));
    }
}
