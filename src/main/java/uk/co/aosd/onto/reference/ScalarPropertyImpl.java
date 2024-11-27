package uk.co.aosd.onto.reference;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.foundation.ScalarProperty;
import uk.co.aosd.onto.foundation.ScalarValue;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.foundation.Unit;

/**
 * An implementation of the ScalarProperty interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScalarPropertyImpl<T extends UniquelyIdentifiable, U extends Number, V extends Unit> implements ScalarProperty<T, U, V> {
    private String identifier;
    private ScalarValue<U, V> property;
    private Set<T> members;
}
