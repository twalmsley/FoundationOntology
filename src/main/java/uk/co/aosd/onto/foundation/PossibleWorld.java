package uk.co.aosd.onto.foundation;

import java.util.Set;

import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;

/**
 * A set of individuals considered to be a part of an actual or possible world.
 * 
 * <p>This interface represents the concept of a possible world, which is a complete
 * way the world is or could have been. It can represent:</p>
 * 
 * <ul>
 *   <li>The actual world (current reality)</li>
 *   <li>A hypothetical scenario or plan</li>
 *   <li>A fictional world (e.g., from a story)</li>
 *   <li>A simulation or model of reality</li>
 * </ul>
 * 
 * <p>A PossibleWorld contains a set of Individuals that exist within that world.
 * It is itself an Individual bounded by Creation and Deletion events, representing 
 * when this particular world concept was created and when it was deleted/abandoned.</p>
 * 
 * <p>Example use cases:</p>
 * <ul>
 *   <li>Representing different project plans for comparison</li>
 *   <li>Modeling fictional scenarios for testing or storytelling</li>
 *   <li>Tracking alternate versions of reality for decision-making</li>
 * </ul>
 *
 * @author Tony Walmsley
 */
public interface PossibleWorld<T extends Created, U extends Deleted> extends Individual<T, U> {
    /**
     * Returns the set of individuals that exist within this possible world.
     * 
     * @return a set of individuals that are part of this possible world
     */
    Set<Individual<? extends Event, ? extends Event>> getParts();
}
