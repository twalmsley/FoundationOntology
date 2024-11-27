package uk.co.aosd.onto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.aosd.onto.biological.DNA;

/**
 * An implementation of the DNA interface.
 *
 * @author Tony Walmsley
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DNAImpl implements DNA {
    private String identifier;
    private String dna;
}
