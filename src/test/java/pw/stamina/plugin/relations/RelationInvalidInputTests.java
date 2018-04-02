package pw.stamina.plugin.relations;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class RelationInvalidInputTests {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void from_unknownRelationNameNonNullInput_shouldReturn() {
        assertThat(Relation.from("Blah"), is(Optional.empty()));
        assertThat(Relation.from("bLaH"), is(Optional.empty()));
        assertThat(Relation.from("foo"), is(Optional.empty()));
        assertThat(Relation.from("bar"), is(Optional.empty()));
    }

    @Test
    public void from_nullInput_shouldThrowException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("input");

        Relation.from(null);
    }
}
