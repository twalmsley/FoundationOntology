package uk.co.aosd.onto.reference;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.organisation.Membership;

/**
 * An implementation of the Membership interface.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.membership")
public record MembershipImpl<R extends Role>(String identifier, Human member, R role, Appointed beginning,
    Removed ending) implements Membership<R> {
}