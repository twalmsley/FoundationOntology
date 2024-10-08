package uk.co.aosd.onto.app;

public class MeetingServices {
    private MeetingServices() {}

    private static MeetingServices instance;

    public static MeetingServices getInstance() {
        if (instance == null) {
            instance = new MeetingServices();
        }
        return instance;
    }
}
