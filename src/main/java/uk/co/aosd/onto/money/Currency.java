package uk.co.aosd.onto.money;

import uk.co.aosd.onto.foundation.Unit;

/**
 * Represents different currencies.
 *
 * @author Tony Walmsley
 */
public interface Currency extends Unit {

    String code();

    String name();

    char symbol();
}