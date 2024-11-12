package uk.co.aosd.onto.ownership;

import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

/**
 * The activity of owning something.
 *
 * @author Tony Walmsley
 */
public interface Owning<A extends Event, B extends Event, C extends Event, D extends Event> extends Activity {

    Individual<A, B> owner();

    Individual<C, D> owned();
}
