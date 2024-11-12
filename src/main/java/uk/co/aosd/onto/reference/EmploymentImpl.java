package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.organisation.Employment;
import uk.co.aosd.onto.organisation.Organisation;

/**
 * An implementation of the Employment interface.
 *
 * @author Tony Walmsley
 */
public record EmploymentImpl<C>(String identifier, Organisation employer, Human employee, String actionsDescription,
    C contract, Appointed beginning, Removed ending) implements Employment<C> {

}
