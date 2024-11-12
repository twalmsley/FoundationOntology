package uk.co.aosd.onto.foundation;

import java.util.Set;

import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;

/**
 * A set of individuals considered to be a part of an actual or possible world.
 * E.g. a plan, a story, current reality.
 *
 * @author Tony Walmsley
 */
public interface PossibleWorld extends Individual<Created, Deleted> {
    Set<Individual<? extends Event, ? extends Event>> parts();
}
