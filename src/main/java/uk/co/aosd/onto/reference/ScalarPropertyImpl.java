package uk.co.aosd.onto.reference;

import java.util.Set;

import uk.co.aosd.onto.foundation.ScalarProperty;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the ScalarProperty interface.
 *
 * @author Tony Walmsley
 */
public record ScalarPropertyImpl<T extends UniquelyIdentifiable, U extends Number, V extends Unit>(String identifier,
    ScalarValue<U, V> property, Set<T> members) implements ScalarProperty<T, U, V> {

}
