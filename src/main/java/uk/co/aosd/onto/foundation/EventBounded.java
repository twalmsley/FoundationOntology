package uk.co.aosd.onto.foundation;

/**
 * A thing that has a beginning and ending events, possibly unknown.
 *
 * @author Tony Walmsley
 */
public interface EventBounded {
    Event beginning();

    Event ending();

}
