# Foundation Ontology

This library is a work-in-progress to define a Top Level Ontology in Java that is compatible with [HQDM](https://github.com/hqdmTop/hqdmFramework) and easier for developers to use than [MagmaCore](https://github.com/gchq/MagmaCore). Only time will tell if this will be successful.

The library defines a set of interfaces that can be implemented in other libraries to provide features such as persistence. Currently the following libraries exist:
- [FoundationOntologyMem](https://github.com/twalmsley/FoundationOntologyMem) which is an in-memory implemenation useful for testing and experimentation.
- [FoundationOntologyJpa](https://github.com/twalmsley/FoundationOntologyJPA) which is a Java Persistence API implemenation for use with a relational database.

**Note: For the moment the focus will be on the foundation package and the other packages will be developed fully later.**

See [this blog post](https://twalmsley.github.io/blog1/blog1.html) for a discussion of HQDM and [this blog post](https://twalmsley.github.io/blog2/blog2.html) for the background to this library.

![UML Diagram](./diagrams/uml.png)

## Architecture Overview

The Foundation Ontology is designed as a set of interfaces that define the core concepts of the ontology. These interfaces are meant to be implemented by concrete classes in your application or in separate implementation libraries.

Key architectural principles:
- **Interface-based design**: All ontology concepts are defined as interfaces
- **Temporal awareness**: Most entities have a beginning and ending in time
- **Unique identification**: All entities have a unique identifier
- **Composability**: Complex entities can be built from simpler ones
- **Factory pattern**: Entity creation is abstracted through factory interfaces

### Core Design Patterns

The ontology uses several design patterns to promote good software engineering practices:

1. **Factory Pattern**: The `OntologyFactory` interface provides a standard way to create ontology entities without exposing implementation details. This allows different implementations (memory-based, JPA-based, etc.) to be used interchangeably.

2. **Interface Segregation**: The ontology is composed of small, focused interfaces that can be combined to create more complex behaviors.

3. **Temporal Modeling**: Entities exist in time and are bounded by events that mark their beginning and end.

4. **Composition over Inheritance**: The design favors composition of interfaces over deep class hierarchies.

### Project Structure

```
src/main/java/uk/co/aosd/onto/
├── foundation/       # Core ontology interfaces
├── biological/       # Biological entity interfaces
├── events/           # Specific event type interfaces
├── language/         # Language representation interfaces
├── model/            # Model interfaces for knowledge representation
├── money/            # Monetary value interfaces
├── organisation/     # Organisation structure interfaces
├── ownership/        # Ownership representation interfaces
├── signifying/       # Sign and signification interfaces
├── units/            # Unit interfaces for measurements
└── util/             # Utility interfaces and classes
```

## Getting Started

To use the Foundation Ontology in your project:

1. Add the dependency to your pom.xml:
```xml
<dependency>
    <groupId>uk.co.aosd.onto</groupId>
    <artifactId>foundation-ontology</artifactId>
    <version>0.0.11-SNAPSHOT</version>
</dependency>
```

2. Choose an implementation library:
```xml
<!-- For in-memory implementation -->
<dependency>
    <groupId>uk.co.aosd.onto</groupId>
    <artifactId>foundation-ontology-mem</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

<!-- OR for JPA implementation -->
<dependency>
    <groupId>uk.co.aosd.onto</groupId>
    <artifactId>foundation-ontology-jpa</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

3. See the examples in the `src/test/java/uk/co/aosd/onto/examples` directory for usage patterns.

## Usage Examples

### Creating a Simple Model

```java
// Create a simple model
Model model = new MemoryModel("example-model");

// Create events that bound an individual
Event birthEvent = new SimpleEvent("birth-event", 
        Instant.parse("2023-01-01T00:00:00Z"), 
        Instant.parse("2023-01-01T00:01:00Z"));

Event deathEvent = new SimpleEvent("death-event", 
        Instant.parse("2023-12-31T23:59:00Z"), 
        Instant.parse("2023-12-31T23:59:59Z"));

// Create an individual bounded by these events
Individual<Event, Event> person = new SimplePerson("person-1", birthEvent, deathEvent);

// Add everything to the model
model.add(birthEvent);
model.add(deathEvent);
model.add(person);
```

### Using the Factory Pattern

The recommended approach is to use the factory pattern, which provides better abstraction and flexibility:

```java
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
```

### Working with Possible Worlds

Possible Worlds represent different scenarios or models of reality:

```java
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

// Add individuals to the possible world
// This would depend on your implementation of PossibleWorld
```

## Example Classes

The project includes several example implementations to demonstrate how to use the ontology:

1. **SimpleModelExample**: Shows basic usage of the core interfaces.
2. **PossibleWorldExample**: Demonstrates how to create and use possible worlds.
3. **SimpleOntologyFactory**: Provides an in-memory implementation of the factory pattern.
4. **FactoryExample**: Shows how to use the factory pattern to create and manipulate entities.

These examples can be found in the `src/test/java/uk/co/aosd/onto/examples` directory.

## Getting Involved

Feel free to raise issues, but it will be best to start a discussion first then issues can be raised once a discussion has progressed far enough to draw some concrete conclusions.

# The Packages

## Foundation

This package contains the foundation interfaces that other classes and records must implement to be recognised as belonging to this ontology.

Key interfaces include:
- `UniquelyIdentifiable` - Base interface for all entities with a unique identifier
- `Event` - Represents a significant occurrence or happening
- `Individual` - An object considered as a single thing, bounded by events
- `TimePeriod` - Represents something with a beginning and end in time
- `Class` - Represents a set of objects of a particular type
- `PossibleWorld` - Represents a complete way the world is or could have been
- `EventBounded` - Represents entities bounded by beginning and ending events

## Biological

This package is for representing biological entities.

## Language

A representation of Languages, which this ontology considers a distinguishing feature of Humans.

## Organisation

Interfaces for representing organisations and their general structure. It can be used as-is or extended for more specific kinds of organisations.

## Ownership

A representation of the ownership of things.

## Signifying

Models the representation of things by signs.

## Units

Defines some standard units for ScalarValues and MonetaryValues.

## Events

Contains specialized event types that represent specific kinds of occurrences, such as:
- `Created` - When something comes into existence
- `Deleted` - When something ceases to exist
- `Birth` - The beginning of a biological organism
- `Death` - The ending of a biological organism
- `Started` - When an activity begins
- `Stopped` - When an activity ends

## Util

Utility interfaces and classes that support the ontology, including:
- `Range` - Represents uncertainty over some value
- `OntologyFactory` - Factory interface for creating ontology entities
