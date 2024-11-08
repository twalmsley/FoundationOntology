package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.State;

/**
 * An implementation of the State interface.
 *
 * @author Tony Walmsley
 */
public record StateImpl<V extends Individual>(String identifier, V individual, Event beginning,
    Event ending) implements State<V> {

}
