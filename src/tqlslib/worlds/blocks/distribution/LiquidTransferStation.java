package tqlslib.worlds.blocks.distribution;

import arc.Events;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.util.Objects;
import mindustry.Vars;
import mindustry.game.EventType.BlockBuildEndEvent;
import mindustry.game.EventType.ContentInitEvent;
import mindustry.game.EventType.WorldLoadEvent;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.Block;

public class LiquidTransferStation extends Block {
   public static int[] liquidTransferStationPos = new int[5];
   public static Building[] liquidTransferStationBuilding = new Building[5];

   public LiquidTransferStation(String name) {
      super(name);
      this.update = true;
      this.solid = true;
      this.health = 1000;
      this.hasLiquids = true;
      this.configurable = false;
      this.liquidCapacity = 1000.0F;
      this.noUpdateDisabled = true;
      this.unloadable = false;
      this.rebuildable = true;

      Events.on(WorldLoadEvent.class, (e) -> {
         int height = Vars.world.height();
         int width = Vars.world.width();

         for(int i = 0; i < liquidTransferStationPos.length; ++i) {
            liquidTransferStationPos[i] = 0;
            liquidTransferStationBuilding[i] = null;
         }

         for(int i = 0; i < width; ++i) {
            for(int ii = 0; ii < height; ++ii) {
               Building building = Vars.world.build(i, ii);
               if (building instanceof LiquidTransferStationBuild) {
                  liquidTransferStationPos[building.team.id] = building.pos();
                  liquidTransferStationBuilding[building.team.id] = building;
               }
            }
         }

      });

      Events.on(BlockBuildEndEvent.class, (e) -> {
         if (e.breaking && e.tile.pos() == liquidTransferStationPos[e.team.id]) {
            liquidTransferStationPos[e.team.id] = 0;
            liquidTransferStationBuilding[e.team.id] = null;
         }

      });

      Events.on(ContentInitEvent.class, (e) -> {
         // 初始化事件处理
      });
   }

   public void setBars() {
      this.addBar("health", (entity) -> {
         Color var10003 = Pal.health;
         Objects.requireNonNull(entity);
         return (new Bar("stat.health", var10003, entity::healthf)).blink(Color.white);
      });
   }

   public class LiquidTransferStationBuild extends Building {
      public void updateTile() {
         if (this.team.core() != null) {
            Building core = this.team.core();
            if (core.interactable(this.team) && core.block.hasLiquids) {
               for(Liquid liquid : Vars.content.liquids()) {
                  if (liquid != null && core.liquids.get(liquid) > 0.0F) {
                     float amount = Mathf.clamp(core.liquids.get(liquid), 0.0F, 10.0F);
                     if (this.liquids.get(liquid) < this.block.liquidCapacity) {
                        core.liquids.remove(liquid, amount);
                        this.liquids.add(liquid, amount);
                     }
                  }
               }
            }
         }

      }

      public void draw() {
         super.draw();
      }

      public void write(Writes write) {
         super.write(write);
      }

      public void read(Reads read, byte revision) {
         super.read(read, revision);
      }

      public boolean acceptItem(Building source, Item item) {
         return false;
      }

      public boolean acceptLiquid(Building source, Liquid liquid) {
         return false;
      }
   }
}