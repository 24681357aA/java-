package tqlslib.event;

import arc.util.Time;

public abstract class TQLEvent {
    public final long timestamp;
    public final String sourceMod;
    
    protected TQLEvent() {
        this.timestamp = Time.millis();
        this.sourceMod = "tqlslib";
    }
}
