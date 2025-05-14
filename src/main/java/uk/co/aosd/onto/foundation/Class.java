package uk.co.aosd.onto.foundation;

import java.util.Set;

/**
 * An arbitrary set of objects of type T. The objects can be distributed
 * throughout space and time and are not generally considered to be a single
 * object.
 * 
 * <p>In this ontology, a Class represents a collection of entities that share some
 * common characteristic or property, or that need to be referenced as a group. Unlike an {@link Individual}, which represents
 * a single entity, a Class represents a group of related entities.</p>
 * 
 * <p>The Class concept is similar to the concept of a class in set theory or a type
 * in programming languages, but with additional semantic richness. It allows for:</p>
 * <ul>
 *   <li>Grouping related entities together</li>
 *   <li>Creating taxonomies through class hierarchies</li>
 *   <li>Defining membership conditions</li>
 * </ul>
 * 
 * <p>Classes themselves are {@link UniquelyIdentifiable}, meaning they have their
 * own identity distinct from their members.</p>
 * 
 * <p>Examples of classes include:</p>
 * <ul>
 *   <li>The class of all people</li>
 *   <li>The class of all cars</li>
 *   <li>The class of all organizations</li>
 * </ul>
 * 
 * <p>Note that Classes in this ontology are extensional, meaning they are defined
 * by their members rather than by properties or conditions.</p>
 *
 * @param <T> The type of objects that can be members of this class
 * 
 * @author Tony Walmsley
 * @see UniquelyIdentifiable
 * @see Individual
 */
public interface Class<T extends UniquelyIdentifiable> extends UniquelyIdentifiable {
    /**
     * Gets the members of this class.
     * 
     * @return a set containing all members of this class
     */
    Set<T> getMembers();
}
