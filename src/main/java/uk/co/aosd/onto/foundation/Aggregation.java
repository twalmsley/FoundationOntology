package uk.co.aosd.onto.foundation;

import java.util.Set;

public interface Aggregation extends UniquelyIdentifiable {
    Set<Object> members();
}
