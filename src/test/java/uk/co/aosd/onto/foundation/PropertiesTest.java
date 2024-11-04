package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Test that the representation of properties is usable.
 *
 * @author Tony Walmsley
 */
public class PropertiesTest {
    private static final Optional<Instant> LIFE_START = Optional.of(Instant.parse("2024-01-01T12:00:00.00Z"));
    private static final Optional<Instant> UNKNOWN_END = Optional.empty();

    /**
     * Show how to represent properties using explicit States.
     */
    @Test
    public void testUsingStates() {
        final var car1 = new Car("car1", LIFE_START, UNKNOWN_END);
        final var carState1 = new StateOfCar("carState1", car1, LIFE_START, UNKNOWN_END); // This would have temporal bounds.
        final var redCars = new ColouredCars("redCars", Color.RED, Set.of(carState1));

        assertSame(car1, redCars.members().iterator().next().individual());
    }

    /**
     * Show how to implement properties without using explicit States.
     */
    @Test
    public void testWithoutStates() {
        final var car1 = new Car("car1", LIFE_START, UNKNOWN_END);
        final var car1IsRed = new ColouredCar(car1, Color.RED, LIFE_START, UNKNOWN_END);

        assertSame(car1, car1IsRed.individual());
    }
}

record Car(String identifier, Optional<Instant> beginning, Optional<Instant> ending) implements Individual {
}

record StateOfCar(String identifier, Car individual, Optional<Instant> beginning, Optional<Instant> ending)
    implements State<Car> {

    @Override
    public Duration duration() {
        return null; // For now
    }

}

record ColouredCars(String identifier, Color property, Set<StateOfCar> members) implements Property<StateOfCar, Color> {
}

record ColouredCar(Car individual, Color property, Optional<Instant> beginning, Optional<Instant> ending)
    implements Attribute<Car, Color> {
}