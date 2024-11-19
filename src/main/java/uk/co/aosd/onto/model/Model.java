package uk.co.aosd.onto.model;

import java.util.Optional;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * A collection of UniquelyIdentifiable things.
 *
 * @author Tony Walmsley
 */
public interface Model extends UniquelyIdentifiable {
    void add(UniquelyIdentifiable thing);

    Optional<UniquelyIdentifiable> getThing(String identifier);
}
