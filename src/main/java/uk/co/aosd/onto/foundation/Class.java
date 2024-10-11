package uk.co.aosd.onto.foundation;

import java.util.Set;

public interface Class<T> extends UniquelyIdentifiable {
    Set<T> members();
}
