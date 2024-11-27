package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.money.Currency;

/**
 * A record of a currency.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyImpl implements Currency {
    private String identifier;
    private String abbreviation;
    private String name;
    private char symbol;
}
