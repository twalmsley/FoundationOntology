package uk.co.aosd.onto.money;

import org.decimal4j.immutable.Decimal3f;
import uk.co.aosd.onto.foundation.ScalarValue;

/**
 * An amount of money of a specific currency.
 *
 * @author Tony Walmsley
 */
public interface MonetaryValue<U extends Currency>  extends ScalarValue<Decimal3f, U> {

}
