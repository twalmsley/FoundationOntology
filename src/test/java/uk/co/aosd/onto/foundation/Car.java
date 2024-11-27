package uk.co.aosd.onto.foundation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;

/**
 * An Individual used for testing.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Individual<Built, Scrapped> {
    private String identifier;
    private Built beginning;
    private Scrapped ending;
}