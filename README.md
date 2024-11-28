# Foundation Ontology

This library is a work-in-progress to define a Top Level Ontology in Java that is compatible with [HQDM](https://github.com/hqdmTop/hqdmFramework) and easier for developers to use than [MagmaCore](https://github.com/gchq/MagmaCore). Only time will tell if this will be successful.

The library defines a set of interfaces that can be implemented in other libraries to provide features such as persistence. Currently the following libraries exist:
- [FoundationOntologyMem](https://github.com/twalmsley/FoundationOntologyMem) which is an in-memory implemenation useful for testing and experimentation.
- [FoundationOntologyJpa](https://github.com/twalmsley/FoundationOntologyJPA) which is a Java Persistence API implemenation for use with a relational database.

**Note: For the moment the focus will be on the foundation package and the other packages will be developed fully later.**

See [this blog post](https://twalmsley.github.io/blog1/blog1.html) for a discussion of HQDM and [this blog post](https://twalmsley.github.io/blog2/blog2.html) for the background to this library.

![UML Diagram](./diagrams/uml.png)

# Getting Involved

Feel free to raise issues, but it will be best to start a discussion first then issues can be raised once a discussion has progressed far enough to draw some concrete conclusions.

# The Packages

## Foundation

This package contains the foundation interfaces that other classes and records must implement to be recognised as belonging to this ontology.

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
