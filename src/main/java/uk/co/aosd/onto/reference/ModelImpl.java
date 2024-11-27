package uk.co.aosd.onto.reference;

import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.model.Model;

/**
 * An implementation of the Model interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelImpl implements Model {
    private String identifier;
    private Set<UniquelyIdentifiable> things;

    public Optional<UniquelyIdentifiable> getThing(final String identifier) {
        return things.stream().filter(t -> t.getIdentifier().equals(identifier)).findAny();
    }

    public void add(final UniquelyIdentifiable thing) {
        things.add(thing);
    }

}
