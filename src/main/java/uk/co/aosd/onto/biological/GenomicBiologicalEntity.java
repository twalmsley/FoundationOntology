package uk.co.aosd.onto.biological;

import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.foundation.Individual;

/**
 * A living thing or a dead thing with intact DNA.
 *
 * @author Tony Walmsley
 */
public interface GenomicBiologicalEntity extends Individual<Birth, Death> {

    /**
     * DNA is a distinguishing feature of living things, whether it is known or not.
     *
     * @return Optional DNA.
     */
    DNA dna();
}
