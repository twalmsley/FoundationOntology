package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.reference.PossibleWorldImpl;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * Test that Organisations can be created.
 *
 * @author Tony Walmsley
 */
public class OrganisationTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

    private static final Instant FROM = Instant.parse("2024-01-01T00:00:00.00Z");
    private static final Instant TO = null;
    private static final Instant UNKNOWN_TIME = null;
    private static final DNA UNKNOWN_DNA = null;

    @Test
    public void testCreateOrganisations() throws JsonProcessingException {

        // Create the required Events
        final var personNamed = new Resignified(randString(), FROM, UNKNOWN_TIME);
        final var notRenamed = new Resignified(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var acmeNamed = new Resignified(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var acmeRenamed = new Resignified(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var born = new Birth(randString(), FROM, TO);
        final var died = new Death(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var incorporated = new Formed(randString(), FROM, TO);
        final var dissolved = new Dissolved(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var appointed = new Appointed(randString(), FROM, TO);
        final var dismissed = new Removed(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var epochStart = new Created(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final var epochEnd = new Deleted(randString(), UNKNOWN_TIME, UNKNOWN_TIME);

        // Create the languages
        final var english = svc.createLanguage(randString(), "British English");
        final var german = svc.createLanguage(randString(), "Deutsch");

        // Create some Signifiers for the person and the organisation.
        final var personSignifier1 = svc.createSignifier(randString(), "Alice Cooper", english, personNamed, notRenamed);
        final var personSignifier2 = svc.createSignifier(randString(), "Vincent Damon Furnier", english, personNamed, notRenamed);
        final var acmeSignifier1 = svc.createSignifier(randString(), "ACME Widgets Ltd", english, acmeNamed, acmeRenamed);
        final var acmeSignifier2 = svc.createSignifier(randString(), "ACME Ltd", english, acmeRenamed, notRenamed);

        // The signifiers need to be added to Classes (Sets)
        final var person1Names = svc.createClass(randString(), Set.of(personSignifier1, personSignifier2));
        final var orgNames = svc.createClass(randString(), Set.of(acmeSignifier1, acmeSignifier2));

        // Create the languages that the person uses.
        final var languages = svc.createClass(randString(), Set.of(english, german));

        // Create the person
        final var alice = svc.createHuman(randString(), born, died, person1Names, english, languages, UNKNOWN_DNA);

        // Create a Class of memberships for the person as a member of something.
        final var ceoRole = new CeoRole(randString(), "CEO");
        final var ceoMembership = svc.createMembership(randString(), alice, ceoRole, appointed, dismissed);
        final var acmeTeamMemberships = svc.createClass(randString(), Set.of(ceoMembership));

        // Create an organisation with memberships and no sub-units.
        final Class<Organisation> units = svc.createClass(randString(), Set.of());
        final var acme = svc.createOrganisation(randString(), acmeTeamMemberships, "ACME makes widgets", units, orgNames, incorporated, dissolved);

        assertNotNull(acme);

        // Add the objects to a Possible World
        // (The correct type cannot be inferred correctly for this Set for some reason.)
        final Set<Individual<? extends Event, ? extends Event>> parts = Set.of(
            personSignifier1,
            personSignifier2,
            acmeSignifier1,
            acmeSignifier2,
            alice,
            ceoMembership,
            acme);

        final var world = svc.createPossibleWorld(randString(), parts, epochStart, epochEnd);
        assertNotNull(world);

        final var json = JsonUtils.writeJsonString(world);
        final var world2 = JsonUtils.readJsonString(json, PossibleWorldImpl.class);

        assertEquals(world, world2);
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}

record CeoRole(String identifier, String name) implements Role {
}