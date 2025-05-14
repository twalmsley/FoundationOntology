package uk.co.aosd.onto.examples;

import java.time.Instant;

import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;
import uk.co.aosd.onto.model.Model;
import uk.co.aosd.onto.util.OntologyFactory;

/**
 * Example demonstrating how to use the OntologyFactory interface
 * to create and manipulate ontology entities.
 */
public class FactoryExample {

    public static void main(String[] args) {
        // Create a factory instance
        OntologyFactory factory = new SimpleOntologyFactory();
        
        // Create a model using the factory
        Model model = factory.createModel("factory-example-model");
        
        // Create events
        Event birthEvent = factory.createEvent("birth-event", 
                Instant.parse("2023-01-01T00:00:00Z"), 
                Instant.parse("2023-01-01T00:01:00Z"));
        
        Event deathEvent = factory.createEvent("death-event", 
                Instant.parse("2023-12-31T23:59:00Z"), 
                Instant.parse("2023-12-31T23:59:59Z"));
        
        // Create an individual bounded by these events
        Individual<? extends Event, ? extends Event> person = 
                factory.createIndividual("person-1", birthEvent, deathEvent);
        
        // Add everything to the model
        model.add(birthEvent);
        model.add(deathEvent);
        model.add(person);
        
        // Create events for a possible world
        Created worldCreation = factory.createCreatedEvent("world-creation", 
                Instant.parse("2022-01-01T00:00:00Z"), 
                Instant.parse("2022-01-01T00:01:00Z"));
        
        Deleted worldDeletion = factory.createDeletedEvent("world-deletion", 
                Instant.parse("2024-12-31T23:59:00Z"), 
                Instant.parse("2024-12-31T23:59:59Z"));
        
        // Create a possible world
        PossibleWorld<? extends Created, ? extends Deleted> possibleWorld = 
                factory.createPossibleWorld("scenario-1", worldCreation, worldDeletion);
        
        // Add the possible world to the model
        model.add(possibleWorld);
        model.add(worldCreation);
        model.add(worldDeletion);
        
        // Display information about the model
        System.out.println("Model contains " + model.getThings().size() + " things:");
        model.getThings().forEach(thing -> {
            System.out.println("- " + thing.getIdentifier());
        });
        
        // Access the individual by its identifier
        model.getThing("person-1").ifPresent(thing -> {
            if (thing instanceof Individual) {
                @SuppressWarnings("unchecked")
                Individual<? extends Event, ? extends Event> individual = 
                        (Individual<? extends Event, ? extends Event>) thing;
                
                System.out.println("\nIndividual: " + individual.getIdentifier());
                System.out.println("Beginning: " + individual.getBeginning().getIdentifier() + 
                        " at " + individual.getBeginning().getFrom());
                System.out.println("Ending: " + individual.getEnding().getIdentifier() + 
                        " at " + individual.getEnding().getTo());
            }
        });
        
        // Access the possible world by its identifier
        model.getThing("scenario-1").ifPresent(thing -> {
            if (thing instanceof PossibleWorld) {
                @SuppressWarnings("unchecked")
                PossibleWorld<? extends Created, ? extends Deleted> world = 
                        (PossibleWorld<? extends Created, ? extends Deleted>) thing;
                
                System.out.println("\nPossible World: " + world.getIdentifier());
                System.out.println("Created: " + world.getBeginning().getIdentifier() + 
                        " at " + world.getBeginning().getFrom());
                System.out.println("Deleted: " + world.getEnding().getIdentifier() + 
                        " at " + world.getEnding().getTo());
                System.out.println("Contains " + world.getParts().size() + " individuals");
            }
        });
    }
} 