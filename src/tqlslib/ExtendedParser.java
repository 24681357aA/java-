package tqlslib;

import arc.util.Log;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Import;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Output;
import tqlslib.worlds.blocks.distribution.LiquidTransferStation;
import tqlslib.worlds.blocks.distribution.Liquid_Import;
import tqlslib.worlds.blocks.distribution.Liquid_Output;
import tqlslib.worlds.blocks.power.PowerTower;

public class ExtendedParser {

   public static void init() {
      try {
         Log.info("ExtendedParser: Initializing...");
      } catch (Exception e) {
         Log.err("ExtendedParser: Failed to initialize", e);
      }
   }

   public Object parse(String type, Object... args) {
      try {
         if (type.equals("PowerTower")) {
            return new PowerTower((String)args[0]);
         }
         if (type.equals("ExtentionStatus")) {
            return new ExtentionStatus((String)args[0]);
         }
         if (type.equals("CoreUnloader_Output")) {
            return new CoreUnloader_Output((String)args[0]);
         }
         if (type.equals("CoreUnloader_Import")) {
            return new CoreUnloader_Import((String)args[0]);
         }
         if (type.equals("Liquid_Output")) {
            return new Liquid_Output((String)args[0]);
         }
         if (type.equals("Liquid_Import")) {
            return new Liquid_Import((String)args[0]);
         }
         if (type.equals("LiquidTransferStation")) {
            return new LiquidTransferStation((String)args[0]);
         }
         return null;
      } catch (Exception e) {
         Log.err("ExtendedParser: Failed to parse class", e);
         return null;
      }
   }
}