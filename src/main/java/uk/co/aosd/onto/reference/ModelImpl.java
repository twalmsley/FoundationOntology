package uk.co.aosd.onto.reference;

import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.model.Model;

/**
 * An implementation of the Model interface.
 *
 * @author Tony Walmsley
 */
public record ModelImpl(String identifier, Set<UniquelyIdentifiable> things) implements Model {

    public String identifier() {
        return identifier;
    }

    public Optional<UniquelyIdentifiable> getThing(final String identifier) {
        return things.stream().filter(t -> t.identifier().equals(identifier)).findAny();
    }

    public void add(final UniquelyIdentifiable thing) {
        things.add(thing);
    }
}
