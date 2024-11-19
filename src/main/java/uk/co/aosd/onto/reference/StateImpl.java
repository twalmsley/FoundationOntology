package uk.co.aosd.onto.reference;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.State;

/**
 * An implementation of the State interface.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.state")
public record StateImpl<B extends Event, E extends Event, V extends Individual<B, E>>(String identifier, V individual, B beginning,
    E ending) implements State<B, E, V> {

}
