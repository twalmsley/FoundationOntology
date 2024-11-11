package uk.co.aosd.onto.reference;

import uk.co.aosd.onto.foundation.Role;

/**
 * An implementation of the Role interface.
 *
 * @author Tony Walmsley
 */
public record RoleImpl(String identifier, String name) implements Role {

}
