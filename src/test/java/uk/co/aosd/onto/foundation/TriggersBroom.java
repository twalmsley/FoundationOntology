package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Disaggregated;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.reference.EventServicesImpl;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.EventServices;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * An example to show object composition and how it changes over time.
 *
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

    private static final OntologyServices svc = new OntologyServicesImpl();
    private static final EventServices ev = new EventServicesImpl();

    private static final Built LIFE_START = ev.createBuiltEvent(randStr(), Instant.parse("2024-01-01T12:00:00.00Z"), Instant.parse("2024-01-01T12:00:00.00Z"));
    private static final Scrapped UNKNOWN_END = ev.createScrappedEvent(randStr(), null, null);
    private static final Built ASSEMBLY_START = ev.createBuiltEvent(randStr(), Instant.parse("2024-11-01T12:00:00.00Z"),
        Instant.parse("2024-11-01T12:00:00.00Z"));
    private static final Scrapped ASSEMBLY_END = ev.createScrappedEvent(randStr(), null, null);
    private static final Aggregated AGGREGATED_EVENT = null;
    private static final Disaggregated DISAGGREGATED_EVENT = null;

    @Test
    public void test() {
        //
        // Create the parts
        //
        final var broomHandle = new BroomHandle(randStr(), LIFE_START, UNKNOWN_END);
        final var broomHead = new BroomHead(randStr(), LIFE_START, UNKNOWN_END);
        final var bristles = new Bristles(randStr(), LIFE_START, UNKNOWN_END);
        final var broomBracket = new BroomBracket(randStr(), LIFE_START, UNKNOWN_END);

        // Gather the parts into an Agglomerate (not really necessary, this just shows
        // what an Agglomerate is)
        final var parts = svc.createAgglomerate(randStr(), Set.of(broomBracket, bristles, broomHead, broomHandle), AGGREGATED_EVENT, DISAGGREGATED_EVENT);

        // Assemble the broom composite from the set of parts
        final var broom = assembleBroom(parts);

        //
        // Check the composition is correct.
        //
        assertSame(broom.handle(), broomHandle);
        assertSame(broom.headWithBracketAssembly().bracket(), broomBracket);
        assertSame(broom.headWithBracketAssembly().headAssembly().bristles(), bristles);
        assertSame(broom.headWithBracketAssembly().headAssembly().head(), broomHead);

        // The bristles are worn out after much use, so replace them
        final var activityFrom = ev.createStartedEvent(randStr(), Instant.parse("2024-11-11T12:00:00.00Z"), Instant.parse("2024-11-11T12:00:00.00Z"));
        final var activityTo = ev.createStoppedEvent(randStr(), Instant.parse("2024-11-11T12:30:00.00Z"), Instant.parse("2024-11-11T12:30:00.00Z"));
        final var activityRecord = replaceBristles(broom, new Bristles(randStr(), LIFE_START, UNKNOWN_END), activityFrom, activityTo);

        JsonUtils.dumpJson(activityRecord);

        assertSame(activityFrom, activityRecord.beginning());
        assertSame(activityTo, activityRecord.ending());

        assertSame(activityFrom, activityRecord.oldBroom().ending());
        assertSame(activityFrom, activityRecord.oldBroom().headWithBracketAssembly().headAssembly().bristles().ending());
        assertSame(activityFrom, activityRecord.oldBristles().ending());
        assertSame(activityFrom, activityRecord.oldHeadAssembly().ending());
        assertSame(activityFrom, activityRecord.oldHeadWithBracketAssembly().ending());

        assertSame(activityFrom, activityRecord.newBroom().beginning());
        assertSame(UNKNOWN_END, activityRecord.newBroom().ending());

        assertSame(broomBracket, activityRecord.newBroom().headWithBracketAssembly().bracket());
        assertSame(broomHead, activityRecord.newBroom().headWithBracketAssembly().headAssembly().head());
        assertSame(broomHandle, activityRecord.newBroom().handle());
    }

    /**
     * Replace the worn out bristles, close off the lifetimes of the old assemblies
     * and old bristles, * create new assemblies and a new broom.
     *
     * @param broom
     *            The Broom
     * @param bristles
     *            The new Bristles
     * @param activityStart
     *            Event
     * @param activityEnd
     *            Event
     * @return ReplaceBristlesActivity
     */
    private ReplaceBristlesActivity replaceBristles(final Broom broom, final Bristles bristles, final Started activityStart, final Stopped activityEnd) {

        // Set the ending for the old headAssembly
        final var headAssembly = broom.headWithBracketAssembly().headAssembly();
        final Bristles oldBristles = new Bristles(headAssembly.bristles().identifier(), headAssembly.bristles().beginning(), UNKNOWN_END);
        final var oldHeadAssembly = new BroomHeadAssembly(headAssembly.identifier(), headAssembly.head(), oldBristles, headAssembly.beginning(),
            UNKNOWN_END);

        // Set the ending for the old headAndBracketAssembly
        final var headAndBracketAssembly = broom.headWithBracketAssembly();
        final var oldHeadWithBracketAssembly = new BroomHeadWithBracketAssembly(headAndBracketAssembly.identifier(), oldHeadAssembly,
            headAndBracketAssembly.bracket(), headAndBracketAssembly.beginning(), UNKNOWN_END);

        // Set the ending for the old Broom.
        final var oldBroom = new Broom(randStr(), broom.handle(), oldHeadWithBracketAssembly, broom.beginning(), UNKNOWN_END);

        // Create the updated broom
        final var updatedHeadAssembly = fitBristles(randStr(), headAssembly.head(), bristles, ASSEMBLY_START, UNKNOWN_END);
        final var updatedHeadAndBracketAssembly = fitBracket(randStr(), updatedHeadAssembly, headAndBracketAssembly.bracket(), ASSEMBLY_START, UNKNOWN_END);
        final var updatedBroom = fitHandle(randStr(), oldBroom.handle(), updatedHeadAndBracketAssembly, ASSEMBLY_START, UNKNOWN_END);

        return new ReplaceBristlesActivity(randStr(), "Replace bristles", oldBroom, updatedBroom, oldBristles, oldHeadAssembly, oldHeadWithBracketAssembly,
            activityStart, activityEnd);
    }

    /**
     * Convert an Agglomerate of broom parts into a composition which is a broom.
     *
     * @param parts
     *            An Agglomerate
     * @return Broom
     */
    private Broom assembleBroom(final Agglomerate parts) {
        //
        // Get the parts from the agglomerate, then build the composite Broom
        //
        final var broomHandle = (BroomHandle) parts.parts().stream().filter(x -> x instanceof BroomHandle).findFirst().orElse(null);
        final var broomHead = (BroomHead) parts.parts().stream().filter(x -> x instanceof BroomHead).findFirst().orElse(null);
        final var bristles = (Bristles) parts.parts().stream().filter(x -> x instanceof Bristles).findFirst().orElse(null);
        final var broomBracket = (BroomBracket) parts.parts().stream().filter(x -> x instanceof BroomBracket).findFirst().orElse(null);

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
        final BroomHeadWithBracketAssembly broomHeadWithBracketAssembly, final Built beginning,
        final Scrapped ending) {
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
        final BroomBracket broomBracket, final Built beginning, final Scrapped ending) {
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
        final Built beginning, final Scrapped ending) {
        return new BroomHeadAssembly(id, head, bristles, beginning, ending);
    }

    private static int id;

    private static String randStr() {
        return Integer.toString(id++);
    }

}

record Broom(String identifier, BroomHandle handle, BroomHeadWithBracketAssembly headWithBracketAssembly, Built beginning, Scrapped ending)
    implements Individual<Built, Scrapped> {
}

record BroomHeadAssembly(String identifier, BroomHead head, Bristles bristles, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record BroomHeadWithBracketAssembly(String identifier, BroomHeadAssembly headAssembly, BroomBracket bracket,
    Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record BroomHandle(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record BroomHead(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record Bristles(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record BroomBracket(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record ReplaceBristlesActivity(String identifier, String actionsDescription, Broom oldBroom, Broom newBroom, Bristles oldBristles,
    BroomHeadAssembly oldHeadAssembly, BroomHeadWithBracketAssembly oldHeadWithBracketAssembly, Started beginning, Stopped ending)
    implements Individual<Started, Stopped> {
}