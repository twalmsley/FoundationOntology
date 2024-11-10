package uk.co.aosd.onto.money;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;

import org.decimal4j.immutable.Decimal3f;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.JsonUtils;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.OntologyServices;

/**
 * Unit tests and examples for how to use Money.
 *
 * @author Tony Walmsley
 */
public class MoneyTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

    private static final Event FROM = svc.createEvent("x", Instant.parse("2024-01-01T12:00:00.00Z"), Instant.parse("2024-01-01T12:01:00.00Z"));
    private static final Event TO = svc.createEvent("y", Instant.parse("2024-01-11T12:00:00.00Z"), Instant.parse("2024-01-11T12:01:00.00Z"));

    @Test
    public void test() {
        final var gbp = svc.createCurrency("1", "GBP", "Pounds Sterling", '£');
        final var usd = svc.createCurrency("2", "USD", "US Dollars", '$');
        final var eur = svc.createCurrency("3", "EUR", "Euros", '€');

        final var pounds = svc.createMonetaryValue(Decimal3f.valueOf("231.27"), gbp);
        final var dollars = svc.createMonetaryValue(Decimal3f.valueOf("27.35"), usd);
        final var euros = svc.createMonetaryValue(Decimal3f.valueOf("999.99"), eur);

        final var widget1 = new Widget("widget1", pounds, FROM, TO);
        final var widget2 = new Widget("widget2", dollars, FROM, TO);
        final var widget3 = new Widget("widget3", euros, FROM, TO);

        assertSame(widget1.value(), pounds);
        assertSame(widget2.value(), dollars);
        assertSame(widget3.value(), euros);

        JsonUtils.dumpJson(widget1);
    }
}

record Widget(String identifier, MonetaryValue<Currency> value, Event beginning, Event ending) implements ValuedAsset<Currency>, Individual {
}
