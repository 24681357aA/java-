package tqlslib.ui;

import arc.util.Log;
import mindustry.Vars;
import mindustry.ui.dialogs.BaseDialog;

public class TQDialogs {
   public static PowerGraphInfoDialog powerGraphInfoDialog;

   public static void init() {
      if (Vars.headless) return;
      
      try {
         powerGraphInfoDialog = new PowerGraphInfoDialog();
         Log.info("Tranqol: Dialogs initialized.");
      } catch (Exception e) {
         Log.err("Tranqol: Failed to initialize dialogs", e);
      }
   }

   public static void showPowerGraphInfo() {
      if (powerGraphInfoDialog != null) {
         powerGraphInfoDialog.show();
      } else {
         Log.warn("Tranqol: Power graph info dialog not initialized");
      }
   }

   public static class PowerGraphInfoDialog extends BaseDialog {
      public PowerGraphInfoDialog() {
         super("Power Graph Info");
         
         cont.add("Power graph information will be displayed here").row();
         cont.button("Close", this::hide).size(120, 50);
      }
   }
}