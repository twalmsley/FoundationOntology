package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.reference.events.AggregatedImpl;
import uk.co.aosd.onto.reference.events.DisaggregatedImpl;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.units.Units;

/**
 * Test that aggregation works as expected.
 *
 * @author Tony Walmsley
 */
public class AggregationTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

    @Test
    public void test() {
        final var aggregatedFrom = new AggregatedImpl(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(1));
        final var aggregatedTo = new DisaggregatedImpl(randString(), Instant.ofEpochSecond(1000), Instant.ofEpochSecond(1001));
        final var quantity = svc.createScalarValue(1000.0, Units.KILOGRAMS);
        final var someSand = svc.createAggregate(randString(), Sand.class, quantity, aggregatedFrom, aggregatedTo);
        final var someWater = svc.createAggregate(randString(), Water.class, quantity, aggregatedFrom, aggregatedTo);

        final var agg = someSand;
        // The following line is not allowed because agg is an aggregate of Sand while
        // someWater is an aggregate of water.
        //
        // agg = someWater; // ERROR - incompatible types.

        assertNotNull(someSand);
        assertNotNull(someWater);
        assertNotNull(agg);
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}

class Sand {
}

class Water {
}