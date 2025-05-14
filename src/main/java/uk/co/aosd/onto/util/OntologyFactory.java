package uk.co.aosd.onto.util;

import java.time.Instant;

import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;
import uk.co.aosd.onto.model.Model;

/**
 * Factory interface for creating ontology entities.
 * This provides a standardized way for implementations to create 
 * instances of the ontology interfaces without exposing the specific
 * implementation details.
 * 
 * <p>The OntologyFactory follows the Factory design pattern to abstract the creation
 * of ontology entities. This pattern provides several benefits:</p>
 * 
 * <ul>
 *   <li>Encapsulates implementation details of entity creation</li>
 *   <li>Allows for switching between different implementations without changing client code</li>
 *   <li>Centralizes entity creation logic</li>
 *   <li>Supports consistency in how entities are created</li>
 * </ul>
 * 
 * <p>Clients should use this factory interface rather than directly instantiating
 * implementation classes. This approach ensures that code remains decoupled from
 * specific implementations and can work with any conforming implementation.</p>
 * 
 * <p>Different factory implementations might use different strategies for:</p>
 * <ul>
 *   <li>Storage (in-memory, database, etc.)</li>
 *   <li>Validation</li>
 *   <li>Identifier generation</li>
 *   <li>Event handling</li>
 * </ul>
 * 
 * <p>For example applications, see the {@code SimpleOntologyFactory} and {@code FactoryExample} classes.</p>
 * 
 * @author Tony Walmsley
 * @see Model
 * @see Event
 * @see Individual
 * @see PossibleWorld
 */
public interface OntologyFactory {
    
    /**
     * Create a new model with the given identifier.
     * 
     * <p>This method creates a new Model instance with the specified identifier.
     * The returned Model will be implementation-specific but will conform to
     * the Model interface.</p>
     * 
     * @param identifier the unique identifier for the model
     * @return a new Model instance
     */
    Model createModel(String identifier);
    
    /**
     * Create a new event with the given parameters.
     * 
     * <p>This method creates a generic Event that represents a significant
     * occurrence at a specific time. The event has a beginning and ending time
     * point, which may be the same for instantaneous events.</p>
     * 
     * @param identifier the unique identifier for the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @return a new Event instance
     * @throws IllegalArgumentException if from is after to
     */
    Event createEvent(String identifier, Instant from, Instant to);
    
    /**
     * Create a new Created event with the given parameters.
     * 
     * <p>A Created event is a specialized event that represents the creation
     * or coming into existence of something. It is typically used as the
     * beginning event for an Individual or PossibleWorld.</p>
     * 
     * @param identifier the unique identifier for the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @return a new Created event instance
     * @throws IllegalArgumentException if from is after to
     */
    Created createCreatedEvent(String identifier, Instant from, Instant to);
    
    /**
     * Create a new Deleted event with the given parameters.
     * 
     * <p>A Deleted event is a specialized event that represents the deletion
     * or ceasing to exist of something. It is typically used as the
     * ending event for an Individual or PossibleWorld.</p>
     * 
     * @param identifier the unique identifier for the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @return a new Deleted event instance
     * @throws IllegalArgumentException if from is after to
     */
    Deleted createDeletedEvent(String identifier, Instant from, Instant to);
    
    /**
     * Create a new PossibleWorld with the given parameters.
     * 
     * <p>A PossibleWorld represents a complete way the world is or could have been.
     * It can represent scenarios, plans, fictional worlds, or the actual world.
     * PossibleWorlds contain sets of Individuals that exist within them.</p>
     * 
     * @param identifier the unique identifier for the possible world
     * @param beginning the creation event for the possible world
     * @param ending the deletion event for the possible world
     * @return a new PossibleWorld instance
     * @throws IllegalArgumentException if temporal constraints are violated
     */
    PossibleWorld<? extends Created, ? extends Deleted> createPossibleWorld(
            String identifier, Created beginning, Deleted ending);
    
    /**
     * Create a new Individual with the given parameters.
     * 
     * <p>An Individual represents a discrete entity that exists in time.
     * It is bounded by beginning and ending events that mark its
     * existence period.</p>
     * 
     * @param identifier the unique identifier for the individual
     * @param beginning the beginning event for the individual
     * @param ending the ending event for the individual
     * @return a new Individual instance
     * @throws IllegalArgumentException if temporal constraints are violated
     */
    Individual<? extends Event, ? extends Event> createIndividual(
            String identifier, Event beginning, Event ending);
} 