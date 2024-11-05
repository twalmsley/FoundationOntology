package uk.co.aosd.onto.reference;

import java.util.Set;

import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * An implementation of Class of T.
 *
 * @author Tony Walmsley
 */
public record ClassImpl<T extends UniquelyIdentifiable>(String identifier, Set<T> members) implements Class<T> {

}
