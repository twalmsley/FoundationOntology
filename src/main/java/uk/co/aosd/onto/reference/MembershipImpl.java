package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipImpl<R extends Role> implements Membership<R> {
    private String identifier;
    private Human member;
    private R role;
    private Appointed beginning;
    private Removed ending;
}