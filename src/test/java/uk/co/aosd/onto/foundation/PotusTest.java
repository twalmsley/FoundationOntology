package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * Model Donal Trump as POTUS.
 *
 * <P>
 * Model the USA as a Nation with a sub-organisation that is the US Government *
 * with Donald Trump's two terms as POTUS.
 * </P>
 *
 * @author Tony Walmsley
 */
public class PotusTest {

    private static OntologyServices svc = new OntologyServicesImpl();

    private static final DNA UNKNOWN_DNA = null;
    private static final String PURPOSE = "To occupy its territory and serve its people.";
    private static final Event USA_FROM = svc.createEvent(randStr(), Instant.parse("1776-06-04T00:00:00.00Z"), Instant.parse("1776-06-04T23:59:59.99Z"));
    private static final Event USA_TO = svc.createEvent(randStr(), null, null);
    private static final Event TRUMP_DIED = svc.createEvent(randStr(), null, null);
    private static final Event TRUMP_BORN = svc.createEvent(randStr(), Instant.parse("1946-06-14T00:00:00.00Z"), Instant.parse("1946-06-14T23:59:59.99Z"));
    private static final Event TRUMP_POTUS_START_1 = svc.createEvent(randStr(), Instant.parse("2017-01-20T00:00:00.00Z"), Instant.parse("2017-01-20T23:59:59.99Z"));
    private static final Event TRUMP_POTUS_END_1 = svc.createEvent(randStr(), Instant.parse("2021-01-20T00:00:00.00Z"), Instant.parse("2017-01-20T23:59:59.99Z"));
    private static final Event TRUMP_POTUS_START_2 = svc.createEvent(randStr(), Instant.parse("2025-01-20T00:00:00.00Z"), Instant.parse("2017-01-20T23:59:59.99Z"));
    private static final Event TRUMP_POTUS_END_2 = svc.createEvent(randStr(), null, null);
    private static final Event TRUMP_CITIZENSHIP_ENDS = svc.createEvent(randStr(), null, null);

    @Test
    public void test() {

        // Create Donald Trump
        final var usEnglish = svc.createLanguage(randStr(), "American English");
        final var languages = svc.createClass(randStr(), Set.of(usEnglish));
        final var personSignifier1 = svc.createSignifier(randStr(), "Donald Trump", usEnglish, TRUMP_BORN, TRUMP_DIED);
        final var person1Names = svc.createClass(randStr(), Set.of(personSignifier1));

        final var donaldTrump = svc.createHuman(randStr(), TRUMP_BORN, TRUMP_DIED, person1Names, usEnglish, languages, UNKNOWN_DNA);

        // Create the names of the USA
        final var usaSignifier1 = svc.createSignifier(randStr(), "USA", usEnglish, USA_FROM, USA_TO);
        final var usaSignifier2 = svc.createSignifier(randStr(), "United States of America", usEnglish, USA_FROM,
            USA_TO);
        final var usaSignifier3 = svc.createSignifier(randStr(), "US", usEnglish, USA_FROM, USA_TO);
        final var usaSignifier4 = svc.createSignifier(randStr(), "United States", usEnglish, USA_FROM, USA_TO);
        final var usaSignifier5 = svc.createSignifier(randStr(), "America", usEnglish, USA_FROM, USA_TO);
        final var setOfSignifiers = Set.of(usaSignifier1, usaSignifier2, usaSignifier3, usaSignifier4, usaSignifier5);
        final var namesOfTheUsa = svc.createClass(randStr(), setOfSignifiers);

        // Create the name of the USA Government
        final var usaGovSignifier1 = svc.createSignifier(randStr(), "The Government of the United States of America", usEnglish, USA_FROM,
            USA_TO);
        final var namesOfTheUsaGov = svc.createClass(randStr(), Set.of(usaGovSignifier1));

        // Create the POTUS and Citizen roles.
        final var presidentRole = svc.createRole("President of the United States of America");
        final var citizenRole = svc.createRole("Citizen of the United States of America");

        // Register Donald Trump's memberships of the US Government in the role of POTUS
        final var potus1 = svc.createMembership(randStr(), donaldTrump, presidentRole, TRUMP_POTUS_START_1, TRUMP_POTUS_END_1);
        final var potus2 = svc.createMembership(randStr(), donaldTrump, presidentRole, TRUMP_POTUS_START_2, TRUMP_POTUS_END_2);
        final var memberships = svc.createClass(randStr(), Set.of(potus1, potus2));

        // Register Donald Trump as a member of the USA in the role of citizen.
        final var citizen1 = svc.createMembership(randStr(), donaldTrump, citizenRole, TRUMP_BORN, TRUMP_CITIZENSHIP_ENDS);
        final var citizenships = svc.createClass(randStr(), Set.of(citizen1));

        // Create the US Government
        final Class<Organisation> units = svc.createClass(randStr(), Set.of());
        final var usGovt = svc.createOrganisation(randStr(), memberships, "To govern the USA", units, namesOfTheUsaGov, USA_FROM,
            USA_TO);

        // Sub-organisations of the USA
        final var usaUnits = svc.createClass(randStr(), Set.of(usGovt));

        // Create the USA
        final var usaTerritory = new Territory(randStr(), USA_FROM, USA_TO);
        final var usa = new Nation(randStr(), usaTerritory, citizenships, PURPOSE, namesOfTheUsa, usaUnits, USA_FROM, USA_TO);

        assertNotNull(usa);

        JsonUtils.dumpJson(usa);
    }

    private static int id;

    private static String randStr() {
        return Integer.toString(id++);
    }

}

record Nation(String identifier, Territory territory, Class<Membership> members, String purpose,
    Class<Signifier<String>> names, Class<Organisation> units, Event beginning, Event ending)
    implements Organisation {
}

record Territory(String identifier, Event beginning, Event ending) implements Individual {

}