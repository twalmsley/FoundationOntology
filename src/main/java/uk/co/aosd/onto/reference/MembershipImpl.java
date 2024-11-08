package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.organisation.Membership;

/**
 * An implementation of the Membership interface.
 *
 * @author Tony Walmsley
 */
public record MembershipImpl(String identifier, Human member, Role role, Event beginning,
    Event ending) implements Membership {
}