package uk.co.aosd.onto.foundation;

import java.util.Optional;

public record TemporallyBoundedRecord(Optional<PointInTime> beginning, Optional<PointInTime> ending)
        implements TemporallyBounded {

}
