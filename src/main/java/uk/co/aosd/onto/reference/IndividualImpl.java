package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;

/**
 * An implementation of the Individual interface.
 *
 * @author Tony Walmsley
 */
public record IndividualImpl(String identifier, Event beginning, Event ending) implements Individual {
}