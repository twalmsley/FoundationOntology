package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.Unit;
import uk.co.aosd.onto.signifying.Named;

/**
 * A placeholder to be extended to represent organisations.
 */
public interface Organisation<T extends Number, U extends Unit> extends Named<T, U>, Individual<T, U> {

}
