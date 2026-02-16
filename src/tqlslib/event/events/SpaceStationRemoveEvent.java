package tqlslib.event.events;

import tqlslib.event.TQLEvent;

public class SpaceStationRemoveEvent extends TQLEvent {
    public final String stationId;
    
    public SpaceStationRemoveEvent(String stationId) {
        this.stationId = stationId;
    }
}
