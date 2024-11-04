package uk.co.aosd.onto.foundation;

/**
 * An activity is a partially ordered set of actions.
 *
 * @author Tony Walmsley
 */
public interface Activity extends Individual {

    /**
     * A description of the actions that comprise the activity.
     *
     * @return String This will be modelled better at some point.
     */
    String actionsDescription();
}
