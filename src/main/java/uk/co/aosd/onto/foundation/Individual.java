package uk.co.aosd.onto.foundation;

/**
 * An object considered as a single thing, even though it is made up of parts.
 * E.g. a person, a car, etc. bounded by a beginning event B and an ending event
 * E.
 * 
 * <p>The Individual interface represents distinct entities that exist in time.
 * Individuals are fundamental building blocks in the ontology, representing discrete
 * objects with identity and temporal boundaries.</p>
 * 
 * <p>Key characteristics of Individuals:</p>
 * <ul>
 *   <li>They have unique identity (via {@link UniquelyIdentifiable})</li>
 *   <li>They have temporal existence bounded by events</li>
 *   <li>They are considered whole objects (even if composed of parts)</li>
 *   <li>They can be classified into types</li>
 * </ul>
 * 
 * <p>Individuals are bounded by two events:</p>
 * <ul>
 *   <li>A beginning event (B) - marks when the individual comes into existence</li>
 *   <li>An ending event (E) - marks when the individual ceases to exist</li>
 * </ul>
 * 
 * <p>The type parameters B and E specify the types of events that can serve as
 * beginning and ending events. This allows for type-safe modeling of different
 * kinds of individuals with specific lifecycle events.</p>
 * 
 * <p>Examples of individuals include:</p>
 * <ul>
 *   <li>A person (bounded by birth and death events)</li>
 *   <li>A car (bounded by manufacturing and scrapping events)</li>
 *   <li>An organization (bounded by formation and dissolution events)</li>
 * </ul>
 *
 * @param <B> The type of event that marks the beginning of this individual
 * @param <E> The type of event that marks the ending of this individual
 * 
 * @author Tony Walmsley
 * @see Event
 * @see EventBounded
 * @see UniquelyIdentifiable
 */
public interface Individual<B extends Event, E extends Event> extends EventBounded<B, E>, UniquelyIdentifiable {

}
