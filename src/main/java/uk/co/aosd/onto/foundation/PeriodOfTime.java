package uk.co.aosd.onto.foundation;

/**
 * There are no PointInTime objects, there are only small time periods.
 */
public interface PeriodOfTime<T extends Number, U extends Unit> extends TemporallyBounded {
    /**
     * The duration.
     *
     * @return ScalarValue
     */
    ScalarValue<T, U> duration();
}
