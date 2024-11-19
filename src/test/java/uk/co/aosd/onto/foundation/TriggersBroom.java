package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Assembled;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Disaggregated;
import uk.co.aosd.onto.events.Disassembled;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
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
 * <p>
 * See the HQDM book, section 13.2 Functional Object, ¶4.
 * </p>
 *
 * @author Tony Walmsley
 */
public class TriggersBroom {

    private static final Instant NOV_11TH_2024_1230 = Instant.parse("2024-11-11T12:30:00.00Z");
    private static final Instant NOV_11TH_2024_MIDDAY = Instant.parse("2024-11-11T12:00:00.00Z");
    private static final Instant NOV_1ST_2024_MIDDAY = Instant.parse("2024-11-01T12:00:00.00Z");
    private static final Instant JAN_1ST_2024_START = Instant.parse("2024-01-01T12:00:00.00Z");
    private static final OntologyServices svc = new OntologyServicesImpl();

    private static final Built LIFE_START = new Built(randStr(), JAN_1ST_2024_START, JAN_1ST_2024_START);
    private static final Disassembled UNKNOWN_DISASSEMBLY = new Disassembled(randStr(), null, null);
    private static final Scrapped UNKNOWN_SCRAPPING = new Scrapped(randStr(), null, null);
    private static final Assembled ASSEMBLY_START = new Assembled(randStr(), NOV_1ST_2024_MIDDAY, NOV_1ST_2024_MIDDAY);
    private static final Disassembled ASSEMBLY_END = new Disassembled(randStr(), null, null);
    private static final Aggregated AGGREGATED_EVENT = null;
    private static final Disaggregated DISAGGREGATED_EVENT = null;

    @Test
    public void test() throws JsonProcessingException {
        //
        // Create the parts
        //
        final var broomHandle = new BroomHandle(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var broomHead = new BroomHead(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var bristles = new Bristles(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var broomBracket = new BroomBracket(randStr(), LIFE_START, UNKNOWN_SCRAPPING);

        // Gather the parts into an Agglomerate (not really necessary, this just shows
        // what an Agglomerate is)
        final var parts = svc.createAgglomerate(randStr(), Set.of(broomBracket, bristles, broomHead, broomHandle), AGGREGATED_EVENT, DISAGGREGATED_EVENT);

        // Assemble the broom composite from the set of parts
        final var broom = assembleBroom(parts);

        //
        // Check the composition is correct.
        //
        assertSame(ASSEMBLY_START, broom.beginning());
        assertSame(ASSEMBLY_END, broom.ending());
        assertSame(broom.handle(), broomHandle);
        assertSame(broom.headWithBracketAssembly().bracket(), broomBracket);
        assertSame(broom.headWithBracketAssembly().headAssembly().bristles(), bristles);
        assertSame(broom.headWithBracketAssembly().headAssembly().head(), broomHead);

        // The bristles are worn out after much use, so replace them
        final var activityFrom = new Started(randStr(), NOV_11TH_2024_MIDDAY, NOV_11TH_2024_MIDDAY);
        final var activityTo = new Stopped(randStr(), NOV_11TH_2024_1230, NOV_11TH_2024_1230);
        final var activityRecord = replaceBristles(broom, new Bristles(randStr(), LIFE_START, UNKNOWN_SCRAPPING), activityFrom, activityTo);

        // Use the broom to sweep the road - note that we can't use the 'parts'
        // agglomerate since it is not an assembled broom, which illustrates the
        // difference between aggregations and compositions since the composition has
        // additional structure which makes it useable in its intended role.
        final var sweepResult = sweepRoad(activityRecord.newBroom());
        assertTrue(sweepResult);

        final var json = JsonUtils.writeJsonString(activityRecord);
        final var activityRecord2 = JsonUtils.readJsonString(json, ReplaceBristlesActivity.class);

        assertEquals(activityRecord, activityRecord2);

        assertSame(broom.beginning(), activityRecord.oldBroom().beginning());
        assertSame(activityFrom.from(), activityRecord.oldBroom().ending().from());
        assertSame(activityFrom.to(), activityRecord.oldBroom().ending().to());

        assertSame(broom.handle(), activityRecord.oldBroom().handle());
        assertSame(ASSEMBLY_START, activityRecord.oldBroom().headWithBracketAssembly().beginning());
        assertSame(activityFrom.from(), activityRecord.oldBroom().headWithBracketAssembly().ending().from());
        assertSame(activityFrom.to(), activityRecord.oldBroom().headWithBracketAssembly().ending().to());

        assertSame(ASSEMBLY_START, activityRecord.oldBroom().headWithBracketAssembly().headAssembly().beginning());
        assertSame(activityFrom.from(), activityRecord.oldBroom().headWithBracketAssembly().headAssembly().ending().from());
        assertSame(activityFrom.to(), activityRecord.oldBroom().headWithBracketAssembly().headAssembly().ending().to());

        assertSame(LIFE_START, activityRecord.oldBroom().headWithBracketAssembly().headAssembly().head().beginning());
        assertNull(activityRecord.oldBroom().headWithBracketAssembly().headAssembly().head().ending().from());
        assertNull(activityRecord.oldBroom().headWithBracketAssembly().headAssembly().head().ending().to());

        assertSame(LIFE_START, activityRecord.oldBroom().headWithBracketAssembly().headAssembly().bristles().beginning());
        assertSame(activityFrom.from(), activityRecord.oldBroom().headWithBracketAssembly().headAssembly().bristles().ending().from());
        assertSame(activityFrom.to(), activityRecord.oldBroom().headWithBracketAssembly().headAssembly().bristles().ending().to());

        assertSame(LIFE_START, activityRecord.oldBroom().headWithBracketAssembly().bracket().beginning());
        assertNull(activityRecord.oldBroom().headWithBracketAssembly().bracket().ending().from());
        assertNull(activityRecord.oldBroom().headWithBracketAssembly().bracket().ending().to());

        assertSame(broom.handle(), activityRecord.newBroom().handle());
        assertSame(ASSEMBLY_START, activityRecord.newBroom().headWithBracketAssembly().beginning());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().ending().from());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().ending().to());

        assertSame(ASSEMBLY_START, activityRecord.newBroom().headWithBracketAssembly().headAssembly().beginning());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().headAssembly().ending().from());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().headAssembly().ending().to());

        assertSame(LIFE_START, activityRecord.newBroom().headWithBracketAssembly().headAssembly().head().beginning());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().headAssembly().head().ending().from());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().headAssembly().head().ending().to());

        assertSame(LIFE_START, activityRecord.newBroom().headWithBracketAssembly().headAssembly().bristles().beginning());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().headAssembly().bristles().ending().from());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().headAssembly().bristles().ending().to());

        assertSame(LIFE_START, activityRecord.newBroom().headWithBracketAssembly().bracket().beginning());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().bracket().ending().from());
        assertNull(activityRecord.newBroom().headWithBracketAssembly().bracket().ending().to());
    }

    @Test
    public void serialisationTest() throws JsonProcessingException {
        //
        // Create the parts
        //
        final var broomHandle = new BroomHandle(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var broomHead = new BroomHead(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var bristles = new Bristles(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var broomBracket = new BroomBracket(randStr(), LIFE_START, UNKNOWN_SCRAPPING);

        // Gather the parts into an Agglomerate (not really necessary, this just shows
        // what an Agglomerate is)
        final var parts = svc.createAgglomerate(randStr(), Set.of(broomBracket, bristles, broomHead, broomHandle), AGGREGATED_EVENT, DISAGGREGATED_EVENT);

        // Assemble the broom composite from the set of parts
        final var broom = assembleBroom(parts);

        // Serialise to JSON and back.
        final var json = JsonUtils.writeJsonString(broom);
        final var broom2 = JsonUtils.readJsonString(json, Broom.class);

        assertEquals(broom, broom2);
    }

    @Test
    public void persistenceTest() {
        //
        // Create the parts
        //
        final var broomHandle = new BroomHandle(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var broomHead = new BroomHead(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var bristles = new Bristles(randStr(), LIFE_START, UNKNOWN_SCRAPPING);
        final var broomBracket = new BroomBracket(randStr(), LIFE_START, UNKNOWN_SCRAPPING);

        // Gather the parts into an Agglomerate (not really necessary, this just shows
        // what an Agglomerate is)
        final var parts = svc.createAgglomerate(randStr(), Set.of(broomBracket, bristles, broomHead, broomHandle), AGGREGATED_EVENT, DISAGGREGATED_EVENT);

        // Assemble the broom composite from the set of parts
        final var broom = assembleBroom(parts);

    }

    /**
     * Use a broom to sweep the road.
     *
     * @param broom
     *            a Broom
     * @return true if the road was successfully swept.
     */
    private boolean sweepRoad(final Broom broom) {
        // Dummy method just to illustrate a point in the main test.
        return broom != null;
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
        // The old assemblies are scrapped (disassembled actually)
        final var assemblyEnds = new Disassembled(randStr(), activityStart.from(), activityStart.to());
        final var bristlesScrapped = new Scrapped(randStr(), activityStart.from(), activityStart.to());

        // Set the ending for the old headAssembly
        final var headAssembly = broom.headWithBracketAssembly().headAssembly();
        final Bristles oldBristles = new Bristles(headAssembly.bristles().identifier(), headAssembly.bristles().beginning(), bristlesScrapped);
        final var oldHeadAssembly = new BroomHeadAssembly(headAssembly.identifier(), headAssembly.head(), oldBristles, headAssembly.beginning(),
            assemblyEnds);

        // Set the ending for the old headAndBracketAssembly
        final var headAndBracketAssembly = broom.headWithBracketAssembly();
        final var oldHeadWithBracketAssembly = new BroomHeadWithBracketAssembly(headAndBracketAssembly.identifier(), oldHeadAssembly,
            headAndBracketAssembly.bracket(), headAndBracketAssembly.beginning(), assemblyEnds);

        // Set the ending for the old Broom.
        final var oldBroom = new Broom(randStr(), broom.handle(), oldHeadWithBracketAssembly, broom.beginning(), assemblyEnds);

        // Create the updated broom
        final var updatedHeadAssembly = fitBristles(randStr(), headAssembly.head(), bristles, ASSEMBLY_START, UNKNOWN_DISASSEMBLY);
        final var updatedHeadAndBracketAssembly = fitBracket(randStr(), updatedHeadAssembly, headAndBracketAssembly.bracket(), ASSEMBLY_START,
            UNKNOWN_DISASSEMBLY);
        final var updatedBroom = fitHandle(randStr(), oldBroom.handle(), updatedHeadAndBracketAssembly, ASSEMBLY_START, UNKNOWN_DISASSEMBLY);

        return new ReplaceBristlesActivity(randStr(), "Replace bristles", oldBroom, updatedBroom, activityStart, activityEnd);
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
    private Broom fitHandle(final String id, final BroomHandle broomHandle, final BroomHeadWithBracketAssembly broomHeadWithBracketAssembly,
        final Assembled beginning, final Disassembled ending) {
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
        final BroomBracket broomBracket, final Assembled beginning, final Disassembled ending) {
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
        final Assembled beginning, final Disassembled ending) {
        return new BroomHeadAssembly(id, head, bristles, beginning, ending);
    }

    private static String randStr() {
        return UUID.randomUUID().toString();
    }

}

record Broom(String identifier, BroomHandle handle, BroomHeadWithBracketAssembly headWithBracketAssembly, Assembled beginning, Disassembled ending)
    implements Individual<Assembled, Disassembled> {
}

record BroomHeadAssembly(String identifier, BroomHead head, Bristles bristles, Assembled beginning, Disassembled ending)
    implements Individual<Assembled, Disassembled> {
}

record BroomHeadWithBracketAssembly(String identifier, BroomHeadAssembly headAssembly, BroomBracket bracket,
    Assembled beginning, Disassembled ending) implements Individual<Assembled, Disassembled> {
}

record BroomHandle(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record BroomHead(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record Bristles(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record BroomBracket(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
}

record ReplaceBristlesActivity(String identifier, String actionsDescription, Broom oldBroom, Broom newBroom, Started beginning, Stopped ending)
    implements Individual<Started, Stopped> {
}