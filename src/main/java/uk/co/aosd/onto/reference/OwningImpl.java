package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.ownership.Owning;

/**
 * An implementation of the Owning interface.
 *
 * @author Tony Walmsley
 */
public record OwningImpl(String identifier, String actionsDescription, Individual owner, Individual owned,
    Event beginning, Event ending) implements Owning {

}
