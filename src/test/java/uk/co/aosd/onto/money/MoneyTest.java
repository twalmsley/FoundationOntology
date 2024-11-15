package uk.co.aosd.onto.money;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.decimal4j.immutable.Decimal3f;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.JsonUtils;
import uk.co.aosd.onto.reference.EventServicesImpl;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.EventServices;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.units.Units;

/**
 * Unit tests and examples for how to use Money.
 *
 * @author Tony Walmsley
 */
public class MoneyTest {

    private static final OntologyServices svc = new OntologyServicesImpl();
    private static final EventServices ev = new EventServicesImpl();

    private static final Built FROM = ev.createBuiltEvent("x", Instant.parse("2024-01-01T12:00:00.00Z"), Instant.parse("2024-01-01T12:01:00.00Z"));
    private static final Scrapped TO = ev.createScrappedEvent("y", Instant.parse("2024-01-11T12:00:00.00Z"), Instant.parse("2024-01-11T12:01:00.00Z"));

    @Test
    public void test() {
        final var pounds = svc.createMonetaryValue(Decimal3f.valueOf("231.27"), Units.POUNDS_STERLING);
        final var dollars = svc.createMonetaryValue(Decimal3f.valueOf("27.35"), Units.DOLLARS);
        final var euros = svc.createMonetaryValue(Decimal3f.valueOf("999.99"), Units.EUROS);

        final var widget1 = new Widget<>("widget1", pounds, FROM, TO);
        final var widget2 = new Widget<>("widget2", dollars, FROM, TO);
        final var widget3 = new Widget<>("widget3", euros, FROM, TO);

        final var model = svc.createModel();
        model.add(Units.POUNDS_STERLING);
        model.add(Units.DOLLARS);
        model.add(Units.EUROS);
        model.add(widget1);
        model.add(widget2);
        model.add(widget3);

        assertSame(widget1.value(), pounds);
        assertSame(widget2.value(), dollars);
        assertSame(widget3.value(), euros);

        assertTrue(model.getThing("PoundsSterling").isPresent());
        assertSame(model.getThing("PoundsSterling").get(), Units.POUNDS_STERLING);

        JsonUtils.dumpJson(model);

        // This won't work due to the types being different...
        // MonetaryValue<Dollars> value = euros;
    }
}

record Widget<C extends Currency>(String identifier, MonetaryValue<C> value, Built beginning, Scrapped ending)
    implements ValuedAsset<C>, Individual<Built, Scrapped> {
}
