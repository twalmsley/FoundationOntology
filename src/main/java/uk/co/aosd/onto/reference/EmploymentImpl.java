package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.organisation.Employment;
import uk.co.aosd.onto.organisation.Organisation;

/**
 * An implementation of the Employment interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentImpl<C> implements Employment<C> {
    private String identifier;
    private Organisation employer;
    private Human employee;
    private String actionsDescription;
    private C contract;
    private Appointed beginning;
    private Removed ending;
}
