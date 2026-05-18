package uk.co.aosd.onto.biological;

import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Agent;
import uk.co.aosd.onto.language.Language;

/**
 * Distinguishing characteristics of Humans.
 *
 * @author Tony Walmsley
 */
public interface Human<T extends Birth, U extends Death, V extends Resignified, L extends Language> extends GenomicBiologicalEntity<T, U>, Agent<T, U, V, L> {

}
