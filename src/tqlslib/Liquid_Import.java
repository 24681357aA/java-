package tqlslib;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import java.util.Objects;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.Block;

public class Liquid_Import extends Block {
   public Liquid_Import(String name) {
      super(name);
      this.update = true;
      this.solid = true;
      this.health = 500;
      this.hasLiquids = true;
      this.configurable = true;
      this.saveConfig = false;
      this.liquidCapacity = 50.0F;
      this.noUpdateDisabled = true;
      this.unloadable = false;
   }

   public void setBars() {
      this.addBar("health", (entity) -> {
         Color var10003 = Pal.health;
         Objects.requireNonNull(entity);
         return (new Bar("stat.health", var10003, entity::healthf)).blink(Color.white);
      });
   }

   public class Liquid_ImportBuild extends Building {
      public Liquid selectedLiquid = null;
      Building core_X;

      public void updateTile() {
         if (this.selectedLiquid != null && this.team.core() != null) {
            this.core_X = this.team.core();
            if (this.core_X.interactable(this.team) && this.core_X.block.hasLiquids && this.liquids.get(this.selectedLiquid) >= 1.0F) {
               if (this.core_X.liquids.get(this.selectedLiquid) < this.core_X.block.liquidCapacity) {
                  this.liquids.remove(this.selectedLiquid, 1.0F);
                  this.core_X.liquids.add(this.selectedLiquid, 1.0F);
               }
            }
         }

      }

      public void draw() {
         super.draw();
         if (this.selectedLiquid != null) {
            Draw.color(this.selectedLiquid.color);
            Draw.rect("coreunloader-mod-Liquid-Import-center", this.x, this.y);
            Draw.color();
         }
      }

      public void buildConfiguration(Table table) {
         table.add("Select Liquid: " + (selectedLiquid != null ? selectedLiquid.name : "None")).row();
         table.button("Clear Selection", () -> {
            selectedLiquid = null;
         }).size(200, 50);
      }

      public boolean onConfigureBuildTapped(Building other) {
         if (this == other) {
            this.deselect();
            selectedLiquid = null;
            return false;
         } else {
            return true;
         }
      }

      public Liquid config() {
         return this.selectedLiquid;
      }

      public byte version() {
         return 1;
      }

      public void write(Writes write) {
         super.write(write);
         write.s(this.selectedLiquid == null ? -1 : this.selectedLiquid.id);
      }

      public void read(Reads read, byte revision) {
         super.read(read, revision);
         int id = revision == 1 ? read.s() : read.b();
         this.selectedLiquid = id == -1 ? null : (Liquid)Vars.content.liquids().get(id);
      }

      public boolean acceptItem(Building source, Item item) {
         return false;
      }

      public boolean acceptLiquid(Building source, Liquid liquid) {
         return this.selectedLiquid == null || this.selectedLiquid == liquid;
      }
   }
}