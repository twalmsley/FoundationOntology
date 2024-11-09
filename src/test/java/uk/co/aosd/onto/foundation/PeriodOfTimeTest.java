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
        final var oneDayStartingNow = new OneDay(from);

        assertNotNull(oneDayStartingNow.from());
        assertNotNull(oneDayStartingNow.from());

        assertNotNull(oneDayStartingNow.to());
        assertNotNull(oneDayStartingNow.to());

        final Optional<Duration> duration = oneDayStartingNow.duration();
        assertTrue(duration.isPresent());
        assertEquals(duration.get(), Duration.ofSeconds(Long.valueOf(3600 * 24)));
    }
}

/**
 * A particular 24-hour period beginning some time during a given second and
 * ending 24 hours worth of seconds later.
 */
record OneDay(Instant from) implements TimePeriod {

    public static Duration oneDay = Duration.ofSeconds(3600 * 24);

    @Override
    public Instant to() {
        return from.plus(oneDay);
    }

}
