package tqlslib.util;

import arc.util.Log;
import mindustry.gen.Building;
import mindustry.gen.Unit;

@Deprecated
public class TQUtls {

   @Deprecated
   public static void logPowerGraphInfo(Object graph) {
      TQUtils.logPowerGraphInfo(graph);
   }

   @Deprecated
   public static void logBuildingInfo(Building building) {
      TQUtils.logBuildingInfo(building);
   }

   @Deprecated
   public static void logUnitInfo(Unit unit) {
      TQUtils.logUnitInfo(unit);
   }

   @Deprecated
   public static String formatTime(long milliseconds) {
      return TimeUtils.formatTime(milliseconds);
   }

   @Deprecated
   public static String formatPower(float power) {
      return TQUtils.formatPower(power);
   }
   
   @Deprecated
   public static void init() {
      Log.info("Tranqol: TQUtls initialized. (Deprecated: use TQUtils instead)");
      TQUtils.init();
   }
}
