package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationImpl<R extends Role> implements Organisation {
    private String identifier;
    private Class<Membership<R>> members;
    private String purpose;
    private Class<Organisation> units;
    private Class<Signifier<String>> names;
    private Formed beginning;
    private Dissolved ending;
}