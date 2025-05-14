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
