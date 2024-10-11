package uk.co.aosd.onto.foundation;

import java.util.Optional;
import java.util.Set;

public record PossibleWorldRecord(String identifier, Set<Object> members, Optional<PointInTime> beginning,
        Optional<PointInTime> ending) implements PossibleWorld {

}
