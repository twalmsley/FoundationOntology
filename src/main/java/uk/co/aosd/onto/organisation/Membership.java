package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.foundation.Individual;

/**
 * Records membership of an organisation.
 *
 * @author Tony Walmsley
 */
public interface Membership extends Individual {

    Human member();

}
