package uk.co.aosd.onto.foundation;

import java.time.Instant;

public record PointInTimeRecord(String identifier, Instant when) implements PointInTime {

}
