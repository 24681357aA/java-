package tqlslib;

import arc.Events;
import arc.util.Log;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import tqlslib.api.ContentRegistry;
import tqlslib.api.SpaceStationAPI;
import tqlslib.core.ContentRegistryCore;
import tqlslib.core.SpaceStationCore;
import tqlslib.core.SpaceStationIO;
import tqlslib.graphics.TQDrawf;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.util.TQUtils;
import tqlslib.util.TQUtls;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Import;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Output;
import tqlslib.worlds.blocks.distribution.LiquidTransferStation;
import tqlslib.worlds.blocks.distribution.Liquid_Import;
import tqlslib.worlds.blocks.distribution.Liquid_Output;
import tqlslib.worlds.blocks.power.PowerTower;

public class TQLSlib extends Mod{
    private static final String PREFIX = "tqls-";
    
    private static final SpaceStationAPI spaceStationAPI = new SpaceStationCore();
    private static final ContentRegistry contentRegistry = new ContentRegistryCore();
    
    public static SpaceStationAPI getSpaceStationAPI() {
        return spaceStationAPI;
    }
    
    public static ContentRegistry getContentRegistry() {
        return contentRegistry;
    }

    public TQLSlib(){
        Log.info("Loaded TQLSlib constructor.");
    }

    @Override
    public void loadContent(){
        Log.info("Loading TQLSlib content.");
        
        DTVars.init();
        
        Log.info("Registering classes...");
        contentRegistry.registerClass("PowerTower", PowerTower.class);
        contentRegistry.registerClass("ExtentionStatus", ExtentionStatus.class);
        contentRegistry.registerClass("CoreUnloader_Output", CoreUnloader_Output.class);
        contentRegistry.registerClass("CoreUnloader_Import", CoreUnloader_Import.class);
        contentRegistry.registerClass("Liquid_Output", Liquid_Output.class);
        contentRegistry.registerClass("Liquid_Import", Liquid_Import.class);
        contentRegistry.registerClass("LiquidTransferStation", LiquidTransferStation.class);
        contentRegistry.registerClass("TQDrawf", TQDrawf.class);
        contentRegistry.registerClass("TQUtils", TQUtils.class);
        contentRegistry.registerClass("TQUtls", TQUtls.class);
        Log.info("Classes registered successfully with tqls- prefix");
        
        Log.info("Initializing utilities...");
        TQDrawf.init();
        TQUtils.init();
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
