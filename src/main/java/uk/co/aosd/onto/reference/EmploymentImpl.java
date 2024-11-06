package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.organisation.Employment;
import uk.co.aosd.onto.organisation.Organisation;

/**
 * An implementation of the Employment interface.
 *
 * @author Tony Walmsley
 */
public record EmploymentImpl<C>(String identifier, Organisation employer, Human employee, String actionsDescription,
    Optional<C> contract, Optional<Instant> beginning, Optional<Instant> ending) implements Employment<C> {

}
