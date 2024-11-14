package uk.co.aosd.onto.foundation;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.reference.EventServicesImpl;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.services.EventServices;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.units.SIUnits;

/**
 * Test that aggregation works as expected.
 *
 * @author Tony Walmsley
 */
public class AggregationTest {

    private static final EventServices ev = new EventServicesImpl();
    private static final OntologyServices svc = new OntologyServicesImpl();

    @Test
    public void test() {
        final var aggregatedFrom = ev.createAggregated(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(1));
        final var aggregatedTo = ev.createDisaggregated(randString(), Instant.ofEpochSecond(1000), Instant.ofEpochSecond(1001));
        final var quantity = svc.createScalarValue(1000.0, SIUnits.KILOGRAMS);
        final var agg1 = svc.createAggregate(randString(), Sand.class, quantity, aggregatedFrom, aggregatedTo);

        JsonUtils.dumpJson(agg1);
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}

class Sand {
}
