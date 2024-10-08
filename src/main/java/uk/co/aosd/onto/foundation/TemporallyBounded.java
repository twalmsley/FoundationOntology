package uk.co.aosd.onto.foundation;

import java.util.Optional;

public interface TemporallyBounded {
  Optional<PointInTime> beginning();
  Optional<PointInTime> ending();
}
