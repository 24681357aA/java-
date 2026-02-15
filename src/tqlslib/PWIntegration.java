package tqlslib;

import arc.util.Log;
import mindustry.mod.ClassMap;
import tqlslib.content.TQTechTree;
import tqlslib.graphics.TQDrawf;
import tqlslib.graphics.TQShaders;
import tqlslib.type.status.ExtentionStatus;
import tqlslib.ui.TQDialogs;
import tqlslib.util.TQUtls;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Import;
import tqlslib.worlds.blocks.distribution.CoreUnloader_Output;
import tqlslib.worlds.blocks.distribution.LiquidTransferStation;
import tqlslib.worlds.blocks.distribution.Liquid_Import;
import tqlslib.worlds.blocks.distribution.Liquid_Output;
import tqlslib.worlds.blocks.power.PowerTower;

public class PWIntegration {
   private static final String PREFIX = "tqls-";

   public void loadContent() {
      this.putClasses();
      Log.info("PWIntegration: Classes registered successfully with tqls- prefix");
   }

   public void putClasses() {
      ClassMap.classes.put(PREFIX + "PowerTower", PowerTower.class);
      ClassMap.classes.put(PREFIX + "ExtentionStatus", ExtentionStatus.class);
      ClassMap.classes.put(PREFIX + "CoreUnloader_Output", CoreUnloader_Output.class);
      ClassMap.classes.put(PREFIX + "CoreUnloader_Import", CoreUnloader_Import.class);
      ClassMap.classes.put(PREFIX + "Liquid_Output", Liquid_Output.class);
      ClassMap.classes.put(PREFIX + "Liquid_Import", Liquid_Import.class);
      ClassMap.classes.put(PREFIX + "LiquidTransferStation", LiquidTransferStation.class);
      ClassMap.classes.put(PREFIX + "TQShaders", TQShaders.class);
      ClassMap.classes.put(PREFIX + "TQDrawf", TQDrawf.class);
      ClassMap.classes.put(PREFIX + "TQUtls", TQUtls.class);
      ClassMap.classes.put(PREFIX + "TQDialogs", TQDialogs.class);
      ClassMap.classes.put(PREFIX + "TQTechTree", TQTechTree.class);
      ClassMap.classes.put(PREFIX + "TranqolIntegration", TranqolIntegration.class);
   }
}
