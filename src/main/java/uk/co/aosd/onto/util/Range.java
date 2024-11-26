package uk.co.aosd.onto.util;

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