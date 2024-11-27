package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.State;

/**
 * An implementation of the State interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateImpl<B extends Event, E extends Event, V extends Individual<B, E>> implements State<B, E, V> {
    private String identifier;
    private V individual;
    private B beginning;
    private E ending;
}
