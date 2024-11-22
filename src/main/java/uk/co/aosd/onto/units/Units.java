package uk.co.aosd.onto.units;

import uk.co.aosd.onto.foundation.Unit;
import uk.co.aosd.onto.money.Currency;

/**
 * Defines some common units.
 *
 * @author Tony Walmsley
 */
public interface Units {
    static final Kilograms KILOGRAMS = new Kilograms("KilogramUnits", "Kilograms", "kg");
    static final Meters METERS = new Meters("MetersUnits", "Meters", "m");
    static final Seconds SECONDS = new Seconds("SecondsTimeUnit", "Seconds", "s");

    static final Dollars DOLLARS = new Dollars("USDollars", "United States Dollar", "USD", '$');
    static final Euros EUROS = new Euros("Euros", "Euros", "EUR", '€');
    static final PoundsSterling POUNDS_STERLING = new PoundsSterling("PoundsSterling", "Pounds Sterling", "GBP", '£');

    /**
     * Kilograms.
     */
    static record Kilograms(String identifier, String name, String abbreviation) implements Unit {
    }

    /**
     * Meters.
     */
    static record Meters(String identifier, String name, String abbreviation) implements Unit {
    }

    /**
     * Seconds.
     */
    static record Seconds(String identifier, String name, String abbreviation) implements Unit {
    }

    /**
     * Dollars.
     */
    static record Dollars(String identifier, String name, String abbreviation, char symbol) implements Currency {
    }

    /**
     * Euros.
     */
    static record Euros(String identifier, String name, String abbreviation, char symbol) implements Currency {
    }

    /**
     * Pounds Sterling.
     */
    static record PoundsSterling(String identifier, String name, String abbreviation, char symbol) implements Currency {
    }
}
