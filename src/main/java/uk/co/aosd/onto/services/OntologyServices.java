package uk.co.aosd.onto.services;

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
import uk.co.aosd.onto.signifying.Signifier;

/**
 * A set of service definitions to be implemented by providers.
 *
 * @author Tony Walmsley
 */
public interface OntologyServices {

    Event createEvent(String identifier, Instant from, Instant to);

    Language createLanguage(String identifier, String name);

    <T> Signifier<T> createSignifier(String identifier, T value, Language language, Event from, Event to);

    <T extends UniquelyIdentifiable> Class<T> createClass(String identifier, Set<T> members);

    Human createHuman(String identifier, Event born, Event died, Class<Signifier<String>> names, Language nativeLanguage, Class<Language> languages, DNA dna);

    Role createRole(String name);

    Membership createMembership(String identifier, Human human, Role role, Event from, Event to);

    Organisation createOrganisation(String identifier, Class<Membership> memberships, String purpose, Class<Organisation> units, Class<Signifier<String>> names,
        Event from, Event to);

    PossibleWorld createPossibleWorld(String identifier, Set<Individual> parts, Event from, Event to);

    Individual createIndividual(String randString, Event from, Event to);

    <T extends Individual> State<T> createState(String randString, T individual, Event from, Event to);
}
