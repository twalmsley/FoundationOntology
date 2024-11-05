package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.signifying.Named;

/**
 * A placeholder to be extended to represent organisations.
 *
 * @author Tony Walmsley
 */
public interface Organisation extends Named, Individual {
    /**
     * Organisations have a common purpose that distinguishes them from other groupings of people.
     *
     * @return String A description of the purpose.
     */
    String purpose();
}
