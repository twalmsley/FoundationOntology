package uk.co.aosd.onto.foundation;

/**
 * A thing that has a beginning and ending events, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface EventBounded<B extends Event, E extends Event> {
    B beginning();

    E ending();

}
