package tqlslib.core;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.type.Planet;
import tqlslib.DTVars;
import tqlslib.api.SpaceStationAPI;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class SpaceStationCore implements SpaceStationAPI {

   @Override
   public SpaceStation createStation(Planet parent, String name) {
      return SpaceStationManager.createStation(parent, name);
   }

   @Override
   public boolean removeStation(String stationId) {
      return SpaceStationManager.removeStation(stationId);
   }

   @Override
   public boolean renameStation(String stationId, String newName) {
      return SpaceStationManager.renameStation(stationId, newName);
   }

   @Override
   public Seq<SpaceStationInfo> getStationsByPlanet(Planet planet) {
      return SpaceStationManager.getStationsByPlanet(planet);
   }

   @Override
   public SpaceStation getStationById(String stationId) {
      return SpaceStationManager.getStationById(stationId);
   }

   @Override
   public SpaceStationInfo getStationInfoById(String stationId) {
      return SpaceStationManager.getStationInfoById(stationId);
   }

   @Override
   public int getNextStationNumber(Planet planet) {
      return SpaceStationManager.getNextStationNumber(planet);
   }

   @Override
   public boolean stationExists(String stationId) {
      return SpaceStationManager.stationExists(stationId);
   }

   @Override
   public Seq<SpaceStationInfo> getAllStations() {
      return SpaceStationManager.getAllStations();
   }

   @Override
   public int getStationCount() {
      return SpaceStationManager.getStationCount();
   }

   @Override
   public int getStationCountByPlanet(Planet planet) {
      return SpaceStationManager.getStationCountByPlanet(planet);
   }
}
