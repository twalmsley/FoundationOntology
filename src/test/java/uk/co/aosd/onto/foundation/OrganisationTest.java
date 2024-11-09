package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * Test that Organisations can be created.
 *
 * @author Tony Walmsley
 */
public class OrganisationTest {

    private static final Instant FROM = Instant.parse("2024-01-01T00:00:00.00Z");
    private static final Instant TO = null;
    private static final Instant UNKNOWN_TIME = null;
    private static final DNA UNKNOWN_DNA = null;

    @Test
    public void testCreateOrganisations() {

        final OntologyServices svc = new OntologyServicesImpl();

        // Create the required Events
        final var born = svc.createEvent(randString(), FROM, TO);
        final var died = svc.createEvent(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var incorporated = svc.createEvent(randString(), FROM, TO);
        final var dissolved = svc.createEvent(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var appointed = svc.createEvent(randString(), FROM, TO);
        final var dismissed = svc.createEvent(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var epochStart = svc.createEvent(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var epochEnd = svc.createEvent(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        
        // Create the languages
        final var english = svc.createLanguage(randString(), "British English");
        final var german = svc.createLanguage(randString(), "Deutsch");

        // Create some Signifiers for the person and the organisation.
        final var personSignifier1 = svc.createSignifier(randString(), "Alice Cooper", english, born, died);
        final var personSignifier2 = svc.createSignifier(randString(), "Vincent Damon Furnier", english, born, died);
        final var acmeSignifier1 = svc.createSignifier(randString(), "ACME Widgets Ltd", english, incorporated, dissolved);
        final var acmeSignifier2 = svc.createSignifier(randString(), "ACME Ltd", english, incorporated, dissolved);

        // The signifiers need to be added to Classes (Sets)
        final var person1Names = svc.createClass(randString(), Set.of(personSignifier1, personSignifier2));
        final var orgNames = svc.createClass(randString(), Set.of(acmeSignifier1, acmeSignifier2));

        // Create the languages that the person uses.
        final var languages = svc.createClass(randString(), Set.of(english, german));

        // Create the person
        final var alice = svc.createHuman(randString(), born, died, person1Names, english, languages, UNKNOWN_DNA);

        // Create a Class of memberships for the person as a member of something.
        final var ceoRole = svc.createRole("CEO");
        final var ceoMembership = svc.createMembership(randString(), alice, ceoRole, appointed, dismissed);
        final var acmeTeamMemberships = svc.createClass(randString(), Set.of(ceoMembership));

        // Create an organisation with memberships and no sub-units.
        final Class<Organisation> units = svc.createClass(randString(), Set.of());
        final var acme = svc.createOrganisation(randString(), acmeTeamMemberships, "ACME makes widgets", units, orgNames, incorporated, dissolved);

        assertNotNull(acme);

        // Add the objects to a Possible World
        final var parts = Set.of(
            personSignifier1,
            personSignifier2,
            acmeSignifier1,
            acmeSignifier2,
            alice,
            ceoMembership,
            acme);

        final var world = svc.createPossibleWorld(randString(), parts, epochStart, epochEnd);
        assertNotNull(world);

        JsonUtils.dumpJson(world);
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}