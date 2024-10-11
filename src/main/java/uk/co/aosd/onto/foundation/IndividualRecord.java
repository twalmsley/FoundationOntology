package uk.co.aosd.onto.foundation;

import java.util.Optional;

public record IndividualRecord(String identifier, Optional<PointInTime> beginning, Optional<PointInTime> ending)
        implements Individual {

}
