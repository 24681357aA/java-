package tqlslib.worlds.blocks.distribution;

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

public class CoreUnloader_Import extends Block {
   public CoreUnloader_Import(String name) {
      super(name);
      this.update = true;
      this.solid = true;
      this.health = 500;
      this.hasItems = true;
      this.configurable = true;
      this.saveConfig = false;
      this.itemCapacity = 50;
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

   public class CoreUnloader_ImportBuild extends Building {
      public Item selectedItem = null;
      Building core_X;

      public void updateTile() {
         if (this.selectedItem != null && this.team.core() != null) {
            this.core_X = this.team.core();
            if (this.core_X.interactable(this.team) && this.core_X.block.hasItems && this.items.get(this.selectedItem) >= 1) {
               if (this.core_X.items.get(this.selectedItem) < this.core_X.block.itemCapacity) {
                  this.items.remove(this.selectedItem, 1);
                  this.core_X.items.add(this.selectedItem, 1);
               }
            }
         }

      }

      public void draw() {
         super.draw();
         Draw.color(this.selectedItem == null ? Color.clear : this.selectedItem.color);
         Draw.rect("coreunloader-mod-coreLoaderInput-XX-center", this.x, this.y);
         Draw.color();
      }

      public void buildConfiguration(Table table) {
         table.add("Select Item: " + (selectedItem != null ? selectedItem.name : "None")).row();
         table.button("Clear Selection", () -> {
            selectedItem = null;
         }).size(200, 50);
      }

      public boolean onConfigureBuildTapped(Building other) {
         if (this == other) {
            this.deselect();
            selectedItem = null;
            return false;
         } else {
            return true;
         }
      }

      public Item config() {
         return this.selectedItem;
      }

      public byte version() {
         return 1;
      }

      public void write(Writes write) {
         super.write(write);
         write.s(this.selectedItem == null ? -1 : this.selectedItem.id);
      }

      public void read(Reads read, byte revision) {
         super.read(read, revision);
         int id = revision == 1 ? read.s() : read.b();
         this.selectedItem = id == -1 ? null : (Item)Vars.content.items().get(id);
      }

      public boolean acceptItem(Building source, Item item) {
         return this.selectedItem == null || this.selectedItem == item;
      }

      public boolean acceptLiquid(Building source, Liquid liquid) {
         return false;
      }
   }
}