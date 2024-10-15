package uk.co.aosd.onto.foundation;

public interface ScalarProperty<T extends UniquelyIdentifiable, U extends Number, V extends Unit> extends Class<T> {

    ScalarValue<U, V> property();
}
