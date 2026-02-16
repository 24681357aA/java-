package tqlslib.event.events;

import tqlslib.event.TQLEvent;

public class SpaceStationRenameEvent extends TQLEvent {
    public final String stationId;
    public final String oldName;
    public final String newName;
    
    public SpaceStationRenameEvent(String stationId, String oldName, String newName) {
        this.stationId = stationId;
        this.oldName = oldName;
        this.newName = newName;
    }
}
