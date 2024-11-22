package uk.co.aosd.onto.foundation;

import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;

/**
 * An Individual used for testing.
 *
 * @author Tony Walmsley
 */
public record Car(String identifier, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
    public Car {
        ensureValid(beginning, ending);
    }
}