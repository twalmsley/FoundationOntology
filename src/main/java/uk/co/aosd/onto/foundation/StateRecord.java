package uk.co.aosd.onto.foundation;

import java.util.Optional;

public record StateRecord<T extends UniquelyIdentifiable>(String identifier, T individual,
        Optional<PointInTime> beginning, Optional<PointInTime> ending) implements State<T> {

}
