package uk.co.aosd.onto.foundation;

import java.util.Set;

/**
 * A set of individuals considered to be a part of an actual or possible world.
 * E.g. a plan, a story, current reality.
 *
 * @author Tony Walmsley
 */
public interface PossibleWorld extends Individual {
    Set<Individual> members();
}
