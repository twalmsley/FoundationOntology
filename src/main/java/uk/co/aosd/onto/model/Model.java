package uk.co.aosd.onto.model;

import java.util.Collection;
import java.util.Optional;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * A collection of UniquelyIdentifiable things.
 *
 * @author Tony Walmsley
 */
public interface Model {
    Collection<UniquelyIdentifiable> getThings();

    void add(UniquelyIdentifiable thing);

    Optional<UniquelyIdentifiable> getThing(String identifier);
}
