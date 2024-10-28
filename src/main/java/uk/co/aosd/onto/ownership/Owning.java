package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.Unit;

/**
 * The activity of owning something.
 */
public interface Owning<T extends Number, U extends Unit> extends Activity<T, U> {

    Individual<T, U> owner();

    Individual<T, U> owned();
}
