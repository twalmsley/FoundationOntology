package uk.co.aosd.onto.examples;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.model.Model;
import uk.co.aosd.onto.util.OntologyFactory;

/**
 * A simple in-memory implementation of the OntologyFactory interface.
 * This class provides concrete implementations of all the ontology interfaces
 * for testing and example purposes.
 * 
 * @author Generated Example
 */
public class SimpleOntologyFactory implements OntologyFactory {

    @Override
    public Model createModel(String identifier) {
        return new MemoryModel(identifier);
    }

    @Override
    public Event createEvent(String identifier, Instant from, Instant to) {
        return new SimpleEvent(identifier, from, to);
    }

    @Override
    public Created createCreatedEvent(String identifier, Instant from, Instant to) {
        return new SimpleCreatedEvent(identifier, from, to);
    }

    @Override
    public Deleted createDeletedEvent(String identifier, Instant from, Instant to) {
        return new SimpleDeletedEvent(identifier, from, to);
    }

    @Override
    public PossibleWorld<SimpleCreatedEvent, SimpleDeletedEvent> createPossibleWorld(
            String identifier, Created beginning, Deleted ending) {
        SimpleCreatedEvent start = (SimpleCreatedEvent) beginning;
        SimpleDeletedEvent end = (SimpleDeletedEvent) ending;
        return new SimplePossibleWorld(identifier, start, end);
    }

    @Override
    public Individual<SimpleEvent, SimpleEvent> createIndividual(
            String identifier, Event beginning, Event ending) {
        SimpleEvent start = (SimpleEvent) beginning;
        SimpleEvent end = (SimpleEvent) ending;
        return new SimpleIndividual(identifier, start, end);
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
    
    // Simple implementation of Event interface
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
    
    // Simple Created event
    static class SimpleCreatedEvent extends SimpleEvent implements Created {
        public SimpleCreatedEvent(String identifier, Instant from, Instant to) {
            super(identifier, from, to);
        }
    }
    
    // Simple Deleted event
    static class SimpleDeletedEvent extends SimpleEvent implements Deleted {
        public SimpleDeletedEvent(String identifier, Instant from, Instant to) {
            super(identifier, from, to);
        }
    }
    
    // Simple implementation of the Individual interface
    static class SimpleIndividual implements Individual<SimpleEvent, SimpleEvent> {
        private final String identifier;
        private final SimpleEvent beginning;
        private final SimpleEvent ending;
        
        public SimpleIndividual(String identifier, SimpleEvent beginning, SimpleEvent ending) {
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
    
    // Implementation of PossibleWorld
    static class SimplePossibleWorld implements PossibleWorld<SimpleCreatedEvent, SimpleDeletedEvent> {
        private final String identifier;
        private final SimpleCreatedEvent beginning;
        private final SimpleDeletedEvent ending;
        private final Set<Individual<? extends Event, ? extends Event>> parts = new HashSet<>();
        
        public SimplePossibleWorld(String identifier, SimpleCreatedEvent beginning, SimpleDeletedEvent ending) {
            this.identifier = identifier;
            this.beginning = beginning;
            this.ending = ending;
            ensureValid(beginning, ending);
        }
        
        public void addIndividual(Individual<? extends Event, ? extends Event> individual) {
            parts.add(individual);
        }
        
        @Override
        public String getIdentifier() {
            return identifier;
        }
        
        @Override
        public SimpleCreatedEvent getBeginning() {
            return beginning;
        }
        
        @Override
        public SimpleDeletedEvent getEnding() {
            return ending;
        }
        
        @Override
        public Set<Individual<? extends Event, ? extends Event>> getParts() {
            return new HashSet<>(parts);
        }
    }
} 