package uk.co.aosd.onto.foundation;

public interface Property<T extends UniquelyIdentifiable, U> extends Class<T> {
    U property();
}
