package uk.co.aosd.onto.app;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public record PlannedMeeting(
        String name,
        String description,
        MeetingRoom location,
        Instant dateTime,
        Duration duration,
        MeetingOrganiser organiser,
        Set<MeetingAttendee> attendees,
        String notes) {

}
