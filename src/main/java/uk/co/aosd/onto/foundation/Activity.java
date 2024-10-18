package uk.co.aosd.onto.foundation;

/**
 * An activity is a partially ordered set of actions.
 */
public interface Activity extends Individual {

    /**
     * A description of the actions that comprise activity.
     *
     * @return String Could be modelled better at some point.
     */
    String actionsDescription();
}
