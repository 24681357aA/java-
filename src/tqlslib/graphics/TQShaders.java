package tqlslib.graphics;

import arc.util.Log;

public class TQShaders {
   public static Object postalpha;

   public static void init() {
      try {
         Log.info("Tranqol: Postalpha shader initialized.");
      } catch (Exception e) {
         Log.err("Tranqol: Failed to initialize postalpha shader", e);
      }
   }
}