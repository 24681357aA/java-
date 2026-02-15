package tqlslib.core;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.type.Planet;
import tqlslib.DTVars;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class SpaceStationManager {
    private static final SpaceStationCore core = new SpaceStationCore();
    
    public static SpaceStationCore getCore() {
        return core;
    }

   public static SpaceStation createStation(Planet parent, String name) {
      try {
         if (parent == null) {
            Log.err("SpaceStationManager: Parent planet cannot be null");
            return null;
         }
         
         if (name == null || name.trim().isEmpty()) {
            Log.err("SpaceStationManager: Station name cannot be empty");
            return null;
         }

         Log.info("SpaceStationManager: Creating station '" + name + "' for planet " + parent.name);
         
         SpaceStation station = SpaceStation.create(parent, name);
         
         if (station != null) {
            Log.info("SpaceStationManager: Station created successfully with ID " + station.stationId);
            return station;
         } else {
            Log.err("SpaceStationManager: Failed to create station");
            return null;
         }
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error creating station", e);
         return null;
      }
   }

   public static boolean removeStation(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            Log.err("SpaceStationManager: Station ID cannot be empty");
            return false;
         }

         if (!stationExists(stationId)) {
            Log.warn("SpaceStationManager: Station with ID " + stationId + " does not exist");
            return false;
         }

         Log.info("SpaceStationManager: Removing station with ID " + stationId);
         
         SpaceStation.remove(stationId);
         
         Log.info("SpaceStationManager: Station removed successfully");
         return true;
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error removing station", e);
         return false;
      }
   }

   public static boolean renameStation(String stationId, String newName) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            Log.err("SpaceStationManager: Station ID cannot be empty");
            return false;
         }
         
         if (newName == null || newName.trim().isEmpty()) {
            Log.err("SpaceStationManager: New name cannot be empty");
            return false;
         }

         SpaceStation station = getStationById(stationId);
         if (station == null) {
            Log.warn("SpaceStationManager: Station with ID " + stationId + " does not exist");
            return false;
         }

         Log.info("SpaceStationManager: Renaming station from '" + station.localizedName + "' to '" + newName + "'");
         
         station.localizedName = newName;
         
         SpaceStationInfo info = getStationInfoById(stationId);
         if (info != null) {
            info.name = newName;
         }
         
         Log.info("SpaceStationManager: Station renamed successfully");
         return true;
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error renaming station", e);
         return false;
      }
   }

   public static Seq<SpaceStationInfo> getStationsByPlanet(Planet planet) {
      try {
         if (planet == null) {
            return new Seq<>();
         }
         return DTVars.getStationsByPlanet(planet);
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting stations by planet", e);
         return new Seq<>();
      }
   }

   public static SpaceStation getStationById(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            return null;
         }
         return DTVars.spaceStationMap.get(stationId);
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting station by ID", e);
         return null;
      }
   }

   public static SpaceStationInfo getStationInfoById(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            return null;
         }
         for (SpaceStationInfo info : DTVars.spaceStationInfos) {
            if (info.id.equals(stationId)) {
               return info;
            }
         }
         return null;
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting station info by ID", e);
         return null;
      }
   }

   public static int getNextStationNumber(Planet planet) {
      try {
         if (planet == null) {
            return 1;
         }
         return DTVars.getNextStationNumber(planet);
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting next station number", e);
         return 1;
      }
   }

   public static boolean stationExists(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            return false;
         }
         return DTVars.spaceStationMap.containsKey(stationId);
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error checking if station exists", e);
         return false;
      }
   }

   public static Seq<SpaceStationInfo> getAllStations() {
      try {
         return new Seq<>(DTVars.spaceStationInfos);
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting all stations", e);
         return new Seq<>();
      }
   }

   public static int getStationCount() {
      try {
         return DTVars.spaceStationInfos.size;
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting station count", e);
         return 0;
      }
   }

   public static int getStationCountByPlanet(Planet planet) {
      try {
         if (planet == null) {
            return 0;
         }
         return getStationsByPlanet(planet).size;
      } catch (Exception e) {
         Log.err("SpaceStationManager: Error getting station count by planet", e);
         return 0;
      }
   }
}
