package tqlslib;

import arc.Events;
import arc.util.Log;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.ClassMap;
import mindustry.mod.Mod;
import tqlslib.core.SpaceStationIO;
import tqlslib.graphics.TQDrawf;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.util.TQUtls;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Import;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Output;
import tqlslib.worlds.blocks.distribution.LiquidTransferStation;
import tqlslib.worlds.blocks.distribution.Liquid_Import;
import tqlslib.worlds.blocks.distribution.Liquid_Output;
import tqlslib.worlds.blocks.power.PowerTower;

public class TQLSlib extends Mod{
    private static final String PREFIX = "tqls-";

    public TQLSlib(){
        Log.info("Loaded TQLSlib constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading TQLSlib content.");
        
        DTVars.init();
        
        Log.info("Registering classes...");
        ClassMap.classes.put(PREFIX + "PowerTower", PowerTower.class);
        ClassMap.classes.put(PREFIX + "ExtentionStatus", ExtentionStatus.class);
        ClassMap.classes.put(PREFIX + "CoreUnloader_Output", CoreUnloader_Output.class);
        ClassMap.classes.put(PREFIX + "CoreUnloader_Import", CoreUnloader_Import.class);
        ClassMap.classes.put(PREFIX + "Liquid_Output", Liquid_Output.class);
        ClassMap.classes.put(PREFIX + "Liquid_Import", Liquid_Import.class);
        ClassMap.classes.put(PREFIX + "LiquidTransferStation", LiquidTransferStation.class);
        ClassMap.classes.put(PREFIX + "TQDrawf", TQDrawf.class);
        ClassMap.classes.put(PREFIX + "TQUtls", TQUtls.class);
        Log.info("Classes registered successfully with tqls- prefix");
        
        Log.info("Initializing utilities...");
        TQDrawf.init();
        TQUtls.init();
        Log.info("Utilities initialized.");
        
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
        
        Log.info("TQLSlib initialized successfully.");
    }

}
