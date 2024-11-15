package uk.co.aosd.onto.units;

import uk.co.aosd.onto.foundation.Unit;

/**
 * Defines some common units.
 *
 * @author Tony Walmsley
 */
public interface Units {
    static final Kilograms KILOGRAMS = new Kilograms("KilogramUnits", "Kilograms", "kg");
    static final Meters METERS = new Meters("MetersUnits", "Meters", "m");

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
}
