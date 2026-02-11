package tqlslib;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class TQLSlib extends Mod{

    public TQLSlib(){
        Log.info("Loaded TQLSlib constructor.");

        // Listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            // Show dialog on startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //Mod sprites are prefixed with the mod name (this mod is called 'tqlslib-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("tqlslib-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some tqlslib content.");
        
        // Load PW module functionality
        Log.info("Loading PW integration content.");
        PWIntegration pwIntegration = new PWIntegration();
        pwIntegration.loadContent();
        
        // Load tranqol module functionality
        Log.info("Loading Tranqol integration content.");
        TranqolIntegration.load();
    }
    
    @Override
    public void init() {
        super.init();
        
        // Initialize tranqol module functionality
        Log.info("Initializing Tranqol integration.");
        TranqolIntegration.init();
    }

}
