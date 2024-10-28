package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * Tests for the PeriodOfTime class.
 */
public class PeriodOfTimeTest {

    @Test
    public void test() {
        final var now = Instant.now();
        final var from = new ParticularSecond(now);
        final var to = new ParticularSecond(now.plusSeconds(OneDayInSeconds.oneDay.value()));
        final var oneDayStartingNow = new OneDayInSeconds(from, to);

        assertNotNull(oneDayStartingNow.beginning());
        assertTrue(oneDayStartingNow.beginning().isPresent());

        assertNotNull(oneDayStartingNow.ending());
        assertTrue(oneDayStartingNow.ending().isPresent());

        assertEquals(oneDayStartingNow.duration().value(), Long.valueOf(3600 * 24));
    }
}

class OneDayInSeconds implements PeriodOfTime<Long, Seconds> {

    public static DurationInSeconds oneDay = new DurationInSeconds(Long.valueOf(3600 * 24));

    private final ParticularSecond from;

    private final ParticularSecond to;

    private final String id;

    public String getId() {
        return id;
    }

    public OneDayInSeconds(final ParticularSecond from, final ParticularSecond to) {
        this.from = from;
        this.to = to;
        this.id = UUID.nameUUIDFromBytes((from.identifier() + to.identifier()).getBytes()).toString();
    }

    @Override
    public Optional<Event> beginning() {
        return Optional.ofNullable(from);
    }

    @Override
    public Optional<Event> ending() {
        return Optional.ofNullable(to);
    }

    @Override
    public ScalarValue<Long, Seconds> duration() {
        return oneDay;
    }

}

class ParticularSecond implements Event {

    private final String id;

    private final Instant when;

    private static final DurationInSeconds oneSecond = new DurationInSeconds(Long.valueOf(1L));

    public Instant getWhen() {
        return when;
    }

    public ParticularSecond(final Instant when) {
        this.when = when;
        id = UUID.nameUUIDFromBytes(when.toString().getBytes()).toString();
    }

    @Override
    public ScalarValue<Long, Seconds> duration() {
        return oneSecond;
    }

    @Override
    public Optional<Event> beginning() {
        return Optional.of(this);
    }

    @Override
    public Optional<Event> ending() {
        return Optional.of(this);
    }

    @Override
    public String identifier() {
        return id;
    }

}

class DurationInSeconds implements ScalarValue<Long, Seconds> {

    private final Long seconds;

    public DurationInSeconds(final Long seconds) {
        this.seconds = seconds;
    }

    @Override
    public Long value() {
        return seconds;
    }

    @Override
    public Seconds unit() {
        return Seconds.units;
    }

}

class Seconds implements Unit {

    public static final Seconds units = new Seconds();

    private final String id = UUID.nameUUIDFromBytes("Seconds".getBytes()).toString();

    private Seconds() {
    }

    @Override
    public String identifier() {
        return id;
    }

    @Override
    public String name() {
        return "Seconds";
    }

}
