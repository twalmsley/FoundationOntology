package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Individual;

/**
 * The activity of owning something.
 */
public interface Owning extends Activity {

    Individual owner();

    Individual owned();
}
