package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;

/**
 * Events have non-zero duration which can mean that some events happen 'at the
 * same time', when they might not actually overlap because they just happen to
 * occur within the same short time period. One example is the change of the
 * President of the USA which, according to Wikipedia, happens on the 20th of
 * January. So at some point during that day the office changes hands, and if
 * events with a granularity of 1-day are used then there are two presidents on
 * that day. Officially the changeover is at exactly 12 noon so there is no
 * overlap or gap in the presidency. The problem still exists at a finer level
 * of granularity because this ontology doesn't allow zero duration events, but
 * I believe that in most cases it doesn't matter very much. In those cases
 * where it does matter then users can make a pragmatic choice about whether any
 * overlap or gap actually occurs.
 *
 * @author Tony Walmsley
 */
public class EventsTest {

    private static final Instant T_1200 = Instant.parse("2024-01-01T12:00:00.00Z");
    private static final Instant T_1300 = Instant.parse("2024-01-01T13:00:00.00Z");
    private static final Instant T_1305 = Instant.parse("2024-01-01T13:05:00.00Z");
    private static final Instant T_1400 = Instant.parse("2024-01-01T14:00:00.00Z");

    @Test
    public void test() {
        // The Started event occurred between 12:00 and 13:00
        final var started = new Started(randString(), T_1200, T_1300);
        // The Stopped event occurred between 13:00 and 14:00
        final var stopped = new Stopped(randString(), T_1305, T_1400);

        // Lunch could be up to 2 hours long, but could also be less than 5 minutes;
        // in this case we don't know the information accurately.
        final var lunch = new Lunch(randString(), "Eating lunch", started, stopped);

        final var range = lunch.range().orElseThrow();
        assertEquals(Duration.ofMinutes(5L), range.min());
        assertEquals(Duration.ofHours(2L), range.max());
    }

    /**
     * Test with the beginning and endings the wrong way round, so that the
     * durations are negative.
     */
    @Test
    public void testBadEvents() {
        // The Started event occurred between 12:00 and 13:00
        final var started = new Started(randString(), T_1300, T_1200);
        // The Stopped event occurred between 13:00 and 14:00
        final var stopped = new Stopped(randString(), T_1400, T_1200);

        // Lunch could be up to 2 hours long, but could also be less than 5 minutes;
        // in this case we don't know the information accurately.
        final var lunch = new Lunch(randString(), "Eating lunch", started, stopped);

        final var range = lunch.range().orElseThrow();
        assertEquals(Duration.ofHours(2L), range.min());
        assertEquals(Duration.ofHours(-1L), range.max());

    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }

    private static record Lunch(String identifier, String actionsDescription, Started beginning, Stopped ending) implements Activity<Started, Stopped> {
    }
}
