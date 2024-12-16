package uk.co.aosd.onto.organisation;

import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.language.Language;

/**
 * Records membership of an organisation.
 *
 * @author Tony Walmsley
 */
public interface Membership<R extends Role, T extends Appointed, U extends Removed, V extends Birth, W extends Death, X extends Resignified, L extends Language>
    extends Individual<T, U> {

    Human<V, W, X, L> getMember();

    R getRole();
}
