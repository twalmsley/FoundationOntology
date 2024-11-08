package uk.co.aosd.onto.reference;

import java.util.Set;

import uk.co.aosd.onto.foundation.Agglomerate;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Agglomerate interface.
 *
 * @author Tony Walmsley
 */
public record AgglomerateImpl(String identifier, Set<Individual> parts, Event beginning,
    Event ending) implements Agglomerate {

}
