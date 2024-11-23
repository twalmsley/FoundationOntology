package uk.co.aosd.onto.topics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import uk.co.aosd.onto.biological.DNA;
import uk.co.aosd.onto.biological.Human;
import uk.co.aosd.onto.events.Appointed;
import uk.co.aosd.onto.events.Birth;
import uk.co.aosd.onto.events.Created;
import uk.co.aosd.onto.events.Death;
import uk.co.aosd.onto.events.Deleted;
import uk.co.aosd.onto.events.Removed;
import uk.co.aosd.onto.events.Resignified;
import uk.co.aosd.onto.events.Started;
import uk.co.aosd.onto.events.Stopped;
import uk.co.aosd.onto.foundation.Activity;
import uk.co.aosd.onto.foundation.Class;
import uk.co.aosd.onto.foundation.Event;
import uk.co.aosd.onto.foundation.Individual;
import uk.co.aosd.onto.foundation.JsonUtils;
import uk.co.aosd.onto.foundation.Role;
import uk.co.aosd.onto.foundation.UniquelyIdentifiable;
import uk.co.aosd.onto.language.Language;
import uk.co.aosd.onto.organisation.Membership;
import uk.co.aosd.onto.reference.ClassImpl;
import uk.co.aosd.onto.reference.DNAImpl;
import uk.co.aosd.onto.reference.HumanImpl;
import uk.co.aosd.onto.reference.LanguageImpl;
import uk.co.aosd.onto.reference.MembershipImpl;
import uk.co.aosd.onto.reference.SignifierImpl;
import uk.co.aosd.onto.signifying.Named;
import uk.co.aosd.onto.signifying.Signifier;

/**
 * Modelling research topics example.
 *
 * @author Tony Walmsley
 */
public class TopicsTest {

    @Test
    public void test() throws JsonProcessingException {
        // Create an object to represent the User
        final Language english = new LanguageImpl(randString(), "English");
        final Birth birth = new Birth(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(0));
        final Death death = new Death(randString(), null, null);
        final Class<Language> languages = new ClassImpl<>(randString(), Set.of(english));
        final DNA dna = new DNAImpl(randString(), "gattaca");
        final Resignified userNamedFrom = new Resignified(randString(), null, null);
        final Resignified userRenamedTo = new Resignified(randString(), null, null);
        final Signifier<String> username = new SignifierImpl<String>(randString(), "user1", english, userNamedFrom, userRenamedTo);
        final Class<Signifier<String>> usernames = new ClassImpl<>(randString(), Set.of(username));
        final var user1 = new User(randString(), birth, death, usernames, english, languages, dna);
        //
        // Create a Topic. A topic is an activity for researching some subject area that
        // can have sub-topics, can be named and renamed, has an expert, an owner, and a
        // set of contibutors. It can refer to source information which in turn can
        // refer to individuals, and individuals can refer to each other.
        //
        final Started from = new Started(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(0));
        final Stopped to = new Stopped(randString(), null, null);
        final Class<Membership<OwnerRole>> owners = new ClassImpl<>(randString(), Set.of());
        final Class<Membership<ExpertRole>> experts = new ClassImpl<>(randString(), Set.of());
        final Resignified topicNamed = new Resignified(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(0));
        final Resignified topicRenamed = new Resignified(randString(), null, null);
        final Signifier<String> name = new SignifierImpl<String>(randString(), "Ontologies Topic", english, topicNamed, topicRenamed);
        final Class<Signifier<String>> names = new ClassImpl<>(randString(), Set.of(name));
        final Resignified topicDescribed = new Resignified(randString(), null, null);
        final Resignified topicDescriptionUpdated = new Resignified(randString(), null, null);
        final Signifier<String> description = new SignifierImpl<String>(randString(), "Research into Ontologies", english, topicDescribed,
            topicDescriptionUpdated);
        final Class<Signifier<String>> descriptions = new ClassImpl<>(randString(), Set.of(description));
        final Class<Topic> subTopics = new ClassImpl<>(randString(), Set.of());
        final Class<Membership<ContributorRole>> contributors = new ClassImpl<>(randString(), Set.of());
        final Created sourceCreated = new Created(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(1));
        final Deleted sourceDeleted = new Deleted(randString(), null, null);
        final Source source = new Source(randString(), "http://www.google.com", sourceCreated, sourceDeleted, user1, user1);
        final Class<Source> sources = new ClassImpl<>(randString(), Set.of(source));
        final Individual<Started, Stopped> individual = new Thing(randString(), new Started(randString(), null, null), new Stopped(randString(), null, null));
        final Class<Individual<? extends Event, ? extends Event>> individuals = new ClassImpl<>(randString(), Set.of(individual));
        final SourceReference sourceReference = new SourceReference(randString(), source, SourceReferenceType.DOCUMENT, individual, user1, user1);
        final Class<SourceReference> sourceReferences = new ClassImpl<>(randString(), Set.of(sourceReference));
        final IndividualReference individualReference = new IndividualReference(randString(), IndividualReferenceType.WORKS_WITH, "References", source, source,
            from, to, user1, user1);
        final Class<IndividualReference> individualReferences = new ClassImpl<>(randString(), Set.of(individualReference));

        final var topic1 = new Topic(
            randString(),
            "Researching Ontologies",
            names,
            descriptions,
            subTopics,
            owners,
            experts,
            contributors,
            sources,
            individuals,
            sourceReferences,
            individualReferences,
            from,
            to,
            user1,
            user1);

        assertNotNull(topic1);

        // Create a Human as the owner, expert, and contributor
        final Resignified namedFrom = new Resignified(randString(), Instant.ofEpochSecond(0), Instant.ofEpochSecond(0));
        final Resignified namedTo = new Resignified(randString(), null, null);
        final Signifier<String> personName = new SignifierImpl<String>(randString(), "Alice", english, namedFrom, namedTo);
        final Class<Signifier<String>> personNames = new ClassImpl<>(randString(), Set.of(personName));

        final Human newOwner = new HumanImpl(randString(), birth, death, personNames, english, languages, dna);
        final Human newExpert = newOwner;
        final Human contributor = newOwner;

        // Create the owner temporal extent.
        final Appointed ownerFrom = new Appointed(randString(), Instant.ofEpochSecond(1000), Instant.ofEpochSecond(1000));
        final Removed ownerTo = new Removed(randString(), null, null);

        // Create the expert temporal extent.
        final Appointed expertFrom = new Appointed(randString(), Instant.ofEpochSecond(1000), Instant.ofEpochSecond(1000));
        final Removed expertTo = new Removed(randString(), null, null);

        // Create the contributor temporal extent.
        final Appointed contributorFrom = new Appointed(randString(), Instant.ofEpochSecond(1000), Instant.ofEpochSecond(1000));
        final Removed contributorTo = new Removed(randString(), null, null);

        // Create the necessary Memberships for each role.
        final MembershipImpl<OwnerRole> ownerMembership = new MembershipImpl<>(randString(), newOwner, ownerRole, ownerFrom, ownerTo);
        final MembershipImpl<ExpertRole> expertMembership = new MembershipImpl<>(randString(), newExpert, expertRole, expertFrom, expertTo);
        final MembershipImpl<ContributorRole> contributorMembership = new MembershipImpl<>(randString(), contributor, contributorRole,
            contributorFrom, contributorTo);

        // Update the topic.
        final Topic updatedWithOwner = addOwner(topic1, ownerMembership, user1);
        final Topic updatedWithExpert = addExpert(updatedWithOwner, expertMembership, user1);
        final Topic updatedWithContributor = addContributor(updatedWithExpert, contributorMembership, user1);

        // Check the result.
        assertTrue(updatedWithContributor.owners().members().contains(ownerMembership));
        assertTrue(updatedWithContributor.experts().members().contains(expertMembership));
        assertTrue(updatedWithContributor.contributors().members().contains(contributorMembership));

        final var json = JsonUtils.writeJsonString(updatedWithContributor);
        final var deserialised = JsonUtils.readJsonString(json, Topic.class);
        assertEquals(deserialised, updatedWithContributor);
    }

    /**
     * Add a contributor.
     *
     * @param t
     *            Topic
     * @param contributor
     *            Human
     * @param beginning
     *            Appointed
     * @param ending
     *            Removed
     * @return Topic
     */
    private static Topic addContributor(final Topic t, final Membership<ContributorRole> contributor, final User updater) {

        final Set<Membership<ContributorRole>> contributorSet = new HashSet<>();
        contributorSet.addAll(t.contributors().members());
        contributorSet.add(contributor);
        final Class<Membership<ContributorRole>> contributors = new ClassImpl<>(t.contributors().identifier(), contributorSet);

        return new Topic(t.identifier(), t.actionsDescription(), t.names(), t.descriptions(), t.subTopics(), t.owners(), t.experts(), contributors, t.sources(),
            t.individuals(), t.sourceReferences(), t.individualReferences(), t.beginning(), t.ending(), t.createdBy(), updater);
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
    private Topic addExpert(final Topic t, final Membership<ExpertRole> expert, final User updater) {
        final Set<Membership<ExpertRole>> expertsSet = new HashSet<>();
        expertsSet.addAll(t.experts().members());
        expertsSet.add(expert);

        final Class<Membership<ExpertRole>> experts = new ClassImpl<>(randString(), expertsSet);

        return new Topic(t.identifier(), t.actionsDescription(), t.names(), t.descriptions(), t.subTopics(), t.owners(), experts, t.contributors(), t.sources(),
            t.individuals(), t.sourceReferences(), t.individualReferences(), t.beginning(), t.ending(), t.createdBy(), updater);
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
    private static Topic addOwner(final Topic t, final Membership<OwnerRole> owner, final User updater) {
        final Set<Membership<OwnerRole>> ownersSet = new HashSet<>();
        ownersSet.addAll(t.owners().members());
        ownersSet.add(owner);

        final Class<Membership<OwnerRole>> owners = new ClassImpl<>(randString(), ownersSet);

        return new Topic(t.identifier(), t.actionsDescription(), t.names(), t.descriptions(), t.subTopics(), owners, t.experts(), t.contributors(), t.sources(),
            t.individuals(), t.sourceReferences(), t.individualReferences(), t.beginning(), t.ending(), t.createdBy(), updater);
    }

    private static String randString() {
        return UUID.randomUUID().toString();
    }

    private static record Topic(
        String identifier,
        String actionsDescription,
        Class<Signifier<String>> names,
        Class<Signifier<String>> descriptions,
        Class<Topic> subTopics,
        Class<Membership<OwnerRole>> owners,
        Class<Membership<ExpertRole>> experts,
        Class<Membership<ContributorRole>> contributors,
        Class<Source> sources,
        Class<Individual<? extends Event, ? extends Event>> individuals,
        Class<SourceReference> sourceReferences,
        Class<IndividualReference> individualReferences,
        Started beginning,
        Stopped ending,
        User createdBy,
        User updatedBy) implements Activity<Started, Stopped>, Named {
        public Topic {
            ensureValid(beginning, ending);
        }
    }

    private static record Source(
        String identifier,
        String reference,
        Created beginning,
        Deleted ending,
        User createdBy,
        User updatedBy) implements Individual<Created, Deleted> {
        public Source {
            ensureValid(beginning, ending);
        }
    }

    private static record SourceReference(
        String identifier,
        Source source,
        SourceReferenceType type,
        Individual<? extends Event, ? extends Event> individual,
        User createdBy,
        User updatedBy) implements UniquelyIdentifiable {
    }

    private static record IndividualReference(
        String identifier,
        IndividualReferenceType type,
        String actionsDescription,
        Individual<? extends Event, ? extends Event> from,
        Individual<? extends Event, ? extends Event> to,
        Started beginning,
        Stopped ending,
        User createdBy,
        User updatedBy) implements Activity<Started, Stopped> {
        public IndividualReference {
            ensureValid(beginning, ending);
        }
    }

    private static enum SourceReferenceType {
        URI, DOCUMENT, VIDEO, AUDIO, TEXT
    }

    private static enum IndividualReferenceType {
        WORKS_WITH, EMPLOYS, MANAGES
    }

    private static final ExpertRole expertRole = new ExpertRole(randString(), "Expert");
    private static final OwnerRole ownerRole = new OwnerRole(randString(), "Owner");
    private static final ContributorRole contributorRole = new ContributorRole(randString(), "Contributor");

    private static record ExpertRole(String identifier, String name) implements Role {
    }

    private static record OwnerRole(String identifier, String name) implements Role {
    }

    private static record ContributorRole(String identifier, String name) implements Role {
    }

    private static final record Thing(String identifier, Started beginning, Stopped ending) implements Individual<Started, Stopped> {
    }

    private static final record User(String identifier,
        Birth beginning,
        Death ending,
        Class<Signifier<String>> names,
        Language nativeLanguage,
        Class<Language> languages,
        DNA dna) implements Human {
        public User {
            ensureValid(beginning, ending);
        }
    }
}
