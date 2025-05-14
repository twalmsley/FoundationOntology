package uk.co.aosd.onto.examples;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.PossibleWorld;
import uk.co.aosd.onto.foundation.TimePeriod;

/**
 * Example demonstrating the use of PossibleWorld interface.
 * This shows how to create different possible scenarios and add individuals to them.
 */
public class PossibleWorldExample {

    public static void main(String[] args) {
        // Create some events that start and end our possible worlds
        SimpleCreatedEvent planCreation = new SimpleCreatedEvent("plan-creation", 
                Instant.parse("2023-01-01T00:00:00Z"), 
                Instant.parse("2023-01-01T00:01:00Z"));
        
        SimpleDeletedEvent planDeletion = new SimpleDeletedEvent("plan-deletion", 
                Instant.parse("2025-12-31T23:59:00Z"), 
                Instant.parse("2025-12-31T23:59:59Z"));
        
        // Create individuals that will exist in our possible world
        SimpleEvent personBirth = new SimpleEvent("birth-event", 
                Instant.parse("2023-02-01T00:00:00Z"), 
                Instant.parse("2023-02-01T00:01:00Z"));
        
        SimpleEvent personDeath = new SimpleEvent("death-event", 
                Instant.parse("2024-02-01T00:00:00Z"), 
                Instant.parse("2024-02-01T00:01:00Z"));
        
        SimplePerson person = new SimplePerson("person-1", personBirth, personDeath);
        
        // Create a possible world (a project plan)
        ProjectPlan projectPlan = new ProjectPlan("project-plan-2023", planCreation, planDeletion);
        projectPlan.addIndividual(person);
        
        // Display information about the plan
        System.out.println("Project plan: " + projectPlan.getIdentifier());
        System.out.println("Plan created: " + projectPlan.getBeginning().getFrom());
        System.out.println("Plan valid until: " + projectPlan.getEnding().getTo());
        System.out.println("Plan contains " + projectPlan.getParts().size() + " individuals");
        
        // Create an alternative scenario
        SimpleCreatedEvent altPlanCreation = new SimpleCreatedEvent("alt-plan-creation", 
                Instant.parse("2023-01-15T00:00:00Z"), 
                Instant.parse("2023-01-15T00:01:00Z"));
        
        SimpleDeletedEvent altPlanDeletion = new SimpleDeletedEvent("alt-plan-deletion", 
                Instant.parse("2026-06-30T23:59:00Z"), 
                Instant.parse("2026-06-30T23:59:59Z"));
        
        // Create a second possible world (alternative plan)
        ProjectPlan alternativePlan = new ProjectPlan("alternative-plan", altPlanCreation, altPlanDeletion);
        
        // Add different individuals to this plan
        SimpleEvent altPersonBirth = new SimpleEvent("alt-birth-event", 
                Instant.parse("2023-03-01T00:00:00Z"), 
                Instant.parse("2023-03-01T00:01:00Z"));
        
        SimpleEvent altPersonDeath = new SimpleEvent("alt-death-event", 
                Instant.parse("2025-03-01T00:00:00Z"), 
                Instant.parse("2025-03-01T00:01:00Z"));
        
        SimplePerson altPerson = new SimplePerson("alt-person", altPersonBirth, altPersonDeath);
        alternativePlan.addIndividual(altPerson);
        
        // Compare the two scenarios
        System.out.println("\nComparison of plans:");
        System.out.println("Original plan duration: " + projectPlan.duration().orElse(null));
        System.out.println("Alternative plan duration: " + alternativePlan.duration().orElse(null));
        
        if (alternativePlan.getEnding().getTo().isAfter(projectPlan.getEnding().getTo())) {
            System.out.println("The alternative plan extends beyond the original plan.");
        }
    }
    
    // Implementation of PossibleWorld
    static class ProjectPlan implements PossibleWorld<SimpleCreatedEvent, SimpleDeletedEvent>, TimePeriod {
        private final String identifier;
        private final SimpleCreatedEvent beginning;
        private final SimpleDeletedEvent ending;
        private final Set<Individual<? extends Event, ? extends Event>> parts = new HashSet<>();
        
        public ProjectPlan(String identifier, SimpleCreatedEvent beginning, SimpleDeletedEvent ending) {
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
        
        @Override
        public Instant getFrom() {
            return beginning.getFrom();
        }
        
        @Override
        public Instant getTo() {
            return ending.getTo();
        }
        
        /**
         * Calculate the duration of this project plan.
         * 
         * @return The duration between the beginning and ending of this plan
         */
        public Optional<Duration> duration() {
            return TimePeriod.super.duration();
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