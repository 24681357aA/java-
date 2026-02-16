package tqlslib.util;

import arc.util.Log;
import mindustry.gen.Building;
import mindustry.gen.Unit;

public class TQUtils {

   public static void logPowerGraphInfo(Object graph) {
      if (graph == null) {
         Log.info("Tranqol: Power graph is null");
         return;
      }
      
      Log.info("Tranqol: Power graph info:");
      Log.info("  Power graph object: @", graph.getClass().getName());
   }

   public static void logBuildingInfo(Building building) {
      if (building == null) {
         Log.info("Tranqol: Building is null");
         return;
      }
      
      Log.info("Tranqol: Building info:");
      Log.info("  Name: @", building.block.name);
      Log.info("  Position: @, @", building.x, building.y);
      Log.info("  Team: @", building.team);
      Log.info("  Health: @/@", building.health, building.maxHealth());
   }

   public static void logUnitInfo(Unit unit) {
      if (unit == null) {
         Log.info("Tranqol: Unit is null");
         return;
      }
      
      Log.info("Tranqol: Unit info:");
      Log.info("  Type: @", unit.type.name);
      Log.info("  Position: @, @", unit.x, unit.y);
      Log.info("  Team: @", unit.team);
      Log.info("  Health: @/@", unit.health, unit.maxHealth());
      Log.info("  Velocity: @, @", unit.vel.x, unit.vel.y);
   }

   public static String formatPower(float power) {
      if (power >= 1000000) {
         return String.format("%.1f MW", power / 1000000);
      } else if (power >= 1000) {
         return String.format("%.1f kW", power / 1000);
      } else {
         return String.format("%.1f W", power);
      }
   }
   
   public static void init() {
      Log.info("Tranqol: TQUtils initialized.");
   }
}
