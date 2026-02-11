package tqlslib;

import java.lang.reflect.Field;

import mindustry.Vars;
import mindustry.mod.ClassMap;
import mindustry.mod.Mods;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.worlds.blocks.distribution.*;
import tqlslib.worlds.blocks.power.PowerTower;

public class PWIntegration {
   private ExtendedParser parser = new ExtendedParser();

   public void loadContent() {
      this.putClasses();
      Class modClass = Mods.class;

      try {
         Field field = modClass.getDeclaredField("parser");
         field.setAccessible(true);
         field.set(Vars.mods, this.parser);
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public void putClasses() {
      ClassMap.classes.put("PowerTower", PowerTower.class);
      ClassMap.classes.put("ExtentionStatus", ExtentionStatus.class);
      ClassMap.classes.put("CoreUnloader_Output", CoreUnloader_Output.class);
      ClassMap.classes.put("CoreUnloader_Import", CoreUnloader_Import.class);
      ClassMap.classes.put("Liquid_Output", Liquid_Output.class);
      ClassMap.classes.put("Liquid_Import", Liquid_Import.class);
      ClassMap.classes.put("LiquidTransferStation", LiquidTransferStation.class);
   }
}