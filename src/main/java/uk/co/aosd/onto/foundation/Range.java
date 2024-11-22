package uk.co.aosd.onto.foundation;

/**
 * Represents the uncertainty over some value.
 *
 * @param min
 *            the minimum value.
 * @param max
 *            the maximum value.
 * 
 * @author Tony Walmsley
 */
public record Range<T>(T min, T max) {
}