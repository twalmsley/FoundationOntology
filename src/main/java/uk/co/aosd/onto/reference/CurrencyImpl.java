package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.money.Currency;

/**
 * A record of a currency.
 *
 * @author Tony Walmsley
 */
public record CurrencyImpl(String identifier, String code, String name, char symbol) implements Currency {

}
