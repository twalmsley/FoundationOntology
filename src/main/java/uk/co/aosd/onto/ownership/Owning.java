package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Individual;

public interface Owning extends Activity {

    Individual owner();

    Individual owned();
}
