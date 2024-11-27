package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.TransferredTo;

/**
 * An implementation of an extension to the Event interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferredToImpl implements TransferredTo {
    private String identifier;
    private Instant from;
    private Instant to;
}
