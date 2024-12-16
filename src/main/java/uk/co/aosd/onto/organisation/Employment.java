package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.language.Language;

/**
 * A Human employed by an Organisation with a contract of type C.
 *
 * @author Tony Walmsley
 */
public interface Employment<C, T extends Appointed, U extends Removed, V extends Formed, W extends Dissolved, X extends Resignified, Y extends Birth, Z extends Death, L extends Language>
    extends Activity<T, U> {

    Human<Y, Z, X, L> getEmployee();

    Organisation<V, W, X> getEmployer();

    C getContract();
}
