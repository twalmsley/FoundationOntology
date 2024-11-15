package uk.co.aosd.onto.units;

import uk.co.aosd.onto.foundation.Unit;

/**
 * Defines some common units.
 *
 * @author Tony Walmsley
 */
public interface Units {
    static final Unit KILOGRAMS = new Kilograms("KilogramUnits", "Kilograms", "kg");

    /**
     * Kilograms.
     */
    public static record Kilograms(String identifier, String name, String abbreviation) implements Unit {
    }
}
