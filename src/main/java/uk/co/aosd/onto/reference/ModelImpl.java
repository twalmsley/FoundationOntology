package uk.co.aosd.onto.reference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.model.Model;

/**
 * An implementation of the Model interface.
 *
 * @author Tony Walmsley
 */
public class ModelImpl implements Model {

    private final Map<String, UniquelyIdentifiable> things;

    public ModelImpl() {
        things = new HashMap<>();
    }

    public Optional<UniquelyIdentifiable> getThing(final String identifier) {
        return Optional.ofNullable(things.get(identifier));
    }

    public Collection<UniquelyIdentifiable> getThings() {
        return things.values();
    }

    public void add(final UniquelyIdentifiable thing) {
        things.put(thing.identifier(), thing);
    }
}
