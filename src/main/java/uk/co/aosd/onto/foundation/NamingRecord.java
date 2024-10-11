package uk.co.aosd.onto.foundation;

import java.util.Optional;

public record NamingRecord<T>(String identifier, T name, UniquelyIdentifiable named, Optional<PointInTime> beginning,
        Optional<PointInTime> ending) implements Naming<T> {

}
