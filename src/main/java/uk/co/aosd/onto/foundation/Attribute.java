package uk.co.aosd.onto.foundation;

/**
 * A property value applied to an individual for a period of time.
 *
 * @author Tony Walmsley
 */
public interface Attribute<I extends Individual, P>
    extends TemporallyBounded {

    I individual();

    P property();
}
