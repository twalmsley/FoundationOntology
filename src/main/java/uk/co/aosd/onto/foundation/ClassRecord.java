package uk.co.aosd.onto.foundation;

import java.util.Set;

public record ClassRecord(String identifier, Set<Object> members) implements Class<Object> {

}
