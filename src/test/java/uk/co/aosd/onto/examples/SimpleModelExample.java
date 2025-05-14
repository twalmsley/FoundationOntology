package uk.co.aosd.onto.examples;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.model.Model;

/**
 * A simple example implementation of the ontology interfaces.
 * This demonstrates how to create and use the base interfaces.
 * 
 * @author Generated Example
 */
public class SimpleModelExample {

    public static void main(String[] args) {
        // Create a simple model
        MemoryModel model = new MemoryModel("example-model");
        
        // Create some events
        SimpleEvent birthEvent = new SimpleEvent("birth-event", 
                Instant.parse("2023-01-01T00:00:00Z"), 
                Instant.parse("2023-01-01T00:01:00Z"));
        
        SimpleEvent deathEvent = new SimpleEvent("death-event", 
                Instant.parse("2023-12-31T23:59:00Z"), 
                Instant.parse("2023-12-31T23:59:59Z"));
        
        // Create an individual bounded by these events
        SimplePerson person = new SimplePerson("person-1", birthEvent, deathEvent);
        
        // Add everything to the model
        model.add(birthEvent);
        model.add(deathEvent);
        model.add(person);
        
        // Retrieve and display information
        System.out.println("Model contains " + model.getThings().size() + " things");
        
        // Calculate the duration of the individual's existence
        person.range().ifPresent(range -> 
            System.out.println("Person existed for between " + 
                range.min().toDays() + " and " + 
                range.max().toDays() + " days"));
    }
    
    // Simple implementation of the Model interface
    static class MemoryModel implements Model {
        private final String identifier;
        private final Map<String, UniquelyIdentifiable> things = new HashMap<>();
        
        public MemoryModel(String identifier) {
            this.identifier = identifier;
        }
        
        @Override
        public String getIdentifier() {
            return identifier;
        }
        
        @Override
        public void add(UniquelyIdentifiable thing) {
            things.put(thing.getIdentifier(), thing);
        }
        
        @Override
        public Optional<UniquelyIdentifiable> getThing(String identifier) {
            return Optional.ofNullable(things.get(identifier));
        }
        
        @Override
        public Set<UniquelyIdentifiable> getThings() {
            return new HashSet<>(things.values());
        }
    }
    
    // Simple implementation of the Event interface
    static class SimpleEvent implements Event {
        private final String identifier;
        private final Instant from;
        private final Instant to;
        
        public SimpleEvent(String identifier, Instant from, Instant to) {
            this.identifier = identifier;
            this.from = from;
            this.to = to;
            ensureValid(from, to);
        }
        
        @Override
        public String getIdentifier() {
            return identifier;
        }
        
        @Override
        public Instant getFrom() {
            return from;
        }
        
        @Override
        public Instant getTo() {
            return to;
        }
    }
    
    // Simple implementation of the Individual interface
    static class SimplePerson implements Individual<SimpleEvent, SimpleEvent> {
        private final String identifier;
        private final SimpleEvent beginning;
        private final SimpleEvent ending;
        
        public SimplePerson(String identifier, SimpleEvent beginning, SimpleEvent ending) {
            this.identifier = identifier;
            this.beginning = beginning;
            this.ending = ending;
            ensureValid(beginning, ending);
        }
        
        @Override
        public String getIdentifier() {
            return identifier;
        }
        
        @Override
        public SimpleEvent getBeginning() {
            return beginning;
        }
        
        @Override
        public SimpleEvent getEnding() {
            return ending;
        }
    }
} 