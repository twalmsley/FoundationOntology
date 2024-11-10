package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.OntologyServices;

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

    private static OntologyServices svc = new OntologyServicesImpl();

    private static final Event LIFE_START = svc.createEvent(randStr(), Instant.parse("2024-01-01T12:00:00.00Z"), Instant.parse("2024-01-01T12:00:00.00Z"));
    private static final Event UNKNOWN_END = svc.createEvent(randStr(), null, null);
    private static final Event ASSEMBLY_START = svc.createEvent(randStr(), Instant.parse("2024-11-01T12:00:00.00Z"), Instant.parse("2024-11-01T12:00:00.00Z"));
    private static final Event ASSEMBLY_END = svc.createEvent(randStr(), null, null);

    @Test
    public void test() {
        //
        // Create the parts
        //
        final var broomHandle = new BroomHandle(randStr(), LIFE_START, UNKNOWN_END);
        final var broomHead = new BroomHead(randStr(), LIFE_START, UNKNOWN_END);
        final var bristles = new Bristles(randStr(), LIFE_START, UNKNOWN_END);
        final var broomBracket = new BroomBracket(randStr(), LIFE_START, UNKNOWN_END);

        // Assemble the broom
        final var broom = assembleBroom(broomHead, bristles, broomBracket, broomHandle);

        //
        // Check the composition is correct.
        //
        assertSame(broom.handle(), broomHandle);
        assertSame(broom.headWithBracketAssembly().bracket(), broomBracket);
        assertSame(broom.headWithBracketAssembly().headAssembly().bristles(), bristles);
        assertSame(broom.headWithBracketAssembly().headAssembly().head(), broomHead);
    
        JsonUtils.dumpJson(broom);
    }

    private Broom assembleBroom(final BroomHead broomHead, final Bristles bristles, final BroomBracket broomBracket,
        final BroomHandle broomHandle) {
        //
        // Fit the bristles in the head.
        //
        final var broomHeadAssembly = fitBristles(randStr(), broomHead, bristles, ASSEMBLY_START,
            ASSEMBLY_END);

        //
        // Attach the bracket to the head.
        //
        final var broomHeadWithBracketAssembly = fitBracket(randStr(), broomHeadAssembly,
            broomBracket, ASSEMBLY_START, ASSEMBLY_END);

        //
        // Attach the handle to the bracket to complete the broom.
        //
        return fitHandle(randStr(), broomHandle, broomHeadWithBracketAssembly, ASSEMBLY_START,
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
        final BroomHeadWithBracketAssembly broomHeadWithBracketAssembly, final Event beginning,
        final Event ending) {
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
        final BroomBracket broomBracket, final Event beginning, final Event ending) {
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
        final Event beginning, final Event ending) {
        return new BroomHeadAssembly(id, head, bristles, beginning, ending);
    }

    private static int id;

    private static String randStr() {
        return Integer.toString(id++);
    }

}

record Broom(String identifier, BroomHandle handle, BroomHeadWithBracketAssembly headWithBracketAssembly,
    Event beginning, Event ending) implements Individual {
}

record BroomHeadAssembly(String identifier, BroomHead head, Bristles bristles, Event beginning,
    Event ending) implements Individual {
}

record BroomHeadWithBracketAssembly(String identifier, BroomHeadAssembly headAssembly, BroomBracket bracket,
    Event beginning, Event ending) implements Individual {
}

record BroomHandle(String identifier, Event beginning, Event ending) implements Individual {
}

record BroomHead(String identifier, Event beginning, Event ending) implements Individual {
}

record Bristles(String identifier, Event beginning, Event ending) implements Individual {
}

record BroomBracket(String identifier, Event beginning, Event ending) implements Individual {
}