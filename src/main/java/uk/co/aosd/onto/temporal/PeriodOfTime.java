package uk.co.aosd.onto.temporal;

/**
 * There are no PointInTime objects, there are only small time periods.
 */
public interface PeriodOfTime extends TemporallyBounded {
    /**
     * The duration in milliseconds.
     *
     * @return long
     */
    long duration();
}
