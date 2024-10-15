package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Individual;

public interface Owning extends Individual {

    Individual owner();

    Individual owned();
}
