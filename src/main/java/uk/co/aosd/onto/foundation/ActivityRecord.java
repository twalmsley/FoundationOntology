package uk.co.aosd.onto.foundation;

import java.util.Optional;

public record ActivityRecord(String identifier, Optional<PointInTime> beginning, Optional<PointInTime> ending)
        implements Activity {

}
