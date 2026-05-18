package uk.co.aosd.onto.foundation;

/**
 * A numeric attribute of type N, a Number, which must have a particular unit U,
 * that applies to an Individual.
 *
 * @author Tony Walmsley
 */
public interface ScalarAttribute<I extends Individual<? extends Event, ? extends Event>, N extends Number, U extends Unit>
    extends Attribute<I, ScalarValue<N, U>> {
}
