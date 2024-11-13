package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.reference.EventServicesImpl;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.EventServices;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * Test the ownership classes and services.
 *
 * @author Tony Walmsley
 */
public class OwnershipTest {

    private static final OntologyServices svc = new OntologyServicesImpl();
    private static final EventServices ev = new EventServicesImpl();

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
    public void testTransferOfOwnership() {

        // Create a language and class
        final var english = svc.createLanguage("en-GB", "British English");
        final var languages = svc.createClass("languanges", Set.of(english));

        // Create Alice, Bob, and a Car
        final var alice = getAlice(DNA, english, languages);
        final var bob = getBob(DNA, english, languages);
        final var car = getCar();

        // Record that Alice owns the car for a time period.
        final var aliceBuysCar = ev.createTransferredFromEvent("bought1", JAN_1ST_2024_START, JAN_1ST_2024_END);
        final var aliceSellsCar = ev.createTransferredToEvent("sold1", null, null);
        final var aliceOwnsCar = svc.createOwnership("aliceOwnsCar", "Car Purchase", alice, car, aliceBuysCar, aliceSellsCar);

        // Transfer ownership when Alice sells the car to Bob
        final var transferActivityBegins = ev.createStartedEvent("transferBegins", NOV_11TH_2024_START, NOV_11TH_2024_START);
        final var transferActivityEnds = ev.createStoppedEvent("transferEnds", NOV_11TH_2024_MIDDAY, NOV_11TH_2024_MIDDAY);

        final var transfer = svc.transferOwnership("carSoldToBob", "Car Sold", aliceOwnsCar, bob, transferActivityBegins, transferActivityEnds);

        JsonUtils.dumpJson(transfer);

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
        final var built = ev.createBuiltEvent("built", JAN_1ST_1999_START, JAN_1ST_1999_END);
        final var scrapped = ev.createScrappedEvent("scrapped", null, null);
        final var car = new Car("car", built, scrapped);
        return car;
    }

    private Human getBob(final DNA dna, final Language english, final Class<Language> languages) {
        final var bobBorn = ev.createBirthEvent("bobBorn", MAY_24TH_1941_START, MAY_24TH_1941_END);
        final var bobDied = ev.createDeathEvent("bobDied", null, null);
        final var bobNamed = ev.createResignifiedEvent("bobNamed", null, null);
        final var bobRenamed = ev.createResignifiedEvent("bobRenamed", null, null);
        final var bobName = svc.createSignifier("bobName", "Bob Dylan", english, bobNamed, bobRenamed);
        final var bobNames = Set.of(bobName);
        final var bobNamesClass = svc.createClass("bobNames", bobNames);
        final var bob = svc.createHuman("bob", bobBorn, bobDied, bobNamesClass, english, languages, dna);
        return bob;
    }

    private Human getAlice(final DNA dna, final Language english, final Class<Language> languages) {
        final var aliceBorn = ev.createBirthEvent("aliceBorn", FEB_4TH_1948_START, FEB_4TH_1948_END);
        final var aliceDied = ev.createDeathEvent("aliceDied", null, null);
        final var aliceNamed = ev.createResignifiedEvent("aliceNamed", null, null);
        final var aliceRenamed = ev.createResignifiedEvent("aliceRenamed", null, null);
        final var aliceName = svc.createSignifier("aliceName", "Alice Cooper", english, aliceNamed, aliceRenamed);
        final var aliceNames = Set.of(aliceName);
        final var aliceNamesClass = svc.createClass("aliceNames", aliceNames);
        final var alice = svc.createHuman("alice", aliceBorn, aliceDied, aliceNamesClass, english, languages, dna);
        return alice;
    }
}