package tqlslib.core;

import arc.struct.Seq;
import mindustry.type.Planet;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class SpaceStationManager {
    private static final SpaceStationCore core = new SpaceStationCore();
    
    public static SpaceStationCore getCore() {
        return core;
    }

   public static SpaceStation createStation(Planet parent, String name) {
      return core.createStation(parent, name);
   }

   public static boolean removeStation(String stationId) {
      return core.removeStation(stationId);
   }

   public static boolean renameStation(String stationId, String newName) {
      return core.renameStation(stationId, newName);
   }

   public static Seq<SpaceStationInfo> getStationsByPlanet(Planet planet) {
      return core.getStationsByPlanet(planet);
   }

   public static SpaceStation getStationById(String stationId) {
      return core.getStationById(stationId);
   }

   public static SpaceStationInfo getStationInfoById(String stationId) {
      return core.getStationInfoById(stationId);
   }

   public static int getNextStationNumber(Planet planet) {
      return core.getNextStationNumber(planet);
   }

   public static boolean stationExists(String stationId) {
      return core.stationExists(stationId);
   }

   public static Seq<SpaceStationInfo> getAllStations() {
      return core.getAllStations();
   }

   public static int getStationCount() {
      return core.getStationCount();
   }

   public static int getStationCountByPlanet(Planet planet) {
      return core.getStationCountByPlanet(planet);
   }
}
