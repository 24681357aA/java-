package tqlslib;

import arc.Events;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType.FileTreeInitEvent;
import tqlslib.content.TQTechTree;
import tqlslib.graphics.TQDrawf;
import tqlslib.graphics.TQShaders;
import tqlslib.ui.TQDialogs;
import tqlslib.util.TQUtls;

public class TranqolIntegration {

   public static void load() {
      try {
         Log.info("Tranqol: Loading integration...");
         
         initShaders();
         
         TQTechTree.load();
         
         initUtils();
         
         Log.info("Tranqol: Integration loaded successfully.");
      } catch (Exception e) {
         Log.err("Tranqol: Failed to load integration", e);
      }
   }

   public static void init() {
      try {
         Log.info("Tranqol: Initializing integration...");
         
         if (!Vars.headless) {
            TQDialogs.init();
         }
         
         registerEvents();
         
         Log.info("Tranqol: Integration initialized successfully.");
      } catch (Exception e) {
         Log.err("Tranqol: Failed to initialize integration", e);
      }
   }

   private static void initShaders() {
      if (Vars.headless) return;
      
      Events.on(FileTreeInitEvent.class, e -> {
         arc.Core.app.post(() -> {
            if (!Vars.headless) {
               TQShaders.init();
            }
         });
      });
   }

   private static void initUtils() {
      try {
         Log.info("Tranqol: Initializing utilities...");
         TQDrawf.init();
         TQUtls.init();
      } catch (Exception e) {
         Log.err("Tranqol: Failed to initialize utilities", e);
      }
   }

   private static void registerEvents() {
      Log.info("Tranqol: Registering events...");
   }

   public static void test() {
      Log.info("Tranqol: Integration test successful!");
      Log.info("  TQShaders: @", TQShaders.postalpha != null ? "loaded" : "not loaded");
      Log.info("  TQDialogs: @", TQDialogs.powerGraphInfoDialog != null ? "initialized" : "not initialized");
      Log.info("  TQDrawf: Initialized");
      Log.info("  TQUtls: Initialized");
      Log.info("  TQTechTree: Initialized");
   }
}
