package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * Test the ownership classes and services.
 *
 * @author Tony Walmsley
 */
public class OwnershipTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

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
        final var aliceBuysCar = svc.createEvent("bought1", Instant.parse("2024-01-01T00:00:00.00Z"), Instant.parse("2024-01-01T23:59:59.00Z"));
        final var aliceSellsCar = svc.createEvent("sold1", null, null);
        final var aliceOwnsCar = svc.createOwnership("aliceOwnsCar", "Car Purchase", alice, car, aliceBuysCar, aliceSellsCar);

        // Transfer ownership when Alice sells the car to Bob
        final var saleEvent = svc.createEvent("sale1", Instant.parse("2024-02-01T00:00:00.00Z"), Instant.parse("2024-02-01T23:59:59.99Z"));
        final var bobSellsCar = svc.createEvent("sold2", null, null);
        final var transfer = svc.transferOwnership("carSoldToBob", "Car Sold", aliceOwnsCar, bob, saleEvent, bobSellsCar);

        assertSame(saleEvent, transfer.from().ending());
        assertSame(saleEvent, transfer.to().beginning());
        assertSame(bob, transfer.to().owner());
        assertSame(car, transfer.to().owned());

        JsonUtils.dumpJson(transfer);
    }

    private Individual getCar() {
        final var built = svc.createEvent("built", Instant.parse("1999-01-01T00:00:00.00Z"), Instant.parse("1999-01-01T23:59:59.00Z"));
        final var scrapped = svc.createEvent("scrapped", null, null);
        final var car = svc.createIndividual("car", built, scrapped);
        return car;
    }

    private Human getBob(final DNA dna, final Language english, final Class<Language> languages) {
        final var bobBorn = svc.createEvent("bobBorn", Instant.parse("1941-05-24T00:00:00.00Z"), Instant.parse("1941-04-24T23:59:59.99Z"));
        final var bobDied = svc.createEvent("bobDied", null, null);
        final var bobName = svc.createSignifier("bobName", "Bob Dylan", english, bobBorn, bobDied);
        final var bobNames = Set.of(bobName);
        final var bobNamesClass = svc.createClass("bobNames", bobNames);
        final var bob = svc.createHuman("bob", bobBorn, bobDied, bobNamesClass, english, languages, dna);
        return bob;
    }

    private Human getAlice(final DNA dna, final Language english, final Class<Language> languages) {
        final var aliceBorn = svc.createEvent("aliceBorn", Instant.parse("1948-02-04T00:00:00.00Z"), Instant.parse("1948-02-04T23:59:59.99Z"));
        final var aliceDied = svc.createEvent("aliceDied", null, null);
        final var aliceName = svc.createSignifier("aliceName", "Alice Cooper", english, aliceBorn, aliceDied);
        final var aliceNames = Set.of(aliceName);
        final var aliceNamesClass = svc.createClass("aliceNames", aliceNames);
        final var alice = svc.createHuman("alice", aliceBorn, aliceDied, aliceNamesClass, english, languages, dna);
        return alice;
    }
}
