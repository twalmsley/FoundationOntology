package uk.co.aosd.onto.foundation;

public interface Naming<T> extends TemporallyBounded, UniquelyIdentifiable {
  T name();
  UniquelyIdentifiable named();
}
