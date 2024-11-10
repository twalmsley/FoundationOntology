package uk.co.aosd.onto.reference;

import org.decimal4j.immutable.Decimal3f;
import uk.co.aosd.onto.money.Currency;
import uk.co.aosd.onto.money.MonetaryValue;

/**
 * An implementation of the MonetaryValue interface.
 *
 * @author Tony Walmsley
 */
public record MonetaryValueImpl<U extends Currency>(Decimal3f value, U unit) implements MonetaryValue<U> {

}
