package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.Sold;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldImpl implements Sold {
    private String identifier;
    private Instant from;
    private Instant to;
}
