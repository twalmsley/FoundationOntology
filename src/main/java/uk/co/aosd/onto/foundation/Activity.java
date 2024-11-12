package uk.co.aosd.onto.foundation;

import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;

/**
 * An activity is a partially ordered set of actions.
 *
 * @author Tony Walmsley
 */
public interface Activity extends Individual<Started, Stopped> {

    /**
     * A description of the actions that comprise the activity.
     *
     * @return String This will be modelled better at some point, but could
     *         represent source code or some other procedural language.
     */
    String actionsDescription();
}
