package uk.co.aosd.onto.foundation;

import java.util.Optional;

public record PeriodOfTimeRecord(String identifier, Optional<PointInTime> beginning, Optional<PointInTime> ending)
        implements PeriodOfTime {

}
