package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.ClassImpl;
import uk.co.aosd.onto.reference.HumanImpl;
import uk.co.aosd.onto.reference.LanguageImpl;
import uk.co.aosd.onto.reference.MembershipImpl;
import uk.co.aosd.onto.reference.OrganisationImpl;
import uk.co.aosd.onto.reference.RoleImpl;
import uk.co.aosd.onto.reference.SignifierImpl;
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

    private static final String PURPOSE = "To occupy its territory and serve its people.";
    private static final Optional<DNA> UNKNOWN_DNA = Optional.empty();
    private static final Optional<Instant> USA_FROM = Optional.of(Instant.parse("1776-06-04T00:00:00.00Z"));
    private static final Optional<Instant> ONGOING = Optional.empty();
    private static final Optional<Instant> TRUMP_FROM = Optional.of(Instant.parse("1946-06-14T00:00:00.00Z"));
    private static final Optional<Instant> TRUMP_POTUS_START_1 = Optional.of(Instant.parse("2017-01-20T00:00:00.00Z"));
    private static final Optional<Instant> TRUMP_POTUS_END_1 = Optional.of(Instant.parse("2021-01-20T00:00:00.00Z"));
    private static final Optional<Instant> TRUMP_POTUS_START_2 = Optional.of(Instant.parse("2025-01-20T00:00:00.00Z"));

    @Test
    public void test() {

        // Create Donald Trump
        final Signifier<String> personSignifier1 = new SignifierImpl<>(randStr(), "Donald Trump", TRUMP_FROM, ONGOING);
        final Class<Signifier<String>> person1Names = new ClassImpl<>(randStr(), Set.of(personSignifier1));
        final Language nativeLanguage = new LanguageImpl(randStr(), "American English");
        final Class<Language> languages = new ClassImpl<>(randStr(), Set.of(nativeLanguage));

        final Human donaldTrump = new HumanImpl(randStr(), TRUMP_FROM, ONGOING, person1Names, nativeLanguage, languages, UNKNOWN_DNA);

        // Create the names of the USA
        final Signifier<String> usaSignifier1 = new SignifierImpl<>(randStr(), "USA", USA_FROM, ONGOING);
        final Signifier<String> usaSignifier2 = new SignifierImpl<>(randStr(), "United States of America", USA_FROM, ONGOING);
        final Signifier<String> usaSignifier3 = new SignifierImpl<>(randStr(), "US", USA_FROM, ONGOING);
        final Signifier<String> usaSignifier4 = new SignifierImpl<>(randStr(), "United States", USA_FROM, ONGOING);
        final Signifier<String> usaSignifier5 = new SignifierImpl<>(randStr(), "America", USA_FROM, ONGOING);
        final Set<Signifier<String>> setOfSignifiers = Set.of(usaSignifier1, usaSignifier2, usaSignifier3, usaSignifier4, usaSignifier5);
        final Class<Signifier<String>> namesOfTheUsa = new ClassImpl<>(randStr(), setOfSignifiers);

        // Create the name of the USA Government
        final Signifier<String> usaGovSignifier1 = new SignifierImpl<>(randStr(), "The Government of the United States of America", USA_FROM, ONGOING);
        final Class<Signifier<String>> namesOfTheUsaGov = new ClassImpl<>(randStr(), Set.of(usaGovSignifier1));

        // Create the POTUS and Citizen roles.
        final Role presidentRole = new RoleImpl("President of the United States of America");
        final Role citizenRole = new RoleImpl("Citizen of the United States of America");

        // Register Donald Trump's memberships of the US Government in the role of POTUS
        final Membership potus1 = new MembershipImpl(randStr(), donaldTrump, presidentRole, TRUMP_POTUS_START_1, TRUMP_POTUS_END_1);
        final Membership potus2 = new MembershipImpl(randStr(), donaldTrump, presidentRole, TRUMP_POTUS_START_2, ONGOING);
        final Class<Membership> memberships = new ClassImpl<>(randStr(), Set.of(potus1, potus2));

        // Register Donald Trump as a member of the USA in the role of citizen.
        final Membership citizen1 = new MembershipImpl(randStr(), donaldTrump, citizenRole, TRUMP_FROM, ONGOING);
        final Class<Membership> citizenships = new ClassImpl<>(randStr(), Set.of(citizen1));

        // Create the US Government
        final Class<Organisation> units = new ClassImpl<>(randStr(), Set.of());
        final Organisation usGovt = new OrganisationImpl(randStr(), memberships, "To govern the USA", units, namesOfTheUsaGov, USA_FROM, ONGOING);

        // Sub-organisations of the USA
        final Class<Organisation> usaUnits = new ClassImpl<>(randStr(), Set.of(usGovt));

        // Create the USA
        final Territory usaTerritory = new Territory(randStr(), USA_FROM, ONGOING);
        final Nation usa = new Nation(randStr(), usaTerritory, citizenships, PURPOSE, namesOfTheUsa, usaUnits, USA_FROM, ONGOING);

        assertNotNull(usa);
    }

    private static String randStr() {
        return UUID.randomUUID().toString();
    }
}

record Nation(String identifier, Territory territory, Class<Membership> members, String purpose,
    Class<Signifier<String>> names, Class<Organisation> units, Optional<Instant> beginning, Optional<Instant> ending)
    implements Organisation {
}

record Territory(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {

}