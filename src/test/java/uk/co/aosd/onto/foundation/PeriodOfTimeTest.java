package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * Tests for the PeriodOfTime class.
 * 
 * <p>
 * Create a PeriodOfTime of OneDayInSeconds, bounded by two ParticularSeconds.
 * </p>
 *
 * @author Tony Walmsley
 */
public class PeriodOfTimeTest {

    @Test
    public void test() {
        final var from = Instant.now();
        final var oneDayStartingNow = new OneDay(Optional.of(from));

        assertNotNull(oneDayStartingNow.beginning());
        assertTrue(oneDayStartingNow.beginning().isPresent());

        assertNotNull(oneDayStartingNow.ending());
        assertTrue(oneDayStartingNow.ending().isPresent());

        assertEquals(oneDayStartingNow.duration(), Duration.ofSeconds(Long.valueOf(3600 * 24)));
    }
}

/**
 * A particular 24-hour period beginning some time during a given second and
 * ending 24 hours worth of seconds later.
 */
record OneDay(Optional<Instant> beginning) implements TemporallyBounded {

    public static Duration oneDay = Duration.ofSeconds(3600 * 24);

    @Override
    public Optional<Instant> ending() {
        return beginning.map(from -> from.plus(oneDay));
    }

}
