package uk.co.aosd.onto.foundation;

/**
 * A numeric property of type U, a Number, which must have a particular unit V, that applies to a set of UniquelyIdentifiable objects of type T.
 *
 * @author Tony Walmsley
 */
public interface ScalarProperty<T extends UniquelyIdentifiable, U extends Number, V extends Unit>
    extends Property<T, ScalarValue<U, V>> {
}
