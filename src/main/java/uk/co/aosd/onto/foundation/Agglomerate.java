package uk.co.aosd.onto.foundation;

import java.util.Set;

import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Disaggregated;

/**
 * An agglomerate is an arbitrary set of objects of any type collected together
 * and considered as a single object.
 * E.g. a pile of the disassembled parts of a particular car.
 *
 * @author Tony Walmsley
 */
public interface Agglomerate<T extends Aggregated, U extends Disaggregated> extends Individual<T, U> {
    Set<Individual<? extends Event, ? extends Event>> getParts();
}
