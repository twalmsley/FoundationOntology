package uk.co.aosd.onto.reference.events;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.events.Installed;

/**
 * An implementation of the Installed interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstalledImpl implements Installed {
    private String identifier;
    private Instant from;
    private Instant to;
}
