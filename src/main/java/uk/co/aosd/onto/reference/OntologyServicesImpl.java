package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Set;

import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.foundation.State;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.ownership.Owning;
import uk.co.aosd.onto.ownership.TransferringOfOwnership;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * A reference implementation of the OntologyServices interface.
 *
 * <p>
 * This allows users of the library to code to the ontology interfaces without knowing about the implementation classes directly.
 * </p>
 *
 * @author Tony Walmsley
 */
public class OntologyServicesImpl implements OntologyServices {

    @Override
    public Event createEvent(final String identifier, final Instant from, final Instant to) {
        return new EventImpl(identifier, from, to);
    }

    @Override
    public Language createLanguage(final String identifier, final String name) {
        return new LanguageImpl(identifier, name);
    }

    @Override
    public <T> Signifier<T> createSignifier(final String identifier, final T value, final Language language, final Event from, final Event to) {
        return new SignifierImpl<T>(identifier, value, language, from, to);
    }

    @Override
    public <T extends UniquelyIdentifiable> Class<T> createClass(final String identifier, final Set<T> members) {
        return new ClassImpl<T>(identifier, members);
    }

    @Override
    public Human createHuman(final String identifier, final Event born, final Event died, final Class<Signifier<String>> names, final Language nativeLanguage,
        final Class<Language> languages, final DNA dna) {
        return new HumanImpl(identifier, born, died, names, nativeLanguage, languages, dna);
    }

    @Override
    public Role createRole(final String name) {
        return new RoleImpl(name);
    }

    @Override
    public Membership createMembership(final String identifier, final Human human, final Role role, final Event from, final Event to) {
        return new MembershipImpl(identifier, human, role, from, to);
    }

    @Override
    public Organisation createOrganisation(final String identifier, final Class<Membership> memberships, final String purpose, final Class<Organisation> units,
        final Class<Signifier<String>> names, final Event from, final Event to) {
        return new OrganisationImpl(identifier, memberships, purpose, units, names, from, to);
    }

    @Override
    public PossibleWorld createPossibleWorld(final String identifier, final Set<Individual> parts, final Event from, final Event to) {
        return new PossibleWorldImpl(identifier, parts, from, to);
    }

    @Override
    public Individual createIndividual(final String randString, final Event from, final Event to) {
        return new IndividualImpl(randString, from, to);
    }

    @Override
    public <T extends Individual> State<T> createState(final String identifier, final T individual, final Event from, final Event to) {
        return new StateImpl<T>(identifier, individual, from, to);
    }

    @Override
    public DNA createDna(final String identifier, final String dna) {
        return new DNAImpl(identifier, dna);
    }

    @Override
    public Owning createOwnership(final String identifier, final String actionsDescription, final Individual owner, final Individual owned, final Event from,
        final Event to) {
        return new OwningImpl(identifier, actionsDescription, owner, owned, from, to);
    }

    @Override
    public TransferringOfOwnership transferOwnership(final String identifier, final String actionsDescription, final Owning current, final Individual newOwner,
        final Event from, final Event to) {
        // The previous owneship ends at the from event.
        final var endOwnership = createOwnership(current.identifier(), current.actionsDescription(), current.owner(), current.owned(), current.beginning(),
            from);
        // The new ownership starts at the from event.
        final var newOwnership = createOwnership(identifier, actionsDescription, newOwner, current.owned(), from, to);

        // The transfer happens at the from event and finishes at the from event.
        return new TransferringOfOwnershipImpl(identifier, actionsDescription, endOwnership, newOwnership, from, from);
    }

}
