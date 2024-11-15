package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.signifying.Named;

/**
 * A represention of organisations.
 *
 * @author Tony Walmsley
 */
public interface Organisation extends Named, Individual<Formed, Dissolved> {
    /**
     * Organisations have a common purpose that distinguishes them from other
     * groupings of people.
     *
     * @return String A description of the purpose.
     */
    String purpose();

    /**
     * The members of the organisation.
     *
     * @return Class of Memberships
     */
    Class<? extends Membership<?>> members();

    /**
     * Organisations can have units and sub-units.
     *
     * @return Class of Organisation
     */
    Class<? extends Organisation> units();
}
