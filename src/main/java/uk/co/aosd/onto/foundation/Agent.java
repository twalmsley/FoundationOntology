package uk.co.aosd.onto.foundation;

import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.signifying.Named;

/**
 * A representation of entities that have agency in the world, such as Humans,
 * Organisations, and AI Agents.
 */
public interface Agent<T extends Event, U extends Event, V extends Resignified, L extends Language> extends Named<V>, Individual<T, U> {

    L getNativeLanguage();

    Class<L> getLanguages();
}
