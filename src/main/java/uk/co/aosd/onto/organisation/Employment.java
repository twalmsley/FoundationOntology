package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Activity;

/**
 * A Human employed by an Organisation with a contract of type C.
 *
 * @author Tony Walmsley
 */
public interface Employment<C> extends Activity {
    Human employee();

    Organisation employer();

    C contract();
}
