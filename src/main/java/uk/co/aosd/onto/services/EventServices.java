package uk.co.aosd.onto.services;

import java.time.Instant;

import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.events.Disaggregated;
import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.events.Sold;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;

/**
 * A set of services for manipulating Events.
 *
 * @author Tony Walmsley
 */
public interface EventServices {

    Created createCreatedEvent(String identifier, Instant from, Instant to);

    Deleted createDeletedEvent(String identifier, Instant from, Instant to);

    Birth createBirthEvent(String identifier, Instant from, Instant to);

    Death createDeathEvent(String identifier, Instant from, Instant to);

    Formed createFormedEvent(String identifier, Instant from, Instant to);

    Dissolved createDissolvedEvent(String identifier, Instant from, Instant to);

    Appointed createAppointedEvent(String identifier, Instant from, Instant to);

    Removed createRemovedEvent(String identifier, Instant from, Instant to);

    Resignified createResignifiedEvent(String identifier, Instant from, Instant to);

    Started createStartedEvent(String identifier, Instant from, Instant to);

    Stopped createStoppedEvent(String identifier, Instant from, Instant to);

    Built createBuiltEvent(String identifier, Instant from, Instant to);

    Scrapped createScrappedEvent(String identifier, Instant from, Instant to);

    Sold createSoldEvent(String identifier, Instant from, Instant to);

    TransferredTo createTransferredToEvent(String identifier, Instant from, Instant to);

    TransferredFrom createTransferredFromEvent(String identifier, Instant from, Instant to);

    Aggregated createAggregated(String identifier, Instant from, Instant to);

    Disaggregated createDisaggregated(String identifier, Instant from, Instant to);

}
