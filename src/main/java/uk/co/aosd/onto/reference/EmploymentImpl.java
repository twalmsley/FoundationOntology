package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.organisation.Employment;
import uk.co.aosd.onto.organisation.Organisation;

/**
 * An implementation of the Employment interface.
 *
 * @author Tony Walmsley
 */
public record EmploymentImpl<C>(String identifier, Organisation employer, Human employee, String actionsDescription,
    C contract, Started beginning, Stopped ending) implements Employment<C> {

}
