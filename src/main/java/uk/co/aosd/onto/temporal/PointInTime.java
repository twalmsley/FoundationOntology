package uk.co.aosd.onto.temporal;

import java.time.Instant;

import uk.co.aosd.onto.foundation.Event;

public interface PointInTime extends Event {
    Instant when();
}
