package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.reference.TransferringOfOwnershipImpl;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * Test the ownership classes and services.
 *
 * @author Tony Walmsley
 */
public class OwnershipTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

    // We're not recording DNA
    private static final DNA DNA = svc.createDna("unknown", null);

    private static final Instant FEB_4TH_1948_END = Instant.parse("1948-02-04T23:59:59.99Z");
    private static final Instant FEB_4TH_1948_START = Instant.parse("1948-02-04T00:00:00.00Z");
    private static final Instant JAN_1ST_1999_END = Instant.parse("1999-01-01T23:59:59.00Z");
    private static final Instant JAN_1ST_1999_START = Instant.parse("1999-01-01T00:00:00.00Z");
    private static final Instant JAN_1ST_2024_END = Instant.parse("2024-01-01T23:59:59.00Z");
    private static final Instant JAN_1ST_2024_START = Instant.parse("2024-01-01T00:00:00.00Z");
    private static final Instant MAY_24TH_1941_END = Instant.parse("1941-04-24T23:59:59.99Z");
    private static final Instant MAY_24TH_1941_START = Instant.parse("1941-05-24T00:00:00.00Z");
    private static final Instant NOV_11TH_2024_MIDDAY = Instant.parse("2024-11-11T12:00:00.00Z");
    private static final Instant NOV_11TH_2024_START = Instant.parse("2024-11-11T00:00:00.00Z");

    @Test
    public void testTransferOfOwnership() throws JsonProcessingException {

        // Create a language and class
        final var english = svc.createLanguage("en-GB", "British English");
        final var languages = svc.createClass("languanges", Set.of(english));

        // Create Alice, Bob, and a Car
        final var alice = getAlice(DNA, english, languages);
        final var bob = getBob(DNA, english, languages);
        final var car = getCar();

        // Record that Alice owns the car for a time period.
        final var aliceBuysCar = new TransferredFrom("bought1", JAN_1ST_2024_START, JAN_1ST_2024_END);
        final var aliceSellsCar = new TransferredTo("sold1", null, null);
        final var aliceOwnsCar = svc.createOwnership("aliceOwnsCar", "Car Purchase", alice, car, aliceBuysCar, aliceSellsCar);

        // Transfer ownership when Alice sells the car to Bob
        final var transferActivityBegins = new Started("transferBegins", NOV_11TH_2024_START, NOV_11TH_2024_START);
        final var transferActivityEnds = new Stopped("transferEnds", NOV_11TH_2024_MIDDAY, NOV_11TH_2024_MIDDAY);

        final var transfer = svc.transferOwnership("carSoldToBob", "Car Sold", aliceOwnsCar, bob, transferActivityBegins, transferActivityEnds);

        final var json = JsonUtils.writeJsonString(transfer);
        final var transfer2 = JsonUtils.readJsonString(json, TransferringOfOwnershipImpl.class);

        assertEquals(transfer, transfer2);

        assertSame(NOV_11TH_2024_START, transfer.beginning().from());
        assertSame(NOV_11TH_2024_START, transfer.beginning().to());
        assertSame(NOV_11TH_2024_MIDDAY, transfer.ending().from());
        assertSame(NOV_11TH_2024_MIDDAY, transfer.ending().to());

        assertSame(aliceOwnsCar.identifier(), transfer.from().identifier());
        assertSame(aliceOwnsCar.actionsDescription(), transfer.from().actionsDescription());
        assertSame(alice, transfer.from().owner());
        assertSame(car, transfer.from().owned());
        assertSame(aliceOwnsCar.beginning(), transfer.from().beginning());
        assertSame(transferActivityEnds.from(), transfer.from().ending().from());
        assertSame(transferActivityEnds.to(), transfer.from().ending().to());

        assertSame("carSoldToBob", transfer.to().identifier());
        assertSame("Car Sold", transfer.to().actionsDescription());
        assertSame(bob, transfer.to().owner());
        assertSame(car, transfer.to().owned());
        assertSame(transferActivityBegins.from(), transfer.to().beginning().from());
        assertSame(transferActivityBegins.to(), transfer.to().beginning().to());
        assertNull(transfer.to().ending().to());
        assertNull(transfer.to().ending().to());
    }

    private Car getCar() {
        final var built = new Built("built", JAN_1ST_1999_START, JAN_1ST_1999_END);
        final var scrapped = new Scrapped("scrapped", null, null);
        final var car = new Car("car", built, scrapped);
        return car;
    }

    private Human getBob(final DNA dna, final Language english, final Class<Language> languages) {
        final var bobBorn = new Birth("bobBorn", MAY_24TH_1941_START, MAY_24TH_1941_END);
        final var bobDied = new Death("bobDied", null, null);
        final var bobNamed = new Resignified("bobNamed", null, null);
        final var bobRenamed = new Resignified("bobRenamed", null, null);
        final var bobName = svc.createSignifier("bobName", "Bob Dylan", english, bobNamed, bobRenamed);
        final var bobNames = Set.of(bobName);
        final var bobNamesClass = svc.createClass("bobNames", bobNames);
        final var bob = svc.createHuman("bob", bobBorn, bobDied, bobNamesClass, english, languages, dna);
        return bob;
    }

    private Human getAlice(final DNA dna, final Language english, final Class<Language> languages) {
        final var aliceBorn = new Birth("aliceBorn", FEB_4TH_1948_START, FEB_4TH_1948_END);
        final var aliceDied = new Death("aliceDied", null, null);
        final var aliceNamed = new Resignified("aliceNamed", null, null);
        final var aliceRenamed = new Resignified("aliceRenamed", null, null);
        final var aliceName = svc.createSignifier("aliceName", "Alice Cooper", english, aliceNamed, aliceRenamed);
        final var aliceNames = Set.of(aliceName);
        final var aliceNamesClass = svc.createClass("aliceNames", aliceNames);
        final var alice = svc.createHuman("alice", aliceBorn, aliceDied, aliceNamesClass, english, languages, dna);
        return alice;
    }
}