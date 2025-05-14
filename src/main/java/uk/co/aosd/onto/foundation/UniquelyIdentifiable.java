package uk.co.aosd.onto.foundation;

/**
 * A thing with a unique identity that can be represented as a String.
 * 
 * <p>This is the base interface for all entities in the ontology. Every entity
 * must have a unique identifier that distinguishes it from all other entities.
 * The identifier should be persistent and immutable.</p>
 * 
 * <p>Implementations should ensure that:</p>
 * <ul>
 *   <li>Identifiers are globally unique</li>
 *   <li>Identifiers persist across serialization/deserialization</li>
 *   <li>Identifiers can be used as references in knowledge graphs</li>
 * </ul>
 * 
 * <p>Common implementation patterns include:</p>
 * <ul>
 *   <li>UUIDs (e.g., "550e8400-e29b-41d4-a716-446655440000")</li>
 *   <li>URIs (e.g., "http://example.org/entities/person1")</li>
 *   <li>Domain-specific identifiers (e.g., "employee-123456")</li>
 * </ul>
 *
 * @author Tony Walmsley
 */
public interface UniquelyIdentifiable {
    /**
     * Gets the unique identifier for this entity.
     * 
     * @return a String representing the unique identifier
     */
    String getIdentifier();
}
