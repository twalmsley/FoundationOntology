package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.reference.ClassImpl;
import uk.co.aosd.onto.reference.LanguageImpl;
import uk.co.aosd.onto.reference.HumanImpl;
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

        final Signifier<String> personSignifier = new SignifierImpl<>("1", "Alice Cooper", FROM, TO);
        final Class<Signifier<String>> person1Names = new ClassImpl<>("person1Names", Set.of(personSignifier));
        final Language english = new LanguageImpl("en-GB", "British English");
        final Class<Language> languages = new ClassImpl<>("languages1", Set.of(english));
        final Human person1 = new HumanImpl("Alice", TO, FROM, person1Names, english, languages, UNKNOWN_DNA);
        final Membership ceo = new AcmeMembership("ceo", person1, FROM, TO);
        final Set<Membership> memberships = Set.of(ceo);
        final Class<Membership> acmeTeamMemberships = new ClassImpl<>("acmeTopLevelTeam", memberships);
        final Class<Organisation> units = new ClassImpl<>("units1", Set.of());
        final var acmeSignifier = new SignifierImpl<>("2", "ACME Ltd", TO, FROM);
        final Class<Signifier<String>> orgNames = new ClassImpl<>("orgNames1", Set.of(acmeSignifier));
        final Org acme = new Org("org1", acmeTeamMemberships, "ACME makes widgets", units, orgNames, FROM, TO);

        assertNotNull(acme);
    }
}

record Org(String identifier, Class<Membership> members, String purpose, Class<Organisation> units,
    Class<Signifier<String>> names, Optional<Instant> beginning, Optional<Instant> ending) implements Organisation {
}

record AcmeMembership(String identifier, Human member, Optional<Instant> beginning,
    Optional<Instant> ending) implements Membership {
}
