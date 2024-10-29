package uk.co.aosd.onto.foundation;

/**
 * An activity is a partially ordered set of actions.
 */
public interface Activity<T extends Number, U extends Unit> extends Individual<T, U> {

    /**
     * A description of the actions that comprise the activity.
     *
     * @return String This will be modelled better at some point.
     */
    String actionsDescription();
}
