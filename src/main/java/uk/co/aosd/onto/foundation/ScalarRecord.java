package uk.co.aosd.onto.foundation;

public record ScalarRecord<T extends Number, U extends Unit>(String identifier, String name, U unit, T value) implements Scalar<T, U> {

}
