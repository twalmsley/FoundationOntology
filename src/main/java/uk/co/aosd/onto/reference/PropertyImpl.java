package uk.co.aosd.onto.reference;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.foundation.Property;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * An implementation of the Property interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyImpl<T extends UniquelyIdentifiable, U> implements Property<T, U> {
    private String identifier;
    private Set<T> members;
    private U property;
}
