package uk.co.aosd.onto.units;

import uk.co.aosd.onto.foundation.Unit;

/**
 * Defines some common units.
 *
 * @author Tony Walmsley
 */
public interface SIUnits {
    static final Unit KILOGRAMS = new Kilograms("KilogramUnits", "Kilograms", "kg");
}

record Kilograms(String identifier, String name, String abbreviation) implements Unit {
}
