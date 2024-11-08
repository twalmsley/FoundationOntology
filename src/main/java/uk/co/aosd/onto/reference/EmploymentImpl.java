package uk.co.aosd.onto.reference;

import java.util.Optional;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.organisation.Employment;
import uk.co.aosd.onto.organisation.Organisation;

/**
 * An implementation of the Employment interface.
 *
 * @author Tony Walmsley
 */
public record EmploymentImpl<C>(String identifier, Organisation employer, Human employee, String actionsDescription,
    Optional<C> contract, Event beginning, Event ending) implements Employment<C> {

}
