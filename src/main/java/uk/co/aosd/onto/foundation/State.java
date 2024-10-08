package uk.co.aosd.onto.foundation;

public interface State<T extends UniquelyIdentifiable> extends TemporallyBounded {
  T individual();
}
