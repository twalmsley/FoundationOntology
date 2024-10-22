package uk.co.aosd.onto.foundation;

public interface ScalarProperty<T extends UniquelyIdentifiable, U extends Number, V extends Unit>
    extends Property<T, ScalarValue<U, V>> {
}
