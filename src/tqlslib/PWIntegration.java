package tqlslib;

import java.lang.reflect.Field;

import mindustry.Vars;
import mindustry.mod.ClassMap;
import mindustry.mod.Mods;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Import;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Output;
import tqlslib.worlds.blocks.distribution.LiquidTransferStation;
import tqlslib.worlds.blocks.distribution.Liquid_Import;
import tqlslib.worlds.blocks.distribution.Liquid_Output;
import tqlslib.worlds.blocks.power.PowerTower;
import tqlslib.content.TQTechTree;
import tqlslib.graphics.TQDrawf;
import tqlslib.graphics.TQShaders;
import tqlslib.ui.TQDialogs;
import tqlslib.util.TQUtls;

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
      // Tranqol integration classes
      ClassMap.classes.put("TQShaders", TQShaders.class);
      ClassMap.classes.put("TQDrawf", TQDrawf.class);
      ClassMap.classes.put("TQUtls", TQUtls.class);
      ClassMap.classes.put("TQDialogs", TQDialogs.class);
      ClassMap.classes.put("TQTechTree", TQTechTree.class);
      ClassMap.classes.put("TranqolIntegration", TranqolIntegration.class);
   }
}