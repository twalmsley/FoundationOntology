package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Test that the representation of properties is usable.
 *
 * @author Tony Walmsley
 */
public class PropertiesTest {

    /**
     * Show how to represent properties using explicit States.
     */
    @Test
    public void testUsingStates() {
        final var car1 = new Car("car1");
        final var carState1 = new StateOfCar("carState1", car1); // This would have temporal bounds.
        final var redCars = new ColouredCars("redCars", Color.RED, Set.of(carState1));

        assertSame(car1, redCars.members().iterator().next().individual());
    }

    /**
     * Show how to implement properties without using explicit States.
     */
    @Test
    public void testWithoutStates() {
        final var car1 = new Car("car1");
        final var car1IsRed = new ColouredCar(car1, Color.RED);

        assertSame(car1, car1IsRed.individual());
    }
}

record Car(String identifier) implements Individual {

    @Override
    public Optional<Event> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event> ending() {
        return Optional.empty();
    }
}

record StateOfCar(String identifier, Car individual) implements State<Car> {

    @Override
    public Optional<Event> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event> ending() {
        return Optional.empty();
    }

    @Override
    public Duration duration() {
        return null; // for now.
    }

}

record ColouredCars(String identifier, Color property, Set<StateOfCar> members) implements Property<StateOfCar, Color> {
}

record ColouredCar(Car individual, Color property) implements Attribute<Car, Color> {

    @Override
    public Duration duration() {
        return null;
    }

    @Override
    public Optional<Event> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event> ending() {
        return Optional.empty();
    }

}