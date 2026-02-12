package tqlslib.content;

import arc.util.Log;

public class TQTechTree {
   public static void load() {
      try {
         Log.info("Tranqol: Loading tech tree...");
         // Tech tree loading logic
         // Since no blocks need to be created, only basic initialization is done here
         Log.info("Tranqol: Tech tree loaded successfully.");
      } catch (Exception e) {
         Log.err("Tranqol: Failed to load tech tree", e);
      }
   }

   public static void init() {
      try {
         Log.info("Tranqol: Initializing tech tree...");
         // Tech tree initialization logic
         Log.info("Tranqol: Tech tree initialized successfully.");
      } catch (Exception e) {
         Log.err("Tranqol: Failed to initialize tech tree", e);
      }
   }
}