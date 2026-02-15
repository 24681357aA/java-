package tqlslib.event.events;

import tqlslib.event.TQLEvent;
import tqlslib.type.SpaceStation;

public class SpaceStationCreateEvent extends TQLEvent {
    public final SpaceStation station;
    
    public SpaceStationCreateEvent(SpaceStation station) {
        this.station = station;
    }
}
