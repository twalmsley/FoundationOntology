package uk.co.aosd.onto.foundation;

/**
 * A quantity of similar things, e.g. a pile of sand or a collection of water molecules in a glass of water.
 */
public interface Aggregation<T extends Number, U extends Unit> extends Individual<T, U> {
    ScalarValue<T, U> quantity();
}
