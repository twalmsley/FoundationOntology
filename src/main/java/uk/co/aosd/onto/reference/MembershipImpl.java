package uk.co.aosd.onto.reference;

import java.time.Instant;
import java.util.Optional;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.organisation.Membership;

/**
 * An implementation of the Membership interface.
 *
 * @author Tony Walmsley
 */
public record MembershipImpl(String identifier, Human member, Optional<Instant> beginning,
    Optional<Instant> ending) implements Membership {
}