package uk.co.aosd.onto.reference;

import com.fasterxml.jackson.annotation.JsonTypeName;
import uk.co.aosd.onto.biological.DNA;

/**
 * An implementation of the DNA interface.
 *
 * @author Tony Walmsley
 */
@JsonTypeName("uk.co.aosd.onto.reference.dna")
public record DNAImpl(String identifier, String dna) implements DNA {

}
