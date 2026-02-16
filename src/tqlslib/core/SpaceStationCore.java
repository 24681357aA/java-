package tqlslib.core;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.type.Planet;
import tqlslib.DTVars;
import tqlslib.TQLSlib;
import tqlslib.api.SpaceStationAPI;
import tqlslib.event.events.SpaceStationCreateEvent;
import tqlslib.event.events.SpaceStationRemoveEvent;
import tqlslib.event.events.SpaceStationRenameEvent;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class SpaceStationCore implements SpaceStationAPI {

   @Override
   public SpaceStation createStation(Planet parent, String name) {
      try {
         if (parent == null) {
            Log.err("SpaceStationCore: Parent planet cannot be null");
            return null;
         }
         
         if (name == null || name.trim().isEmpty()) {
            Log.err("SpaceStationCore: Station name cannot be empty");
            return null;
         }

         Log.info("SpaceStationCore: Creating station '" + name + "' for planet " + parent.name);
         
         SpaceStation station = SpaceStation.create(parent, name);
         
         if (station != null) {
            Log.info("SpaceStationCore: Station created successfully with ID " + station.stationId);
            TQLSlib.getEventBus().post(new SpaceStationCreateEvent(station));
            return station;
         } else {
            Log.err("SpaceStationCore: Failed to create station");
            return null;
         }
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error creating station", e);
         return null;
      }
   }

   @Override
   public boolean removeStation(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            Log.err("SpaceStationCore: Station ID cannot be empty");
            return false;
         }

         if (!stationExists(stationId)) {
            Log.warn("SpaceStationCore: Station with ID " + stationId + " does not exist");
            return false;
         }

         Log.info("SpaceStationCore: Removing station with ID " + stationId);
         
         SpaceStation.remove(stationId);
         TQLSlib.getEventBus().post(new SpaceStationRemoveEvent(stationId));
         
         Log.info("SpaceStationCore: Station removed successfully");
         return true;
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error removing station", e);
         return false;
      }
   }

   @Override
   public boolean renameStation(String stationId, String newName) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            Log.err("SpaceStationCore: Station ID cannot be empty");
            return false;
         }
         
         if (newName == null || newName.trim().isEmpty()) {
            Log.err("SpaceStationCore: New name cannot be empty");
            return false;
         }

         SpaceStation station = getStationById(stationId);
         if (station == null) {
            Log.warn("SpaceStationCore: Station with ID " + stationId + " does not exist");
            return false;
         }

         String oldName = station.localizedName;
         Log.info("SpaceStationCore: Renaming station from '" + oldName + "' to '" + newName + "'");
         
         station.localizedName = newName;
         
         SpaceStationInfo info = getStationInfoById(stationId);
         if (info != null) {
            info.name = newName;
         }
         
         TQLSlib.getEventBus().post(new SpaceStationRenameEvent(stationId, oldName, newName));
         
         Log.info("SpaceStationCore: Station renamed successfully");
         return true;
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error renaming station", e);
         return false;
      }
   }

   @Override
   public Seq<SpaceStationInfo> getStationsByPlanet(Planet planet) {
      try {
         if (planet == null) {
            return new Seq<>();
         }
         return DTVars.getStationsByPlanet(planet);
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error getting stations by planet", e);
         return new Seq<>();
      }
   }

   @Override
   public SpaceStation getStationById(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            return null;
         }
         return DTVars.spaceStationMap.get(stationId);
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error getting station by ID", e);
         return null;
      }
   }

   @Override
   public SpaceStationInfo getStationInfoById(String stationId) {
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
         Log.err("SpaceStationCore: Error getting station info by ID", e);
         return null;
      }
   }

   @Override
   public int getNextStationNumber(Planet planet) {
      try {
         if (planet == null) {
            return 1;
         }
         return DTVars.getNextStationNumber(planet);
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error getting next station number", e);
         return 1;
      }
   }

   @Override
   public boolean stationExists(String stationId) {
      try {
         if (stationId == null || stationId.trim().isEmpty()) {
            return false;
         }
         return DTVars.spaceStationMap.containsKey(stationId);
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error checking if station exists", e);
         return false;
      }
   }

   @Override
   public Seq<SpaceStationInfo> getAllStations() {
      try {
         return new Seq<>(DTVars.spaceStationInfos);
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error getting all stations", e);
         return new Seq<>();
      }
   }

   @Override
   public int getStationCount() {
      try {
         return DTVars.spaceStationInfos.size;
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error getting station count", e);
         return 0;
      }
   }

   @Override
   public int getStationCountByPlanet(Planet planet) {
      try {
         if (planet == null) {
            return 0;
         }
         return getStationsByPlanet(planet).size;
      } catch (Exception e) {
         Log.err("SpaceStationCore: Error getting station count by planet", e);
         return 0;
      }
   }
}
