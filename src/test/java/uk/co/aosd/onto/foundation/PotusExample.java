package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * Model Donald Trump as POTUS.
 *
 * <P>
 * Model the USA as a Nation with a sub-organisation that is the US Government
 * with Donald Trump's two terms as POTUS.
 * </P>
 *
 * @author Tony Walmsley
 */
public class PotusExample {

    private static final OntologyServices svc = new OntologyServicesImpl();

    private static final Instant JAN_20TH_2025_END = Instant.parse("2025-01-20T23:59:59.99Z");
    private static final Instant JAN_20TH_2025_START = Instant.parse("2025-01-20T00:00:00.00Z");
    private static final Instant JAN_20TH_2021_END = Instant.parse("2021-01-20T23:59:59.99Z");
    private static final Instant JAN_20TH_2021_START = Instant.parse("2021-01-20T00:00:00.00Z");
    private static final Instant JAN_20TH_2017_END = Instant.parse("2017-01-20T23:59:59.99Z");
    private static final Instant JAN_20TH_2017_START = Instant.parse("2017-01-20T00:00:00.00Z");
    private static final Instant JUNE_14TH_1946_END = Instant.parse("1946-06-14T23:59:59.99Z");
    private static final Instant JUNE_14TH_1946_START = Instant.parse("1946-06-14T00:00:00.00Z");
    private static final Instant JUNE_4TH_1776_END = Instant.parse("1776-06-04T23:59:59.99Z");
    private static final Instant JUNE_4TH_1776_START = Instant.parse("1776-06-04T00:00:00.00Z");

    private static final DNA UNKNOWN_DNA = null;
    private static final Appointed TRUMP_CITIZENSHIP_BEGINS = new Appointed(randStr(), JUNE_14TH_1946_START, JUNE_14TH_1946_END);
    private static final Appointed TRUMP_POTUS_START_1 = new Appointed(randStr(), JAN_20TH_2017_START, JAN_20TH_2017_END);
    private static final Appointed TRUMP_POTUS_START_2 = new Appointed(randStr(), JAN_20TH_2025_START, JAN_20TH_2025_END);
    private static final Birth TRUMP_BORN = new Birth(randStr(), JUNE_14TH_1946_START, JUNE_14TH_1946_END);
    private static final Death TRUMP_DIED = new Death(randStr(), null, null);
    private static final Dissolved USA_TO = new Dissolved(randStr(), null, null);
    private static final Formed USA_FROM = new Formed(randStr(), JUNE_4TH_1776_START, JUNE_4TH_1776_END);
    private static final Removed TRUMP_CITIZENSHIP_ENDS = new Removed(randStr(), null, null);
    private static final Removed TRUMP_POTUS_END_1 = new Removed(randStr(), JAN_20TH_2021_START, JAN_20TH_2021_END);
    private static final Removed TRUMP_POTUS_END_2 = new Removed(randStr(), null, null);
    private static final Resignified TRUMP_NAMED = new Resignified(randStr(), JUNE_14TH_1946_START, JUNE_14TH_1946_END);
    private static final Resignified TRUMP_RENAMED = new Resignified(randStr(), null, null);
    private static final Resignified USA_NAMED = new Resignified(randStr(), JUNE_4TH_1776_START, JUNE_4TH_1776_END);
    private static final Resignified USA_RENAMED = new Resignified(randStr(), null, null);
    private static final String PURPOSE = "To occupy its territory and serve its people.";

    private static final PresidentOfTheUsa PRESIDENT_USA = new PresidentOfTheUsa(randStr(), "President of the United States of America");
    private static final CitizenOfTheUsa CITIZEN_USA = new CitizenOfTheUsa(randStr(), "Citizen of the United States of America");

    @Test
    public void test() {

        // Create Donald Trump
        final var usEnglish = svc.createLanguage(randStr(), "American English");
        final var languages = svc.createClass(randStr(), Set.of(usEnglish));
        final var personSignifier1 = svc.createSignifier(randStr(), "Donald Trump", usEnglish, TRUMP_NAMED, TRUMP_RENAMED);
        final var person1Names = svc.createClass(randStr(), Set.of(personSignifier1));

        final var donaldTrump = svc.createHuman(randStr(), TRUMP_BORN, TRUMP_DIED, person1Names, usEnglish, languages, UNKNOWN_DNA);

        // Create the names of the USA
        final var usaSignifier1 = svc.createSignifier(randStr(), "USA", usEnglish, USA_NAMED, USA_RENAMED);
        final var usaSignifier2 = svc.createSignifier(randStr(), "United States of America", usEnglish, USA_NAMED, USA_RENAMED);
        final var usaSignifier3 = svc.createSignifier(randStr(), "US", usEnglish, USA_NAMED, USA_RENAMED);
        final var usaSignifier4 = svc.createSignifier(randStr(), "United States", usEnglish, USA_NAMED, USA_RENAMED);
        final var usaSignifier5 = svc.createSignifier(randStr(), "America", usEnglish, USA_NAMED, USA_RENAMED);
        final var setOfSignifiers = Set.of(usaSignifier1, usaSignifier2, usaSignifier3, usaSignifier4, usaSignifier5);
        final var namesOfTheUsa = svc.createClass(randStr(), setOfSignifiers);

        // Create the name of the USA Government
        final var usaGovSignifier1 = svc.createSignifier(randStr(), "The Government of the United States of America", usEnglish, USA_NAMED, USA_RENAMED);
        final var namesOfTheUsaGov = svc.createClass(randStr(), Set.of(usaGovSignifier1));

        // Register Donald Trump's memberships of the US Government in the role of POTUS
        final var potus1 = svc.createMembership(randStr(), donaldTrump, PRESIDENT_USA, TRUMP_POTUS_START_1, TRUMP_POTUS_END_1);
        final var potus2 = svc.createMembership(randStr(), donaldTrump, PRESIDENT_USA, TRUMP_POTUS_START_2, TRUMP_POTUS_END_2);
        final var memberships = svc.createClass(randStr(), Set.of(potus1, potus2));

        // Register Donald Trump as a member of the USA in the role of citizen.
        final var citizen1 = svc.createMembership(randStr(), donaldTrump, CITIZEN_USA, TRUMP_CITIZENSHIP_BEGINS, TRUMP_CITIZENSHIP_ENDS);
        final var citizenships = svc.createClass(randStr(), Set.of(citizen1));

        // Create the US Government
        final Class<Organisation> units = svc.createClass(randStr(), Set.of());
        final var usGovt = svc.createOrganisation(randStr(), memberships, "To govern the USA", units, namesOfTheUsaGov, USA_FROM,
            USA_TO);

        // Sub-organisations of the USA
        final var usaUnits = svc.createClass(randStr(), Set.of(usGovt));

        // Create the USA
        final var usaTerritory = new Territory(randStr(), USA_FROM, USA_TO);
        final var usa = new Nation<>(randStr(), usaTerritory, citizenships, PURPOSE, namesOfTheUsa, usaUnits, USA_FROM, USA_TO);

        assertNotNull(usa);

        JsonUtils.dumpJson(usa);
    }

    private static String randStr() {
        return UUID.randomUUID().toString();
    }

}

record Nation<R extends Role>(String identifier, Territory territory, Class<Membership<R>> members, String purpose,
    Class<Signifier<String>> names, Class<Organisation> units, Formed beginning, Dissolved ending)
    implements Organisation {
}

record Territory(String identifier, Formed beginning, Dissolved ending) implements Individual<Formed, Dissolved> {

}

record PresidentOfTheUsa(String identifier, String name) implements Role {
}

record CitizenOfTheUsa(String identifier, String name) implements Role {
}