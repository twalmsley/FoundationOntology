package uk.co.aosd.onto.foundation;

/**
 * A boolean value with a particular Unit U.
 *
 * @author Tony Walmsley
 */
public interface BooleanValue<U extends Unit> {
    Boolean getValue();

    U getUnit();
}
