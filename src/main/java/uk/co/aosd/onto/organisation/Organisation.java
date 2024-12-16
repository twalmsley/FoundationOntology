package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.signifying.Named;

/**
 * A represention of organisations.
 *
 * @author Tony Walmsley
 */
public interface Organisation<T extends Formed, U extends Dissolved, V extends Resignified> extends Named<V>, Individual<T, U> {
    /**
     * Organisations have a common purpose that distinguishes them from other
     * groupings of people.
     *
     * @return String A description of the purpose.
     */
    String getPurpose();

    /**
     * The members of the organisation.
     *
     * @return Class of Memberships
     */
    Class<? extends Membership<?, ?, ?, ?, ?, ?, ?>> getMembers();

    /**
     * Organisations can have units and sub-units.
     *
     * @return Class of Organisation
     */
    Class<? extends Organisation<T, U, V>> getUnits();
}
