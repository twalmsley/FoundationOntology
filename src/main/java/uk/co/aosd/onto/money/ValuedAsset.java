package uk.co.aosd.onto.money;

/**
 * A thing that has a MonetaryValue, such as a coin, note, voucher, car,
 * precious metal, etc.
 *
 * @author Tony Walmsley
 */
public interface ValuedAsset<U extends Currency> {

    MonetaryValue<U> getValue();
}
