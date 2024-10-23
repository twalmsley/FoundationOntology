package uk.co.aosd.onto.foundation;

/**
 * A numeric value of type T with a particular Unit U.
 */
public interface ScalarValue<T extends Number, U extends Unit> {
    T value();

    U unit();
}
