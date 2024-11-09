package uk.co.aosd.onto.biological;

import uk.co.aosd.onto.foundation.UniquelyIdentifiable;

/**
 * DeoxyRibonucleic Acid sequence for an organism.
 *
 * @author Tony Walmsley
 */
public interface DNA extends UniquelyIdentifiable {
    /**
     * The sequence of ACGT letters encoding the DNA for this organism.
     *
     * @return String
     */
    String dna();
}
