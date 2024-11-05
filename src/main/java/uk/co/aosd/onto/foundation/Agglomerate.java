package uk.co.aosd.onto.foundation;

import java.util.Set;

/**
 * An agglomerate is an arbitrary set of objects of any type collected together and considered as a single object. 
 * E.g. a pile of the disassembled parts of a particular car.
 *
 * @author Tony Walmsley
 */
public interface Agglomerate extends Individual {
    Set<Individual> members();
}