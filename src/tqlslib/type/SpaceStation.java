package tqlslib.type;

import arc.util.Log;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.PlanetGrid.Ptile;
import mindustry.type.Planet;
import mindustry.type.Sector;
import tqlslib.DTVars;
import tqlslib.type.maps.planet.SpaceStationGenerator;

public class SpaceStation extends Planet {
   public String stationId;
   public int stationNumber;

   public SpaceStation(String name, Planet parent, String stationId, int stationNumber) {
      super(name, parent, 0.05F);
      this.stationId = stationId;
      this.stationNumber = stationNumber;
      this.generator = new SpaceStationGenerator();
      this.mesh = new HexMesh(this, 0);
      this.sectors.add(new Sector(this, Ptile.empty));
      this.hasAtmosphere = false;
      this.updateLighting = false;
      this.drawOrbit = true;
      this.accessible = true;
      this.clipRadius = 2.0F;
      this.orbitRadius = parent.radius + 1.0F + (stationNumber * 0.3F);
      this.alwaysUnlocked = true;
      this.allowWaves = false;
      this.icon = "commandRally";
   }

   public static SpaceStation create(Planet parent, String stationId, String stationName, int stationNumber) {
      try {
         Log.info("SpaceStation: Creating space station " + stationName + " for planet " + parent.name);
         
         SpaceStation spaceStation = new SpaceStation(stationId, parent, stationId, stationNumber);
         spaceStation.localizedName = stationName;
         
         SpaceStationInfo info = new SpaceStationInfo(stationId, parent, stationName, System.currentTimeMillis(), stationNumber);
         DTVars.spaceStationInfos.add(info);
         DTVars.spaceStationMap.put(stationId, spaceStation);
         
         Sector sector = spaceStation.getSector(Ptile.empty);
         sector.loadInfo();
         sector.info.wasCaptured = true;
         sector.info.spawnPosition = 0;
         
         Log.info("SpaceStation: Created successfully with ID " + stationId);
         return spaceStation;
      } catch (Exception e) {
         Log.err("SpaceStation: Failed to create", e);
         return null;
      }
   }

   public static SpaceStation create(Planet parent, String stationName) {
      String stationId = DTVars.generateStationId(parent);
      int stationNumber = DTVars.getNextStationNumber(parent);
      return create(parent, stationId, stationName, stationNumber);
   }

   public static void remove(String stationId) {
      try {
         SpaceStation station = DTVars.spaceStationMap.get(stationId);
         if (station != null) {
            Log.info("SpaceStation: Removing station " + stationId);
            
            DTVars.spaceStationMap.remove(stationId);
            DTVars.spaceStationInfos.remove(info -> info.id.equals(stationId));
            
            station.accessible = false;
            station.visible = false;
            station.sectors.clear();
            
            Log.info("SpaceStation: Removed successfully");
         }
      } catch (Exception e) {
         Log.err("SpaceStation: Failed to remove", e);
      }
   }
}
