package uk.co.aosd.onto.reference;

import java.time.Instant;

import uk.co.aosd.onto.events.Aggregated;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Assembled;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.events.Disaggregated;
import uk.co.aosd.onto.events.Disassembled;
import uk.co.aosd.onto.events.Dissolved;
import uk.co.aosd.onto.events.Formed;
import uk.co.aosd.onto.events.Installed;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.events.Sold;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.events.TransferredFrom;
import uk.co.aosd.onto.events.TransferredTo;
import uk.co.aosd.onto.reference.events.AggregatedImpl;
import uk.co.aosd.onto.reference.events.AppointedImpl;
import uk.co.aosd.onto.reference.events.AssembledImpl;
import uk.co.aosd.onto.reference.events.BirthImpl;
import uk.co.aosd.onto.reference.events.BuiltImpl;
import uk.co.aosd.onto.reference.events.CreatedImpl;
import uk.co.aosd.onto.reference.events.DeathImpl;
import uk.co.aosd.onto.reference.events.DeletedImpl;
import uk.co.aosd.onto.reference.events.DisaggregatedImpl;
import uk.co.aosd.onto.reference.events.DisassembledImpl;
import uk.co.aosd.onto.reference.events.DissolvedImpl;
import uk.co.aosd.onto.reference.events.FormedImpl;
import uk.co.aosd.onto.reference.events.InstalledImpl;
import uk.co.aosd.onto.reference.events.RemovedImpl;
import uk.co.aosd.onto.reference.events.ResignifiedImpl;
import uk.co.aosd.onto.reference.events.ScrappedImpl;
import uk.co.aosd.onto.reference.events.SoldImpl;
import uk.co.aosd.onto.reference.events.StartedImpl;
import uk.co.aosd.onto.reference.events.StoppedImpl;
import uk.co.aosd.onto.reference.events.TransferredFromImpl;
import uk.co.aosd.onto.reference.events.TransferredToImpl;
import uk.co.aosd.onto.services.EventServices;

/**
 * An implementation of the EventServices interface.
 *
 * @author Tony Walmsley
 */
public class EventServicesImpl implements EventServices {

    @Override
    public Created createCreatedEvent(final String identifier, final Instant from, final Instant to) {
        return new CreatedImpl(identifier, from, to);
    }

    @Override
    public Deleted createDeletedEvent(final String identifier, final Instant from, final Instant to) {
        return new DeletedImpl(identifier, from, to);
    }

    @Override
    public Birth createBirthEvent(final String identifier, final Instant from, final Instant to) {
        return new BirthImpl(identifier, from, to);
    }

    @Override
    public Death createDeathEvent(final String identifier, final Instant from, final Instant to) {
        return new DeathImpl(identifier, from, to);
    }

    @Override
    public Formed createFormedEvent(final String identifier, final Instant from, final Instant to) {
        return new FormedImpl(identifier, from, to);
    }

    @Override
    public Dissolved createDissolvedEvent(final String identifier, final Instant from, final Instant to) {
        return new DissolvedImpl(identifier, from, to);
    }

    @Override
    public Appointed createAppointedEvent(final String identifier, final Instant from, final Instant to) {
        return new AppointedImpl(identifier, from, to);
    }

    @Override
    public Removed createRemovedEvent(final String identifier, final Instant from, final Instant to) {
        return new RemovedImpl(identifier, from, to);
    }

    @Override
    public Resignified createResignifiedEvent(final String identifier, final Instant from, final Instant to) {
        return new ResignifiedImpl(identifier, from, to);
    }

    @Override
    public Started createStartedEvent(final String identifier, final Instant from, final Instant to) {
        return new StartedImpl(identifier, from, to);
    }

    @Override
    public Stopped createStoppedEvent(final String identifier, final Instant from, final Instant to) {
        return new StoppedImpl(identifier, from, to);
    }

    @Override
    public Built createBuiltEvent(final String identifier, final Instant from, final Instant to) {
        return new BuiltImpl(identifier, from, to);
    }

    @Override
    public Scrapped createScrappedEvent(final String identifier, final Instant from, final Instant to) {
        return new ScrappedImpl(identifier, from, to);
    }

    @Override
    public Sold createSoldEvent(final String identifier, final Instant from, final Instant to) {
        return new SoldImpl(identifier, from, to);
    }

    @Override
    public TransferredTo createTransferredToEvent(final String identifier, final Instant from, final Instant to) {
        return new TransferredToImpl(identifier, from, to);
    }

    @Override
    public TransferredFrom createTransferredFromEvent(final String identifier, final Instant from, final Instant to) {
        return new TransferredFromImpl(identifier, from, to);
    }

    @Override
    public Aggregated createAggregated(final String identifier, final Instant from, final Instant to) {
        return new AggregatedImpl(identifier, from, to);
    }

    @Override
    public Disaggregated createDisaggregated(final String identifier, final Instant from, final Instant to) {
        return new DisaggregatedImpl(identifier, from, to);
    }

    @Override
    public Assembled createAssembled(final String identifier, final Instant from, final Instant to) {
        return new AssembledImpl(identifier, from, to);
    }

    @Override
    public Installed createInstalled(final String identifier, final Instant from, final Instant to) {
        return new InstalledImpl(identifier, from, to);
    }

    @Override
    public Disassembled createDisassembled(final String identifier, final Instant from, final Instant to) {
        return new DisassembledImpl(identifier, from, to);
    }

}
