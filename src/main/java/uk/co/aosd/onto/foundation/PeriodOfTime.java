package uk.co.aosd.onto.foundation;

import java.time.Duration;

/**
 * There are no PointInTime objects, there are only small time periods.
 *
 * @author Tony Walmsley
 */
public interface PeriodOfTime extends TemporallyBounded {
    /**
     * The duration.
     *
     * @return ScalarValue
     */
    Duration duration();
}
