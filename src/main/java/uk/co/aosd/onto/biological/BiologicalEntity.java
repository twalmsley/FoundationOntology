package uk.co.aosd.onto.biological;

import java.util.Optional;

import uk.co.aosd.onto.foundation.Individual;

public interface BiologicalEntity extends Individual {

    Optional<DNA> dna();
}
