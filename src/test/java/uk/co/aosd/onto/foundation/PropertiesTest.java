package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.reference.OntologyServicesImpl;
import uk.co.aosd.onto.reference.events.BuiltImpl;
import uk.co.aosd.onto.reference.events.ScrappedImpl;
import uk.co.aosd.onto.services.OntologyServices;
import uk.co.aosd.onto.units.Units;
import uk.co.aosd.onto.units.Units.Kilograms;
import uk.co.aosd.onto.units.Units.Meters;

/**
 * Test that the representation of properties is usable.
 *
 * @author Tony Walmsley
 */
public class PropertiesTest {

    private static final OntologyServices svc = new OntologyServicesImpl();

    private static final Instant LIFE_START_TIME = Instant.parse("2024-01-01T12:00:00.00Z");
    private static final Instant LIFE_END_TIME = Instant.parse("2025-01-01T12:00:00.00Z");
    private static final Built LIFE_START = new BuiltImpl(randString(), LIFE_START_TIME, LIFE_END_TIME);
    private static final Scrapped UNKNOWN_END = new ScrappedImpl(randString(), LIFE_END_TIME, LIFE_END_TIME);

    /**
     * Show how to represent properties using explicit States.
     */
    @Test
    public void testUsingStates() {
        final var car1 = new Car(randString(), LIFE_START, UNKNOWN_END);
        final var carState1 = svc.createState(randString(), car1, LIFE_START, UNKNOWN_END);
        final var redCars = new ColouredCars(randString(), Color.RED, Set.of(carState1));

        assertSame(car1, redCars.getMembers().iterator().next().getIndividual());
    }

    /**
     * Show how to implement properties without using explicit States.
     */
    @Test
    public void testWithoutStates() {
        final var car1 = new Car(randString(), LIFE_START, UNKNOWN_END);
        final var car1IsRed = new CarColour(randString(), car1, Color.RED, LIFE_START_TIME, LIFE_END_TIME);

        assertSame(car1, car1IsRed.getIndividual());
    }

    /**
     * Demonstrate the Property/Attribute isomorphism.
     */
    @Test
    public void isomorphism() {
        final var car1 = new Car(randString(), LIFE_START, UNKNOWN_END);
        final var carState1 = svc.createState(randString(), car1, LIFE_START, UNKNOWN_END);
        final var redCars = new ColouredCars(randString(), Color.RED, Set.of(carState1));

        // Convert the ColouredCars property into a list of CarColour Attributes
        final var attributes = redCars
            .getMembers()
            .stream()
            .map(state -> {
                return new CarColour(randString(), state.getIndividual(), redCars.getProperty(), state.getBeginning().getFrom(), state.getEnding().getFrom());
            }).toList();

        // Convert the list of CarColour Attributes into a ColouredCars Property
        final var redCars2 = new ColouredCars(
            randString(),
            Color.RED,
            attributes
                .stream()
                .map(attr -> {
                    return svc.createState(randString(), attr.getIndividual(), new BuiltImpl(randString(), attr.getFrom(), LIFE_END_TIME),
                        new ScrappedImpl(randString(), attr.getTo(), LIFE_END_TIME));
                }).collect(Collectors.toSet()));

        // Apart from the IDs, redCars and redCars2 will be identical (checked manually
        // using https://jsondiff.com/)
        assertEquals(redCars.getMembers().size(), redCars2.getMembers().size());

        // JsonUtils.dumpJson(redCars);
        // JsonUtils.dumpJson(redCars2);
    }

    /**
     * Test the ScalarAttributes can be created.
     */
    @Test
    public void testScalarAttributes() {
        final var car = new Car(randString(), LIFE_START, UNKNOWN_END);

        final var kg1000 = svc.createScalarValue(1000.0D, Units.KILOGRAMS);
        final var kg1100 = svc.createScalarValue(1100.0D, Units.KILOGRAMS);
        final var kg1200 = svc.createScalarValue(1200.0D, Units.KILOGRAMS);

        final var from1 = Instant.ofEpochSecond(0);
        final var from2 = Instant.ofEpochSecond(1000);
        final var from3 = Instant.ofEpochSecond(2000);
        final var to1 = Instant.ofEpochSecond(1000);
        final var to2 = Instant.ofEpochSecond(2000);
        final var to3 = Instant.ofEpochSecond(3000);

        final var weight1 = new CarWeight(randString(), car, kg1000, from1, to1);
        final var weight2 = new CarWeight(randString(), car, kg1100, from2, to2);
        final var weight3 = new CarWeight(randString(), car, kg1200, from3, to3);

        final var m3 = svc.createScalarValue(3.0D, Units.METERS);

        // Same at run-time, but not at compile time due to type erasure.
        assertEquals(m3.getClass(), kg1000.getClass());

        assertNotEquals(m3.getUnit().getClass(), kg1000.getUnit().getClass());

        assertEquals(1000.0D, weight1.getProperty().getValue());
        assertEquals(1100.0D, weight2.getProperty().getValue());
        assertEquals(1200.0D, weight3.getProperty().getValue());
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}

enum Color {
    RED, BLUE, GREEN
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ColouredCars implements Property<State<Built, Scrapped, Car>, Color> {
    private String identifier;
    private Color property;
    private Set<State<Built, Scrapped, Car>> members;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CarColour implements Attribute<Car, Color> {
    private String identifier;
    private Car individual;
    private Color property;
    private Instant from;
    private Instant to;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CarWeight implements ScalarAttribute<Car, Double, Kilograms> {
    private String identifier;
    private Car individual;
    private ScalarValue<Double, Kilograms> property;
    private Instant from;
    private Instant to;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CarLength implements ScalarAttribute<Car, Double, Meters> {
    private String identifier;
    private Car individual;
    private ScalarValue<Double, Meters> property;
    private Instant from;
    private Instant to;
}