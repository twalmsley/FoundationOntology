package uk.co.aosd.onto.services;

import java.util.Set;

import org.decimal4j.immutable.Decimal3f;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.events.Disaggregated;
import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.foundation.Agglomerate;
import uk.co.aosd.onto.foundation.Aggregate;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.State;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.foundation.Unit;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.model.Model;
import uk.co.aosd.onto.money.Currency;
import uk.co.aosd.onto.money.MonetaryValue;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.ownership.Owning;
import uk.co.aosd.onto.ownership.TransferringOfOwnership;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * A set of service definitions to be implemented by providers.
 *
 * @author Tony Walmsley
 */
public interface OntologyServices {

    Language createLanguage(String identifier, String name);

    <T> Signifier<T> createSignifier(String identifier, T value, Language language, Resignified from, Resignified to);

    <T extends UniquelyIdentifiable> Class<T> createClass(String identifier, Set<T> members);

    Human createHuman(String identifier, Birth born, Death died, Class<Signifier<String>> names, Language nativeLanguage, Class<Language> languages, DNA dna);

    <R extends Role> Membership<R> createMembership(String identifier, Human human, R role, Appointed from, Removed to);

    <R extends Role> Organisation createOrganisation(String identifier, Class<Membership<R>> memberships, String purpose, Class<Organisation> units,
        Class<Signifier<String>> names, Formed from, Dissolved to);

    PossibleWorld createPossibleWorld(String identifier, Set<Individual<? extends Event, ? extends Event>> parts, Created from, Deleted to);

    <B extends Event, E extends Event, T extends Individual<B, E>> State<B, E, T> createState(String randString, T individual, B from, E to);

    DNA createDna(String identifier, String dna);

    <A extends Event, B extends Event, C extends Event, D extends Event> Owning<A, B, C, D> createOwnership(String identifier, String actionsDescription,
        Individual<A, B> owner, Individual<C, D> owned, TransferredFrom from, TransferredTo to);

    <A extends Event, B extends Event, C extends Event, D extends Event> TransferringOfOwnership<A, B, C, D> transferOwnership(String string,
        String actionsDescription, Owning<A, B, C, D> current, Individual<A, B> newOwner, Started from, Stopped to);

    Currency createCurrency(String identifier, String code, String name, char symbol);

    <U extends Currency> MonetaryValue<U> createMonetaryValue(Decimal3f value, U unit);

    Model createModel(final String identifier);

    Agglomerate createAgglomerate(String identifier, Set<Individual<? extends Event, ? extends Event>> items, Aggregated from, Disaggregated to);

    <N extends Number, U extends Unit, T> Aggregate<N, U, T> createAggregate(String identifier, java.lang.Class<T> kind, ScalarValue<N, U> quantity,
        Aggregated from,
        Disaggregated to);

    <N extends Number, U extends Unit> ScalarValue<N, U> createScalarValue(N value, U unit);
}
