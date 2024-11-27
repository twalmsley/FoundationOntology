package uk.co.aosd.onto.units;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Kilograms implements Unit {
        private String identifier;
        private String name;
        private String abbreviation;
    }

    /**
     * Meters.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Meters implements Unit {
        private String identifier;
        private String name;
        private String abbreviation;
    }

    /**
     * Seconds.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Seconds implements Unit {
        private String identifier;
        private String name;
        private String abbreviation;
    }

    /**
     * Dollars.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Dollars implements Currency {
        private String identifier;
        private String name;
        private String abbreviation;
        private char symbol;
    }

    /**
     * Euros.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Euros implements Currency {
        private String identifier;
        private String name;
        private String abbreviation;
        private char symbol;
    }

    /**
     * Pounds Sterling.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class PoundsSterling implements Currency {
        private String identifier;
        private String name;
        private String abbreviation;
        private char symbol;
    }
}
