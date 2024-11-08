package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.HashSet;
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
import uk.co.aosd.onto.reference.EventImpl;
import uk.co.aosd.onto.reference.HumanImpl;
import uk.co.aosd.onto.reference.LanguageImpl;
import uk.co.aosd.onto.reference.MembershipImpl;
import uk.co.aosd.onto.reference.OrganisationImpl;
import uk.co.aosd.onto.reference.PossibleWorldImpl;
import uk.co.aosd.onto.reference.RoleImpl;
import uk.co.aosd.onto.reference.SignifierImpl;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * Test that Organisations can be created.
 *
 * @author Tony Walmsley
 */
public class OrganisationTest {

    private static final Optional<Instant> FROM = Optional.of(Instant.parse("2024-01-01T00:00:00.00Z"));
    private static final Optional<Instant> TO = Optional.empty();
    private static final Optional<Instant> UNKNOWN_TIME = Optional.empty();
    private static final Optional<DNA> UNKNOWN_DNA = Optional.empty();

    @Test
    public void testCreateOrganisations() {

        // Create the required Events
        final Event born = new EventImpl(randString(), FROM, TO);
        final Event died = new EventImpl(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final Event incorporated = new EventImpl(randString(), FROM, TO);
        final Event dissolved = new EventImpl(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final Event appointed = new EventImpl(randString(), FROM, TO);
        final Event dismissed = new EventImpl(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final Event epochStart = new EventImpl(randString(), UNKNOWN_TIME, UNKNOWN_TIME);
        final Event epochEnd = new EventImpl(randString(), UNKNOWN_TIME, UNKNOWN_TIME);

        // Create some Signifiers for the person and the organisation.
        final Signifier<String> personSignifier1 = new SignifierImpl<>(randString(), "Alice Cooper", born, died);
        final Signifier<String> personSignifier2 = new SignifierImpl<>(randString(), "Vincent Damon Furnier", born, died);
        final Signifier<String> acmeSignifier1 = new SignifierImpl<>(randString(), "ACME Widgets Ltd", incorporated, dissolved);
        final Signifier<String> acmeSignifier2 = new SignifierImpl<>(randString(), "ACME Ltd", incorporated, dissolved);

        // The signifiers need to be added to Classes (Sets)
        final Class<Signifier<String>> person1Names = new ClassImpl<>(randString(), Set.of(personSignifier1, personSignifier2));
        final Class<Signifier<String>> orgNames = new ClassImpl<>(randString(), Set.of(acmeSignifier1, acmeSignifier2));

        // Create the languages that the person uses.
        final Language english = new LanguageImpl(randString(), "British English");
        final Language german = new LanguageImpl(randString(), "Deutsch");
        final Class<Language> languages = new ClassImpl<>(randString(), Set.of(english, german));

        // Create the person
        final Human alice = new HumanImpl(randString(), born, died, person1Names, english, languages, UNKNOWN_DNA);

        // Create a Class of memberships for the person as a member of something.
        final Role ceoRole = new RoleImpl("CEO");
        final Membership ceoMembership = new MembershipImpl(randString(), alice, ceoRole, appointed, dismissed);
        final Class<Membership> acmeTeamMemberships = new ClassImpl<>(randString(), Set.of(ceoMembership));

        // Create an organisation with memberships and no sub-units.
        final Class<Organisation> units = new ClassImpl<>(randString(), Set.of());
        final OrganisationImpl acme = new OrganisationImpl(randString(), acmeTeamMemberships, "ACME makes widgets", units, orgNames, incorporated, dissolved);

        assertNotNull(acme);

        // Add the objects to a Possible World
        final PossibleWorld world = new PossibleWorldImpl(randString(), new HashSet<>(), epochStart, epochEnd);
        world.parts().add(personSignifier1);
        world.parts().add(personSignifier2);
        world.parts().add(acmeSignifier1);
        world.parts().add(acmeSignifier2);
        world.parts().add(alice);
        world.parts().add(ceoMembership);
        world.parts().add(acme);
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}