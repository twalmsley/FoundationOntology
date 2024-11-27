package uk.co.aosd.onto.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.decimal4j.immutable.Decimal3f;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.JsonUtils;
import uk.co.aosd.onto.reference.ModelImpl;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.reference.events.BuiltImpl;
import uk.co.aosd.onto.reference.events.ScrappedImpl;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.units.Units;

/**
 * Unit tests and examples for how to use Money.
 *
 * @author Tony Walmsley
 */
public class MoneyTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

    private static final Built FROM = new BuiltImpl("x", Instant.parse("2024-01-01T12:00:00.00Z"), Instant.parse("2024-01-01T12:01:00.00Z"));
    private static final Scrapped TO = new ScrappedImpl("y", Instant.parse("2024-01-11T12:00:00.00Z"), Instant.parse("2024-01-11T12:01:00.00Z"));

    @Test
    public void test() throws JsonProcessingException {
        final var pounds = svc.createMonetaryValue(Decimal3f.valueOf("231.27"), Units.POUNDS_STERLING);
        final var dollars = svc.createMonetaryValue(Decimal3f.valueOf("27.35"), Units.DOLLARS);
        final var euros = svc.createMonetaryValue(Decimal3f.valueOf("999.99"), Units.EUROS);

        final var widget1 = new Widget<>("widget1", pounds, FROM, TO);
        final var widget2 = new Widget<>("widget2", dollars, FROM, TO);
        final var widget3 = new Widget<>("widget3", euros, FROM, TO);

        final var model = svc.createModel("model1");
        model.add(Units.POUNDS_STERLING);
        model.add(Units.DOLLARS);
        model.add(Units.EUROS);
        model.add(widget1);
        model.add(widget2);
        model.add(widget3);

        assertSame(widget1.getValue(), pounds);
        assertSame(widget2.getValue(), dollars);
        assertSame(widget3.getValue(), euros);

        assertTrue(model.getThing("PoundsSterling").isPresent());
        assertSame(model.getThing("PoundsSterling").get(), Units.POUNDS_STERLING);

        final var json = JsonUtils.writeJsonString(model);
        final var model2 = JsonUtils.readJsonString(json, ModelImpl.class);

        assertEquals(model.getIdentifier(), model2.getIdentifier());
        model.getThings().forEach(thing -> {
            assertTrue(model2.getThing(thing.getIdentifier()).isPresent());
        });
        model2.getThings().forEach(thing -> {
            assertTrue(model.getThing(thing.getIdentifier()).isPresent());
        });

        // This won't work due to the types being different...
        // MonetaryValue<Dollars> value = euros;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Widget<C extends Currency> implements ValuedAsset<C>, Individual<Built, Scrapped> {
    private String identifier;
    private MonetaryValue<C> value;
    private Built beginning;
    private Scrapped ending;
}
