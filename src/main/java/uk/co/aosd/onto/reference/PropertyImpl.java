package uk.co.aosd.onto.reference;

import java.util.Set;

import uk.co.aosd.onto.foundation.Property;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * An implementation of the Property interface.
 *
 * @author Tony Walmsley
 */
public record PropertyImpl<T extends UniquelyIdentifiable, U>(String identifier, Set<T> members, U property)
    implements Property<T, U> {

}
