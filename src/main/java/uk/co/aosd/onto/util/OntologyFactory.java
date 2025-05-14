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
 * @author Tony Walmsley
 */
public interface OntologyFactory {
    
    /**
     * Create a new model with the given identifier.
     * 
     * @param identifier the unique identifier for the model
     * @return a new Model instance
     */
    Model createModel(String identifier);
    
    /**
     * Create a new event with the given parameters.
     * 
     * @param identifier the unique identifier for the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @return a new Event instance
     */
    Event createEvent(String identifier, Instant from, Instant to);
    
    /**
     * Create a new Created event with the given parameters.
     * 
     * @param identifier the unique identifier for the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @return a new Created event instance
     */
    Created createCreatedEvent(String identifier, Instant from, Instant to);
    
    /**
     * Create a new Deleted event with the given parameters.
     * 
     * @param identifier the unique identifier for the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @return a new Deleted event instance
     */
    Deleted createDeletedEvent(String identifier, Instant from, Instant to);
    
    /**
     * Create a new PossibleWorld with the given parameters.
     * 
     * @param identifier the unique identifier for the possible world
     * @param beginning the creation event for the possible world
     * @param ending the deletion event for the possible world
     * @return a new PossibleWorld instance
     */
    PossibleWorld<? extends Created, ? extends Deleted> createPossibleWorld(
            String identifier, Created beginning, Deleted ending);
    
    /**
     * Create a new Individual with the given parameters.
     * 
     * @param identifier the unique identifier for the individual
     * @param beginning the beginning event for the individual
     * @param ending the ending event for the individual
     * @return a new Individual instance
     */
    Individual<? extends Event, ? extends Event> createIndividual(
            String identifier, Event beginning, Event ending);
} 