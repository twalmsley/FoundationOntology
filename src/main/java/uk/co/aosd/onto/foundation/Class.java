package uk.co.aosd.onto.foundation;

import java.util.Set;

/**
 * An arbitrary set of objects of type T. The objects can be distributed throughout space and time and are not generally considered to be a single object.
 *
 * @author Tony Walmsley
 */
public interface Class<T extends UniquelyIdentifiable> extends UniquelyIdentifiable {
    Set<T> members();
}
