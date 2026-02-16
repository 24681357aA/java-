package tqlslib;

import arc.files.Fi;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import mindustry.type.Planet;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class DTVars {
   public static String modName = "tqlslib";
   public static int spaceStationRequirement = 5000;
   public static int spaceStationBaseRequirement = 2;
   
   public static Fi DTRoot;
   public static Fi spaceStationFi;
   public static Seq<SpaceStationInfo> spaceStationInfos;
   public static ObjectMap<String, SpaceStation> spaceStationMap;

   public static void init() {
      try {
         DTRoot = Vars.dataDirectory.child(modName + "/");
         if (!DTRoot.exists()) {
            DTRoot.mkdirs();
         }
         spaceStationFi = DTRoot.child("space_stations");
         if (!spaceStationFi.exists()) {
            spaceStationFi.writeString("");
         }
         spaceStationInfos = new Seq<>();
         spaceStationMap = new ObjectMap<>();
         Log.info("DTVars: Initialized successfully.");
      } catch (Exception e) {
         Log.err("DTVars: Failed to initialize", e);
      }
   }

   public static int getNextStationNumber(Planet planet) {
      int maxNumber = 0;
      for (SpaceStationInfo info : spaceStationInfos) {
         if (info.parent == planet && info.stationNumber > maxNumber) {
            maxNumber = info.stationNumber;
         }
      }
      return maxNumber + 1;
   }

   public static Seq<SpaceStationInfo> getStationsByPlanet(Planet planet) {
      Seq<SpaceStationInfo> result = new Seq<>();
      for (SpaceStationInfo info : spaceStationInfos) {
         if (info.parent == planet) {
            result.add(info);
         }
      }
      return result;
   }

   public static String generateStationId(Planet planet) {
      return planet.name + "-station-" + System.currentTimeMillis();
   }
}
