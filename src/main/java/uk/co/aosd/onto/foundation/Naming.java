package uk.co.aosd.onto.foundation;

public interface Naming<T> extends TemporallyBounded {
  T name();
  UniquelyIdentifiable named();
}
