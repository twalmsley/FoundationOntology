package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;
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

    @Test
    public void testTransferOfOwnership() {

        // We're not recording DNA
        final var dna = svc.createDna("unknown", null);

        // Create a language and class
        final var english = svc.createLanguage("en-GB", "British English");
        final var languages = svc.createClass("languanges", Set.of(english));

        // Create Alice, Bob, and a Car
        final var alice = getAlice(dna, english, languages);
        final var bob = getBob(dna, english, languages);
        final var car = getCar();

        // Record that Alice owns the car for a time period.
        final var aliceBuysCar = ev.createTransferredFromEvent("bought1", Instant.parse("2024-01-01T00:00:00.00Z"), Instant.parse("2024-01-01T23:59:59.00Z"));
        final var aliceSellsCar = ev.createTransferredToEvent("sold1", null, null);
        final var aliceOwnsCar = svc.createOwnership("aliceOwnsCar", "Car Purchase", alice, car, aliceBuysCar, aliceSellsCar);

        // Transfer ownership when Alice sells the car to Bob
        final var aliceSellsCar2 = ev.createSoldEvent("aliceSellsCar", Instant.parse("2024-02-01T00:00:00.00Z"), Instant.parse("2024-02-01T23:59:59.99Z"));
        final var bobBuysCar = ev.createSoldEvent("aliceSellsCar", Instant.parse("2024-02-01T00:00:00.00Z"), Instant.parse("2024-02-01T23:59:59.99Z"));
        final var bobSellsCar = ev.createSoldEvent("bobSellsCar", null, null);
        final var transferActivityBegins = ev.createStartedEvent("transferBegins", Instant.parse("2024-11-11T00:00:00.00Z"),
            Instant.parse("2024-11-11T00:00:00.00Z"));
        final var transferActivityEnds = ev.createStoppedEvent("transferEnds", Instant.parse("2024-11-11T12:00:00.00Z"),
            Instant.parse("2024-11-11T12:00:00.00Z"));

        final var transfer = svc.transferOwnership("carSoldToBob", "Car Sold", aliceOwnsCar, bob, transferActivityBegins, transferActivityEnds);

        assertSame(aliceBuysCar, transfer.from().beginning());
        assertNotNull(transfer.to().beginning().from());
        assertNotNull(transfer.to().beginning().to());
        assertSame(bob, transfer.to().owner());
        assertSame(car, transfer.to().owned());

        JsonUtils.dumpJson(transfer);
    }

    private Individual<Built, Scrapped> getCar() {
        final var built = ev.createBuiltEvent("built", Instant.parse("1999-01-01T00:00:00.00Z"), Instant.parse("1999-01-01T23:59:59.00Z"));
        final var scrapped = ev.createScrappedEvent("scrapped", null, null);
        final var car = new Car("car", built, scrapped);
        return car;
    }

    private Human getBob(final DNA dna, final Language english, final Class<Language> languages) {
        final var bobBorn = ev.createBirthEvent("bobBorn", Instant.parse("1941-05-24T00:00:00.00Z"), Instant.parse("1941-04-24T23:59:59.99Z"));
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
        final var aliceBorn = ev.createBirthEvent("aliceBorn", Instant.parse("1948-02-04T00:00:00.00Z"), Instant.parse("1948-02-04T23:59:59.99Z"));
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