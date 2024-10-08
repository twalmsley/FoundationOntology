package uk.co.aosd.onto.foundation;

public interface Scalar<T extends Number, U extends Unit> {
  T value();
  U unit();
}
