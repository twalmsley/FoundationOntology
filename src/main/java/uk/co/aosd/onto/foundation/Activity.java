package uk.co.aosd.onto.foundation;

/**
 * An activity is a partially ordered set of actions.
 *
 * @author Tony Walmsley
 */
public interface Activity<B extends Event, E extends Event> extends Individual<B, E> {

    /**
     * A description of the actions that comprise the activity.
     *
     * @return String This will be modelled better at some point, but could
     *         represent source code or some other procedural language.
     */
    String getActionsDescription();
}
