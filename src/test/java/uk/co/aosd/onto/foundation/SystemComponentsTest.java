package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.events.Built;
import uk.co.aosd.onto.events.Scrapped;
import uk.co.aosd.onto.reference.EventServicesImpl;
import uk.co.aosd.onto.services.EventServices;

/**
 * Explain how system, system_component, and installed_object are represented
 * using Java.
 *
 * @author Tony Walmsley
 */
public class SystemComponentsTest {

    private static final EventServices ev = new EventServicesImpl();

    // For this example we don't care about when these events happened.
    private static final Built cduFrom = ev.createBuiltEvent(randString(), null, null);
    private static final Built pump1From = ev.createBuiltEvent(randString(), null, null);
    private static final Built pump2From = ev.createBuiltEvent(randString(), null, null);

    private static final Scrapped cduTo = ev.createScrappedEvent(randString(), null, null);
    private static final Scrapped pump1To = ev.createScrappedEvent(randString(), null, null);
    private static final Scrapped pump2To = ev.createScrappedEvent(randString(), null, null);

    /**
     * This is a simple example based on the text from page 176 of "Developing High
     * Quality Data Models", by Matthew West.
     */
    @Test
    public void simpleExample() {

        final var pump1 = new Pump3000(randString(), "001", pump1From, pump1To);
        final var pump2 = new Pump3000(randString(), "002", pump2From, pump2To);

        // The CDU initially has no pump
        final var cdu = new CDU(randString(), null, cduFrom, cduTo);
        assertNull(cdu.p101());

        // Install pump1 as a system component for tag P101. Pump1 is now an installed
        // object.
        cdu.setP101(pump1);
        assertSame(pump1, cdu.p101());

        // Replace system component P101 (pump1) with pump2. Pump1 is no longer an
        // installed object and pump2 is now an installed object.
        cdu.setP101(pump2);
        assertSame(pump2, cdu.p101());

        // Reinstall pump1 as system component P101. Pump2 is no longer an installed
        // object and pump1 is an installed object again.
        cdu.setP101(pump1);
        assertSame(pump1, cdu.p101());

        // Remove the pump. System component P101 is no longer populated.
        cdu.setP101(null);
        assertNull(cdu.p101());
    }

    /**
     * This is a more complex example which keeps track of what was installed where
     * and when.
     */
    @Test
    public void complexExample() {
        // TODO
        fail("Not implemented yet");
    }

    /**
     * A basic representation of a Crude Distillation Unit.
     */
    private static class CDU implements Individual<Built, Scrapped> {

        private String identifier;
        private Pump3000 p101;

        public CDU(final String identifier, final Pump3000 p101, final Built from, final Scrapped to) {
            this.identifier = identifier;
            this.p101 = p101;
            this.from = from;
            this.to = to;
        }

        private Built from;
        private Scrapped to;

        @Override
        public Built beginning() {
            return from;
        }

        @Override
        public Scrapped ending() {
            return to;
        }

        @Override
        public String identifier() {
            return identifier;
        }

        public Pump3000 p101() {
            return p101;
        }

        public void setP101(final Pump3000 p101) {
            this.p101 = p101;
        }

    }

    /**
     * A basic representation of a pump.
     */
    private static record Pump3000(String identifier, String serialNumber, Built beginning, Scrapped ending) implements Individual<Built, Scrapped> {
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }
}
