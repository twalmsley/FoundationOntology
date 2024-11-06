package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * An example to show object composition and how it changes over time.
 * <p>
 * Trigger's broom consists of some bristles, a broom head, a broom handle, and
 * a bracket to connect the handle to the head. In this example we construct a
 * broom and replace some or all of its parts.
 * </p>
 *
 * <p>
 * The Broom class represents a composition of the BroomHandle and the
 * BroomHeadWithBracketAssembly, which then also has nested parts. In this
 * example the composition reflects the way the broom was constructed, but it is
 * also possible to use a flatter structure in which the broom has the four
 * parts as immediate child properties. In this second case it may be necessary
 * to construct the broom in one go, or to make the parts Optional, however this
 * would not capture the 4D aspects of the object relationships correctly.
 * </p>
 *
 * @author Tony Walmsley
 */
public class TriggersBroom {

    private static final String BROOM_HANDLE_1_ID = "Broom Handle 1";
    private static final String BROOM_HEAD_1_ID = "Broom Head 1";
    private static final String BRISTLES_1_ID = "Bristles 1";
    private static final String BROOM_BRACKET_1_ID = "Broom Bracket 1";
    private static final String BROOM_HEAD_ASSEMBLY_1_ID = "Broom Head Assembly 1";
    private static final String BROOM_HEAD_WITH_BRACKET_ASSEMBLY_1_ID = "Broom Head Assembly With Bracket 1";
    private static final String BROOM_1_ID = "Broom 1";
    private static final Optional<Instant> LIFE_START = Optional.of(Instant.parse("2024-01-01T12:00:00.00Z"));
    private static final Optional<Instant> UNKNOWN_END = Optional.empty();
    private static final Optional<Instant> ASSEMBLY_START = Optional.of(Instant.parse("2024-11-01T12:00:00.00Z"));
    private static final Optional<Instant> ASSEMBLY_END = Optional.empty();

    @Test
    public void test() {
        //
        // Create the parts
        //
        final var broomHandle = new BroomHandle(BROOM_HANDLE_1_ID, LIFE_START, UNKNOWN_END);
        final var broomHead = new BroomHead(BROOM_HEAD_1_ID, LIFE_START, UNKNOWN_END);
        final var bristles = new Bristles(BRISTLES_1_ID, LIFE_START, UNKNOWN_END);
        final var broomBracket = new BroomBracket(BROOM_BRACKET_1_ID, LIFE_START, UNKNOWN_END);

        // Assemble the broom
        final var broom = assembleBroom(broomHead, bristles, broomBracket, broomHandle);

        //
        // Check the composition is correct.
        //
        assertSame(broom.handle(), broomHandle);
        assertSame(broom.headWithBracketAssembly().bracket(), broomBracket);
        assertSame(broom.headWithBracketAssembly().headAssembly().bristles(), bristles);
        assertSame(broom.headWithBracketAssembly().headAssembly().head(), broomHead);
    }

    private Broom assembleBroom(final BroomHead broomHead, final Bristles bristles, final BroomBracket broomBracket,
        final BroomHandle broomHandle) {
        //
        // Fit the bristles in the head.
        //
        final var broomHeadAssembly = fitBristles(BROOM_HEAD_ASSEMBLY_1_ID, broomHead, bristles, ASSEMBLY_START,
            ASSEMBLY_END);

        //
        // Attach the bracket to the head.
        //
        final var broomHeadWithBracketAssembly = fitBracket(BROOM_HEAD_WITH_BRACKET_ASSEMBLY_1_ID, broomHeadAssembly,
            broomBracket, ASSEMBLY_START, ASSEMBLY_END);

        //
        // Attach the handle to the bracket to complete the broom.
        //
        return fitHandle(BROOM_1_ID, broomHandle, broomHeadWithBracketAssembly, ASSEMBLY_START,
            ASSEMBLY_END);
    }

    /**
     * Ideally this would also set the beginning event, but this is a simple
     * example.
     *
     * @param id
     *            String
     * @param broomHandle
     *            BroomHandle
     * @param broomHeadWithBracketAssembly
     *            BroomHeadWithBracketAssembly
     * @return Broom
     */
    private Broom fitHandle(final String id, final BroomHandle broomHandle,
        final BroomHeadWithBracketAssembly broomHeadWithBracketAssembly, final Optional<Instant> beginning,
        final Optional<Instant> ending) {
        return new Broom(id, broomHandle, broomHeadWithBracketAssembly, beginning, ending);
    }

    /**
     * Ideally this would set the beginning event, but this is a simple example.
     *
     * @param id
     *            String
     * @param broomHeadAssembly
     *            BroomHeadAssembly
     * @param broomBracket
     *            BroomBracket
     * @return BroomHeadWithBracketAssembly
     */
    private static BroomHeadWithBracketAssembly fitBracket(final String id, final BroomHeadAssembly broomHeadAssembly,
        final BroomBracket broomBracket, final Optional<Instant> beginning, final Optional<Instant> ending) {
        return new BroomHeadWithBracketAssembly(id, broomHeadAssembly, broomBracket, beginning, ending);
    }

    /**
     * Ideally this would also set the beginning event, but I'm keeping this example
     * simple.
     *
     * @param id
     *            String
     * @param head
     *            BroomHead
     * @param bristles
     *            Bristles
     * @return BroomHeadAssembly
     */
    private static BroomHeadAssembly fitBristles(final String id, final BroomHead head, final Bristles bristles,
        final Optional<Instant> beginning, final Optional<Instant> ending) {
        return new BroomHeadAssembly(id, head, bristles, beginning, ending);
    }

}

record Broom(String identifier, BroomHandle handle, BroomHeadWithBracketAssembly headWithBracketAssembly,
    Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}

record BroomHeadAssembly(String identifier, BroomHead head, Bristles bristles, Optional<Instant> beginning,
    Optional<Instant> ending) implements Individual {
}

record BroomHeadWithBracketAssembly(String identifier, BroomHeadAssembly headAssembly, BroomBracket bracket,
    Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}

record BroomHandle(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}

record BroomHead(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}

record Bristles(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}

record BroomBracket(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}