package uk.co.aosd.onto.topics;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.reference.ClassImpl;
import uk.co.aosd.onto.signifying.Named;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * Modelling research topics example.
 *
 * @author Tony Walmsley
 */
public class TopicsTest {

    @Test
    public void test() {
        //
        // Create a Topic. A topic is an activity for researching some subject area that
        // can have sub-topics, can be named and renamed, has an expert, an owner, and a
        // set of contibutors. It can refer to source information which in turn can
        // refer to individuals, and individuals can be related to each other.
        //
        final Human owner = null;
        final Human expert = null;
        final Started from = null;
        final Stopped to = null;
        final Class<Signifier<String>> names = new ClassImpl<>(randString(), Set.of());
        final Class<Topic> subTopics = new ClassImpl<>(randString(), Set.of());
        final Class<Human> contributors = new ClassImpl<>(randString(), Set.of());
        final Class<Source> sources = new ClassImpl<>(randString(), Set.of());
        final Class<Individual<? extends Event, ? extends Event>> individuals = new ClassImpl<>(randString(), Set.of());
        final Class<SourceReference> sourceReferences = new ClassImpl<>(randString(), Set.of());
        final Class<IndividualReference> individualReferences = new ClassImpl<>(randString(), Set.of());

        final var topic1 = new Topic(
            randString(),
            "Researching Ontologies",
            names,
            subTopics,
            owner,
            expert,
            contributors,
            sources,
            individuals,
            sourceReferences,
            individualReferences,
            from,
            to);

        assertNotNull(topic1);

        final Human newOwner = null;
        final Human newExpert = null;
        final Human contributor = null;

        final Topic updatedWithOwner = addOwner(topic1, newOwner);
        final Topic updatedWithExpert = addExpert(updatedWithOwner, newExpert);
        final Topic updatedWithContributor = addContributor(updatedWithExpert, contributor);

        assertSame(newOwner, updatedWithContributor.owner());
        assertSame(newExpert, updatedWithContributor.expert());
        assertTrue(updatedWithContributor.contributors().members().contains(contributor));
        //
        // Create a set of users.
        //
        final Class<Human> users = new ClassImpl<>(randString(), Set.of());

        assertNotNull(users);
    }

    /**
     * Add a contributor.
     *
     * @param t
     *            Topic
     * @param contributor
     *            Human
     * @return Topic
     */
    private static Topic addContributor(final Topic t, final Human contributor) {

        // TODO: Make the contributors temporal.
        //
        final Set<Human> contributorSet = new HashSet<>();
        contributorSet.addAll(t.contributors().members());
        contributorSet.add(contributor);
        final Class<Human> contributors = new ClassImpl<>(t.contributors().identifier(), contributorSet);

        return new Topic(t.identifier(), t.actionsDescription(), t.names(), t.subTopics(), t.owner(), t.expert(), contributors, t.sources(), t.individuals(),
            t.sourceReferences(), t.individualReferences(), t.beginning(), t.ending());
    }

    /**
     * Set the Expert.
     *
     * @param t
     *            Topic
     * @param expert
     *            Human
     * @return Topic
     */
    private Topic addExpert(final Topic t, final Human expert) {
        // TODO: Make the expert temporal.
        //
        return new Topic(t.identifier(), t.actionsDescription(), t.names(), t.subTopics(), t.owner(), expert, t.contributors(), t.sources(), t.individuals(),
            t.sourceReferences(), t.individualReferences(), t.beginning(), t.ending());
    }

    /**
     * Set the owner.
     *
     * @param t
     *            Topic
     * @param owner
     *            Human
     * @return Topic
     */
    private static Topic addOwner(final Topic t, final Human owner) {
        // TODO: Make the owner temporal - use ownership.
        //
        return new Topic(t.identifier(), t.actionsDescription(), t.names(), t.subTopics(), owner, t.expert(), t.contributors(), t.sources(), t.individuals(),
            t.sourceReferences(), t.individualReferences(), t.beginning(), t.ending());
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }

    private static record Topic(
        String identifier,
        String actionsDescription,
        Class<Signifier<String>> names,
        Class<Topic> subTopics,
        Human owner,
        Human expert,
        Class<Human> contributors,
        Class<Source> sources,
        Class<Individual<? extends Event, ? extends Event>> individuals,
        Class<SourceReference> sourceReferences,
        Class<IndividualReference> individualReferences,
        Started beginning,
        Stopped ending) implements Activity<Started, Stopped>, Named {
    }

    private static record Source(
        String identifier,
        String reference,
        Created beginning,
        Deleted ending) implements Individual<Created, Deleted> {
    }

    private static record SourceReference(
        String identifier,
        Source source,
        SourceReferenceType type,
        Individual<? extends Event, ? extends Event> individual) implements UniquelyIdentifiable {
    }

    private static record IndividualReference(
        String identifier,
        IndividualReferenceType type,
        String actionsDescription,
        Individual<? extends Event, ? extends Event> from,
        Individual<? extends Event, ? extends Event> to,
        Started beginning,
        Stopped ending) implements Activity<Started, Stopped> {
    }

    private static enum SourceReferenceType {
        URI, DOCUMENT, VIDEO, AUDIO, TEXT
    }

    private static enum IndividualReferenceType {
        WORKS_WITH, EMPLOYS, MANAGES
    }
}
