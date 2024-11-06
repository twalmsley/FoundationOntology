package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Color;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.reference.IndividualImpl;
import uk.co.aosd.onto.reference.StateImpl;

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
        final var car1 = new IndividualImpl(randString(), LIFE_START, UNKNOWN_END);
        final var carState1 = new StateImpl<Individual>(randString(), car1, LIFE_START, UNKNOWN_END);
        final var redCars = new ColouredCars(randString(), Color.RED, Set.of(carState1));

        assertSame(car1, redCars.members().iterator().next().individual());
    }

    /**
     * Show how to implement properties without using explicit States.
     */
    @Test
    public void testWithoutStates() {
        final var car1 = new IndividualImpl(randString(), LIFE_START, UNKNOWN_END);
        final var car1IsRed = new ColouredCar(car1, Color.RED, LIFE_START, UNKNOWN_END);

        assertSame(car1, car1IsRed.individual());
    }

    /**
     * Demonstrate the Property/Attribute isomorphism.
     */
    @Test
    public void isomorphism() {
        final Individual car1 = new IndividualImpl(randString(), LIFE_START, UNKNOWN_END);
        final State<Individual> carState1 = new StateImpl<>(randString(), car1, LIFE_START, UNKNOWN_END);
        final Property<State<Individual>, Color> redCars = new ColouredCars(randString(), Color.RED, Set.of(carState1));

        // Convert the ColouredCars property into a list of ColouredCar Attributes
        final List<ColouredCar> attributes = redCars
            .members()
            .stream()
            .map(state -> {
                return new ColouredCar(state.individual(), redCars.property(), state.beginning(), state.ending());
            }).toList();

        // Convert the list of ColouredCar Attributes into a ColouredCars Property
        final ColouredCars redCars2 = new ColouredCars(
            randString(),
            Color.RED,
            attributes
                .stream()
                .map(attr -> {
                    return new StateImpl<Individual>(randString(), attr.individual(), attr.beginning(),
                        attr.ending());
                }).collect(Collectors.toSet()));

        // Apart from the IDs, redCars and redCars2 will be identical
        assertEquals(redCars.members().size(), redCars2.members().size());
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}

record ColouredCars(String identifier, Color property, Set<State<Individual>> members)
    implements Property<State<Individual>, Color> {
}

record ColouredCar(Individual individual, Color property, Optional<Instant> beginning, Optional<Instant> ending)
    implements Attribute<Individual, Color> {
}