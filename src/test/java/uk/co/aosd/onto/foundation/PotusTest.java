package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.ClassImpl;
import uk.co.aosd.onto.reference.EventImpl;
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

    private static final DNA UNKNOWN_DNA = null;
    private static final String PURPOSE = "To occupy its territory and serve its people.";
    private static final Event USA_FROM = mkEvent("1776-06-04T00:00:00.00Z", "1776-06-04T23:59:59.99Z");
    private static final Event USA_TO = mkOngoingEvent();
    private static final Event TRUMP_DIED = mkOngoingEvent();
    private static final Event TRUMP_BORN = mkEvent("1946-06-14T00:00:00.00Z", "1946-06-14T23:59:59.99Z");
    private static final Event TRUMP_POTUS_START_1 = mkEvent("2017-01-20T00:00:00.00Z", "2017-01-20T23:59:59.99Z");
    private static final Event TRUMP_POTUS_END_1 = mkEvent("2021-01-20T00:00:00.00Z", "2017-01-20T23:59:59.99Z");
    private static final Event TRUMP_POTUS_START_2 = mkEvent("2025-01-20T00:00:00.00Z", "2017-01-20T23:59:59.99Z");
    private static final Event TRUMP_POTUS_END_2 = mkOngoingEvent();
    private static final Event TRUMP_CITIZENSHIP_ENDS = mkOngoingEvent();

    @Test
    public void test() {

        // Create Donald Trump
        final Signifier<String> personSignifier1 = new SignifierImpl<>(randStr(), "Donald Trump", TRUMP_BORN, TRUMP_DIED);
        final Class<Signifier<String>> person1Names = new ClassImpl<>(randStr(), Set.of(personSignifier1));
        final Language nativeLanguage = new LanguageImpl(randStr(), "American English");
        final Class<Language> languages = new ClassImpl<>(randStr(), Set.of(nativeLanguage));

        final Human donaldTrump = new HumanImpl(randStr(), TRUMP_BORN, TRUMP_DIED, person1Names, nativeLanguage, languages,
            UNKNOWN_DNA);

        // Create the names of the USA
        final Signifier<String> usaSignifier1 = new SignifierImpl<>(randStr(), "USA", USA_FROM, USA_TO);
        final Signifier<String> usaSignifier2 = new SignifierImpl<>(randStr(), "United States of America", USA_FROM,
            USA_TO);
        final Signifier<String> usaSignifier3 = new SignifierImpl<>(randStr(), "US", USA_FROM, USA_TO);
        final Signifier<String> usaSignifier4 = new SignifierImpl<>(randStr(), "United States", USA_FROM, USA_TO);
        final Signifier<String> usaSignifier5 = new SignifierImpl<>(randStr(), "America", USA_FROM, USA_TO);
        final Set<Signifier<String>> setOfSignifiers = Set.of(usaSignifier1, usaSignifier2, usaSignifier3, usaSignifier4, usaSignifier5);
        final Class<Signifier<String>> namesOfTheUsa = new ClassImpl<>(randStr(), setOfSignifiers);

        // Create the name of the USA Government
        final Signifier<String> usaGovSignifier1 = new SignifierImpl<>(randStr(), "The Government of the United States of America", USA_FROM,
            USA_TO);
        final Class<Signifier<String>> namesOfTheUsaGov = new ClassImpl<>(randStr(), Set.of(usaGovSignifier1));

        // Create the POTUS and Citizen roles.
        final Role presidentRole = new RoleImpl("President of the United States of America");
        final Role citizenRole = new RoleImpl("Citizen of the United States of America");

        // Register Donald Trump's memberships of the US Government in the role of POTUS
        final Membership potus1 = new MembershipImpl(randStr(), donaldTrump, presidentRole, TRUMP_POTUS_START_1, TRUMP_POTUS_END_1);
        final Membership potus2 = new MembershipImpl(randStr(), donaldTrump, presidentRole, TRUMP_POTUS_START_2, TRUMP_POTUS_END_2);
        final Class<Membership> memberships = new ClassImpl<>(randStr(), Set.of(potus1, potus2));

        // Register Donald Trump as a member of the USA in the role of citizen.
        final Membership citizen1 = new MembershipImpl(randStr(), donaldTrump, citizenRole, TRUMP_BORN, TRUMP_CITIZENSHIP_ENDS);
        final Class<Membership> citizenships = new ClassImpl<>(randStr(), Set.of(citizen1));

        // Create the US Government
        final Class<Organisation> units = new ClassImpl<>(randStr(), Set.of());
        final Organisation usGovt = new OrganisationImpl(randStr(), memberships, "To govern the USA", units, namesOfTheUsaGov, USA_FROM,
            USA_TO);

        // Sub-organisations of the USA
        final Class<Organisation> usaUnits = new ClassImpl<>(randStr(), Set.of(usGovt));

        // Create the USA
        final Territory usaTerritory = new Territory(randStr(), USA_FROM, USA_TO);
        final Nation usa = new Nation(randStr(), usaTerritory, citizenships, PURPOSE, namesOfTheUsa, usaUnits, USA_FROM,
            USA_TO);

        assertNotNull(usa);

        JsonUtils.dumpJson(usa);
    }

    private static String randStr() {
        return UUID.randomUUID().toString();
    }

    private static Event mkOngoingEvent() {
        return new EventImpl(randStr(), null, null);
    }

    private static Event mkEvent(final String from, final String to) {
        return new EventImpl(randStr(), Instant.parse(from), Instant.parse(to));
    }
}

record Nation(String identifier, Territory territory, Class<Membership> members, String purpose,
    Class<Signifier<String>> names, Class<Organisation> units, Event beginning, Event ending)
    implements Organisation {
}

record Territory(String identifier, Event beginning, Event ending) implements Individual {

}