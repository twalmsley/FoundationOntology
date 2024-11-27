package uk.co.aosd.onto.foundation;

/**
 * A numeric value of type T with a particular Unit U.
 *
 * @author Tony Walmsley
 */
public interface ScalarValue<T extends Number, U extends Unit> {
    T getValue();

    U getUnit();
}
