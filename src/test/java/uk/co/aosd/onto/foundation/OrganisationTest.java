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
import uk.co.aosd.onto.reference.OrganisationImpl;
import uk.co.aosd.onto.reference.SignifierImpl;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * Test that Organisations can be created.
 */
public class OrganisationTest {

    private static final Optional<Instant> FROM = Optional.of(Instant.parse("2024-01-01T00:00:00.00Z"));
    private static final Optional<Instant> TO = Optional.empty();
    private static final Optional<DNA> UNKNOWN_DNA = Optional.empty();

    @Test
    public void testCreateOrganisations() {

        final Signifier<String> personSignifier1 = new SignifierImpl<>(randString(), "Alice Cooper", FROM, TO);
        final Signifier<String> personSignifier2 = new SignifierImpl<>(randString(), "Vincent Damon Furnier", FROM, TO);
        final Signifier<String> acmeSignifier1 = new SignifierImpl<>(randString(), "ACME Widgets Ltd", FROM, TO);
        final Signifier<String> acmeSignifier2 = new SignifierImpl<>(randString(), "ACME Ltd", FROM, TO);

        final Class<Signifier<String>> person1Names = new ClassImpl<>(randString(),
            Set.of(personSignifier1, personSignifier2));
        final Class<Signifier<String>> orgNames = new ClassImpl<>(randString(), Set.of(acmeSignifier1, acmeSignifier2));

        final Language english = new LanguageImpl(randString(), "British English");
        final Language german = new LanguageImpl(randString(), "Deutsch");
        final Class<Language> languages = new ClassImpl<>(randString(), Set.of(english, german));
        final Human alice = new HumanImpl(randString(), FROM, TO, person1Names, english, languages, UNKNOWN_DNA);

        final Membership ceoMembership = new AcmeMembership(randString(), alice, FROM, TO);
        final Set<Membership> memberships = Set.of(ceoMembership);
        final Class<Membership> acmeTeamMemberships = new ClassImpl<>(randString(), memberships);

        final Class<Organisation> units = new ClassImpl<>(randString(), Set.of());
        final OrganisationImpl acme = new OrganisationImpl(randString(), acmeTeamMemberships, "ACME makes widgets", units, orgNames, FROM, TO);

        assertNotNull(acme);
    }
    
    private static String randString() {
        return UUID.randomUUID().toString();
    }
}

record AcmeMembership(String identifier, Human member, Optional<Instant> beginning,
    Optional<Instant> ending) implements Membership {
}
