package uk.co.aosd.onto.model;

import java.util.Optional;
import java.util.Set;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * A collection of UniquelyIdentifiable things.
 * 
 * <p>The Model interface represents a collection of entities that together form a coherent
 * knowledge representation. It serves as a container for all entities in a knowledge base
 * and provides methods to add and retrieve them.</p>
 * 
 * <p>A Model is itself a {@link UniquelyIdentifiable} entity, meaning it has a unique
 * identifier that distinguishes it from other models.</p>
 * 
 * <p>Models are central to working with the ontology as they:</p>
 * <ul>
 *   <li>Organize entities into coherent sets</li>
 *   <li>Provide lookup mechanisms for entities</li>
 *   <li>Enable serialization and persistence of knowledge</li>
 *   <li>Facilitate querying and reasoning over collections of entities</li>
 * </ul>
 * 
 * <p>Different implementations of this interface can provide various storage mechanisms,
 * such as in-memory storage, database persistence, or graph databases.</p>
 *
 * @author Tony Walmsley
 * @see UniquelyIdentifiable
 */
public interface Model extends UniquelyIdentifiable {
    /**
     * Adds a thing to this model.
     * 
     * <p>This is the primary method for populating a model with entities.
     * Implementations should ensure that the entity is properly indexed
     * by its identifier for efficient retrieval.</p>
     * 
     * @param thing the entity to add to the model
     */
    void add(UniquelyIdentifiable thing);

    /**
     * Retrieves a thing from this model by its identifier.
     * 
     * <p>This method allows for lookups of entities based on their
     * unique identifiers. It returns an Optional that will be empty
     * if no entity with the specified identifier exists in the model.</p>
     * 
     * @param identifier the identifier of the thing to retrieve
     * @return an Optional containing the thing if found, or empty if not found
     */
    Optional<UniquelyIdentifiable> getThing(String identifier);

    /**
     * Gets all things in this model.
     * 
     * <p>This method returns all entities contained in the model as a Set.
     * The Set is a copy of the internal collection to prevent external
     * modification of the model's contents.</p>
     * 
     * @return a Set containing all entities in this model
     */
    Set<UniquelyIdentifiable> getThings();
}
