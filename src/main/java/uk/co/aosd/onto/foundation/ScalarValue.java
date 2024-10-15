package uk.co.aosd.onto.foundation;

public interface ScalarValue<T extends Number, U extends Unit> {
    T value();

    U unit();
}
