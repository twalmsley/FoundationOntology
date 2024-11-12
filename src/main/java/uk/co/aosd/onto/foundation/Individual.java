package uk.co.aosd.onto.foundation;

/**
 * An object considered as a single thing, even though it is made up of parts.
 * E.g. a person, a car, etc. bounded by a beginning event B and an ending event
 * E.
 *
 * @author Tony Walmsley
 */
public interface Individual<B extends Event, E extends Event> extends EventBounded<B, E>, UniquelyIdentifiable {

}
