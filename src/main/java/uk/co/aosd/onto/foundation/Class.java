package uk.co.aosd.onto.foundation;

import java.util.Set;

/**
 * An arbitrary set of objects of type T.
 */
public interface Class<T> extends UniquelyIdentifiable {
    Set<T> members();
}
