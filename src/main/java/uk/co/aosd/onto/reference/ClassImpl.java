package uk.co.aosd.onto.reference;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * An implementation of Class of T.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.class")
public record ClassImpl<T extends UniquelyIdentifiable>(String identifier, Set<T> members) implements Class<T> {

}
