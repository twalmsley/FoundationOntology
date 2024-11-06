package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * An implementation of the Organisation interface.
 *
 * @author Tony Walmsley
 */
public record OrganisationImpl(String identifier, Class<Membership> members, String purpose, Class<Organisation> units,
    Class<Signifier<String>> names, Optional<Instant> beginning, Optional<Instant> ending) implements Organisation {
}