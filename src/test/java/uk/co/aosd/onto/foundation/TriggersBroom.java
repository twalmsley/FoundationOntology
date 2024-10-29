package uk.co.aosd.onto.foundation;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * An example to show object composition and how it changes over time.
 * <p>
 * Trigger's broom consists of some bristles, a broom head, a broom handle, and
 * a bracket to connect the handle to the head. In this example we construct a
 * broom and replace some or all of its parts.
 * </p>
 */
public class TriggersBroom {

    private static final String BROOM_HANDLE_1_ID = "Broom Handle 1";
    private static final String BROOM_HEAD_1_ID = "Broom Head 1";
    private static final String BRISTLES_1_ID = "Bristles 1";
    private static final String BROOM_BRACKET_1_ID = "Broom Bracket 1";
    private static final String BROOM_HEAD_ASSEMBLY_1_ID = "Broom Head Assembly 1";
    private static final String BROOM_HEAD_WITH_BRACKET_ASSEMBLY_1_ID = "Broom Head Assembly With Bracket 1";
    private static final String BROOM_1_ID = "Broom 1";

    @Test
    public void test() {
        //
        // Create the parts (ignoring their beginning and ending events to keep the
        // example simple).
        //
        final var broomHandle = new BroomHandle(BROOM_HANDLE_1_ID);
        final var broomHead = new BroomHead(BROOM_HEAD_1_ID);
        final var bristles = new Bristles(BRISTLES_1_ID);
        final var broomBracket = new BroomBracket(BROOM_BRACKET_1_ID);

        //
        // Fit the bristles in the head.
        //
        final var broomHeadAssembly = fitBristles(BROOM_HEAD_ASSEMBLY_1_ID, broomHead, bristles);

        //
        // Attach the bracket to the head.
        //
        final var broomHeadWithBracketAssembly = fitBracket(BROOM_HEAD_WITH_BRACKET_ASSEMBLY_1_ID, broomHeadAssembly,
            broomBracket);

        //
        // Attach the handle to the bracket to complete the broom.
        //
        final var broom = fitHandle(BROOM_1_ID, broomHandle, broomHeadWithBracketAssembly);

        //
        // Check the composition is correct.
        //
        assertSame(broom.getHandle(), broomHandle);
        assertSame(broom.getHeadWithBracketAssembly().getBracket(), broomBracket);
        assertSame(broom.getHeadWithBracketAssembly().getHeadAssembly().getBristles(), bristles);
        assertSame(broom.getHeadWithBracketAssembly().getHeadAssembly().getHead(), broomHead);
    }

    /**
     * Ideally this would also set the beginning event, but this is a simple
     * example.
     *
     * @param id
     *            String
     * @param broomHandle
     *            BroomHandle
     * @param broomHeadWithBracketAssembly
     *            BroomHeadWithBracketAssembly
     * @return Broom
     */
    private Broom fitHandle(final String id, final BroomHandle broomHandle,
        final BroomHeadWithBracketAssembly broomHeadWithBracketAssembly) {
        return new Broom(id, broomHandle, broomHeadWithBracketAssembly);
    }

    /**
     * Ideally this would set the beginning event, but this is a simple example.
     *
     * @param id
     *            String
     * @param broomHeadAssembly
     *            BroomHeadAssembly
     * @param broomBracket
     *            BroomBracket
     * @return BroomHeadWithBracketAssembly
     */
    private static BroomHeadWithBracketAssembly fitBracket(final String id, final BroomHeadAssembly broomHeadAssembly,
        final BroomBracket broomBracket) {
        return new BroomHeadWithBracketAssembly(id, broomHeadAssembly, broomBracket);
    }

    /**
     * Ideally this would also set the beginning event, but I'm keeping this example
     * simple.
     *
     * @param id
     *            String
     * @param head
     *            BroomHead
     * @param bristles
     *            Bristles
     * @return BroomHeadAssembly
     */
    private static BroomHeadAssembly fitBristles(final String id, final BroomHead head, final Bristles bristles) {
        return new BroomHeadAssembly(id, head, bristles);
    }

}

class Broom implements Individual<Integer, Days> {

    private final String id;

    private final BroomHandle handle;

    private final BroomHeadWithBracketAssembly headWithBracketAssembly;

    public Broom(final String id, final BroomHandle handle,
        final BroomHeadWithBracketAssembly headWithBracketAssembly) {
        this.id = id;
        this.handle = handle;
        this.headWithBracketAssembly = headWithBracketAssembly;
    }

    public BroomHandle getHandle() {
        return handle;
    }

    public BroomHeadWithBracketAssembly getHeadWithBracketAssembly() {
        return headWithBracketAssembly;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

class BroomHeadAssembly implements Individual<Integer, Days> {

    private final String id;

    private final BroomHead head;

    private final Bristles bristles;

    public BroomHeadAssembly(final String id, final BroomHead head, final Bristles bristles) {
        this.id = id;
        this.head = head;
        this.bristles = bristles;
    }

    public BroomHead getHead() {
        return head;
    }

    public Bristles getBristles() {
        return bristles;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

class BroomHeadWithBracketAssembly implements Individual<Integer, Days> {

    private final String id;

    private final BroomHeadAssembly headAssembly;

    private final BroomBracket bracket;

    public BroomHeadWithBracketAssembly(final String id, final BroomHeadAssembly headAssembly,
        final BroomBracket bracket) {
        this.id = id;
        this.headAssembly = headAssembly;
        this.bracket = bracket;
    }

    public BroomHeadAssembly getHeadAssembly() {
        return headAssembly;
    }

    public BroomBracket getBracket() {
        return bracket;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

class BroomHandle implements Individual<Integer, Days> {

    private final String id;

    public BroomHandle(final String id) {
        this.id = id;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

class BroomHead implements Individual<Integer, Days> {

    private final String id;

    public BroomHead(final String id) {
        this.id = id;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

class Bristles implements Individual<Integer, Days> {

    private final String id;

    public Bristles(final String id) {
        this.id = id;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

class BroomBracket implements Individual<Integer, Days> {

    private final String id;

    public BroomBracket(final String id) {
        this.id = id;
    }

    @Override
    public Optional<Event<Integer, Days>> beginning() {
        return Optional.empty();
    }

    @Override
    public Optional<Event<Integer, Days>> ending() {
        return Optional.empty();
    }

    @Override
    public String identifier() {
        return id;
    }

}

/**
 * The 'Days' unit of time.
 */
class Days implements Unit {

    public static final Days units = new Days();

    private final String id = UUID.nameUUIDFromBytes("Days".getBytes()).toString();

    private Days() {
    }

    @Override
    public String identifier() {
        return id;
    }

    @Override
    public String name() {
        return "Days";
    }

}
