package tqlslib;

import arc.Events;
import arc.util.Log;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import tqlslib.core.SpaceStationIO;

public class TQLSlib extends Mod{

    public TQLSlib(){
        Log.info("Loaded TQLSlib constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading TQLSlib content.");
        
        DTVars.init();
        
        Log.info("Loading PW integration content.");
        PWIntegration pwIntegration = new PWIntegration();
        pwIntegration.loadContent();
        
        Log.info("Loading Tranqol integration content.");
        TranqolIntegration.load();
        
        Log.info("TQLSlib content loaded successfully.");
    }
    
    @Override
    public void init() {
        super.init();
        
        Log.info("Initializing TQLSlib.");
        
        Events.on(ClientLoadEvent.class, e -> {
            Log.info("TQLSlib: Loading space station data...");
            SpaceStationIO.load();
            Log.info("TQLSlib: Space station data loaded.");
        });
        
        Log.info("Initializing Tranqol integration.");
        TranqolIntegration.init();
        
        Log.info("TQLSlib initialized successfully.");
    }

}
