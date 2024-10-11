package uk.co.aosd.onto.foundation;

import java.util.Set;

public record AggregationRecord(String identifier, Set<Object> members) implements Aggregation {

}
