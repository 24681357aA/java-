package tqlslib;

import mindustry.mod.Mods;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.worlds.blocks.power.PowerTower;

public class ExtendedParser extends Mods {

   public Object parse(String type, Object... args) {
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
   }
}