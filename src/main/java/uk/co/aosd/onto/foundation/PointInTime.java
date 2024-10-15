package uk.co.aosd.onto.foundation;

import java.time.Instant;

public interface PointInTime extends Event {
    Instant when();
}
