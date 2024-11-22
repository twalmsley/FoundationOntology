package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.organisation.Organisation;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * An implementation of the Organisation interface.
 *
 * @author Tony Walmsley
 */
public record OrganisationImpl<R extends Role>(String identifier, Class<Membership<R>> members, String purpose, Class<Organisation> units,
    Class<Signifier<String>> names, Formed beginning, Dissolved ending) implements Organisation {
    public OrganisationImpl {
        ensureValid(beginning, ending);
    }
}