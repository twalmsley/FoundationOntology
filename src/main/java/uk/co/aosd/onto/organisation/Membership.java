package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.Role;

/**
 * Records membership of an organisation.
 *
 * @author Tony Walmsley
 */
public interface Membership extends Individual<Appointed, Removed> {

    Human member();

    Role role();
}
