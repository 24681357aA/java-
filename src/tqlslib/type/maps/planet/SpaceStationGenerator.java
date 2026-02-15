package tqlslib.type.maps.planet;

import arc.math.geom.Vec2;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.graphics.g3d.PlanetParams;
import mindustry.maps.generators.BlankPlanetGenerator;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;

public class SpaceStationGenerator extends BlankPlanetGenerator {
   public Block core;

   public SpaceStationGenerator() {
      this.core = Blocks.coreShard;
   }

   public void generate() {
      int sx = this.width / 2;
      int sy = this.height / 2;
      Floor background = Blocks.empty.asFloor();
      Floor ground = Blocks.metalFloor.asFloor();
      
      this.tiles.eachTile(t -> t.setFloor(background));
      
      int areaSize = this.core.size + 4;
      for (int x = sx - areaSize; x <= sx + areaSize; x++) {
         for (int y = sy - areaSize; y <= sy + areaSize; y++) {
            var tile = this.tiles.get(x, y);
            if (tile != null) {
               tile.setFloor(ground);
            }
         }
      }
      
      var coreTile = Vars.world.tile(sx + this.core.size / 2, sy + this.core.size / 2);
      if (coreTile != null) {
         coreTile.setBlock(this.core, Team.sharded);
      }
      
      Vars.state.rules.planetBackground = new PlanetParams() {{
         if (sector != null && sector.planet != null && sector.planet.parent != null) {
            this.planet = sector.planet.parent;
         }
         this.camPos.setZero();
         if (this.planet != null) {
            this.planet.addParentOffset(this.camPos).nor();
         }
         this.zoom = 1.0F;
      }};
      
      Vars.state.rules.dragMultiplier = 0.7F;
      Vars.state.rules.borderDarkness = false;
      Vars.state.rules.waves = false;
   }

   public int getSectorSize(Sector sector) {
      return 600;
   }
}