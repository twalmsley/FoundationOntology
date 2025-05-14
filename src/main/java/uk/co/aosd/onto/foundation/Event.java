package uk.co.aosd.onto.foundation;

/**
 * A significant occurrence or happening that marks a transition, beginning, or conclusion, often involving a change in circumstances or state.
 *
 * <p>In this ontology, events are fundamental temporal constructs that mark significant
 * changes or transitions. They serve multiple purposes:</p>
 * 
 * <ul>
 *   <li>Marking the beginning or ending of an {@link Individual}</li>
 *   <li>Representing state transitions</li>
 *   <li>Capturing significant occurrences in time</li>
 *   <li>Providing temporal boundaries for existential reasoning</li>
 * </ul>
 * 
 * <p>Events implement the {@link TimePeriod} interface because they themselves occur over a span of time,
 * having both a beginning and an end. For instantaneous events, the beginning and end times can be the same.</p>
 * 
 * <p>Events are also {@link UniquelyIdentifiable}, meaning they have unique identifiers that can be used
 * to reference them within a model.</p>
 * 
 * <p>The ontology defines many specific types of events such as:</p>
 * <ul>
 *   <li>Birth - The beginning of a biological organism</li>
 *   <li>Death - The ending of a biological organism</li>
 *   <li>Created - When something is created</li>
 *   <li>Deleted - When something is deleted</li>
 *   <li>Started - When an activity begins</li>
 *   <li>Stopped - When an activity ends</li>
 * </ul>
 *
 * @author Tony Walmsley
 * @see TimePeriod
 * @see UniquelyIdentifiable
 * @see Individual
 */
public interface Event extends TimePeriod, UniquelyIdentifiable {

}

