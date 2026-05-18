package uk.co.aosd.onto.foundation;

/**
 * A boolean which must have a particular unit U,
 * that applies to an Individual.
 *
 * @author Tony Walmsley
 */
public interface BooleanAttribute<I extends Individual<? extends Event, ? extends Event>, U extends Unit>
    extends Attribute<I, BooleanValue<U>> {
}
