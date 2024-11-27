package uk.co.aosd.onto.reference;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * An implementation of Class of T.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassImpl<T extends UniquelyIdentifiable> implements Class<T> {
    private String identifier;
    private Set<T> members;
}
