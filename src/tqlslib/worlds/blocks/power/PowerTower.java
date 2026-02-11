package tqlslib.worlds.blocks.power;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import arc.func.Boolf;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Rect;
import arc.struct.Seq;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.ai.BlockIndexer;
import mindustry.core.Renderer;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.PowerGraph;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import tqlslib.content.MxStats;

public class PowerTower extends BeamNode {
   public Color baseColor = Color.valueOf("d4e1ff");
   public int range = 8;
   public int linkRange = 5;

   public PowerTower(String name) {
      super(name);
   }

   public void drawPlace(int x, int y, int rotation, boolean valid) {
      for(int i = 0; i < 4; ++i) {
         int maxLen = this.range + this.size / 2;
         Building dest = null;
         Point2 dir = Geometry.d4[i];
         int dx = dir.x;
         int dy = dir.y;
         int offset = this.size / 2;

         for(int j = 1 + offset; j <= this.range + offset; ++j) {
            Building other = Vars.world.build(x + j * dir.x, y + j * dir.y);
            if (other != null && other.isInsulated()) {
               break;
            }

            if (other != null && other.team == Vars.player.team() && other.block instanceof PowerTower) {
               maxLen = j;
               dest = other;
               break;
            }
         }

         Drawf.dashLine(Pal.placing, (float)(x * 8) + (float)dx * ((float)(8 * this.size) / 2.0F + 2.0F), (float)(y * 8) + (float)dy * ((float)(8 * this.size) / 2.0F + 2.0F), (float)(x * 8 + dx * maxLen * 8), (float)(y * 8 + dy * maxLen * 8));
         if (dest != null) {
            Drawf.square(dest.x, dest.y, (float)(dest.block.size * 8) / 2.0F + 2.5F, 0.0F);
         }
      }

      x *= 8;
      y *= 8;
      x = (int)((float)x + this.offset);
      y = (int)((float)y + this.offset);
      Drawf.dashSquare(this.baseColor, (float)x, (float)y, (float)(this.linkRange * 8));
      Vars.indexer.eachBlock(Vars.player.team(), Tmp.r1.setCentered((float)x, (float)y, (float)(this.linkRange * 8)), (b) -> {
         return b instanceof mindustry.gen.Building && ((mindustry.gen.Building)b).power != null && !(b instanceof PowerTower.PowerTowerBuild);
      }, (t) -> {
         Drawf.selected(t, Tmp.c1.set(this.baseColor).a(Mathf.absin(4.0F, 1.0F)));
      });
   }

   public void setStats() {
      super.setStats();
      this.stats.remove(Stat.powerRange);
      this.stats.add(MxStats.linkRange, (float)this.range, StatUnit.blocks);
      this.stats.add(MxStats.supplyRange, (float)this.linkRange, StatUnit.blocks);
   }

   public class PowerTowerBuild extends BeamNodeBuild {
      public Seq targets = new Seq();

      public PowerTowerBuild() {
         super();
      }

      public void updateTile() {
         if (this.lastChange != Vars.world.tileChanges) {
            this.lastChange = Vars.world.tileChanges;
            this.updateLink();
            this.updateDirections();
         }

      }

      public void updateDirections() {
         for(int i = 0; i < 4; ++i) {
            Building prev = this.links[i];
            Point2 dir = Geometry.d4[i];
            this.links[i] = null;
            this.dests[i] = null;
            int offset = PowerTower.this.size / 2;

            for(int j = 1 + offset; j <= PowerTower.this.range + offset; ++j) {
               Building other = Vars.world.build(this.tile.x + j * dir.x, this.tile.y + j * dir.y);
               if (other != null && other.isInsulated()) {
                  break;
               }

               if (other != null && other.block.hasPower && other.block.connectedPower && other.team == this.team && other.block instanceof PowerTower) {
                  this.links[i] = other;
                  this.dests[i] = Vars.world.tile(this.tile.x + j * dir.x, this.tile.y + j * dir.y);
                  break;
               }
            }

            Building next = this.links[i];
            if (next != prev) {
               if (prev != null) {
                  prev.power.links.removeValue(this.pos());
                  this.power.links.removeValue(prev.pos());
                  PowerGraph newgraph = new PowerGraph();
                  newgraph.reflow(this);
                  if (prev.power.graph != newgraph) {
                     PowerGraph og = new PowerGraph();
                     og.reflow(prev);
                  }
               }

               if (next != null) {
                  this.power.links.addUnique(next.pos());
                  next.power.links.addUnique(this.pos());
                  this.power.graph.addGraph(next.power.graph);
               }
            }
         }

      }

      public void pickedUp() {
         Arrays.fill(this.links, (Object)null);
         Arrays.fill(this.dests, (Object)null);

         Building build;
         for(Iterator var1 = this.targets.iterator(); var1.hasNext(); this.targets.remove(build)) {
            build = (Building)var1.next();
            build.power.links.removeValue(this.pos());
            this.power.links.removeValue(build.pos());
            PowerGraph newgraph = new PowerGraph();
            newgraph.reflow(this);
            if (build.power.graph != newgraph) {
               PowerGraph og = new PowerGraph();
               og.reflow(build);
            }
         }

      }

      public void updateLink() {
         Seq newTargets = new Seq();
         BlockIndexer var10000 = Vars.indexer;
         Team var10001 = Vars.player.team();
         Rect var10002 = Tmp.r1.setCentered(this.x, this.y, (float)(PowerTower.this.linkRange * 8));
         Boolf var10003 = (b) -> {
            return b instanceof mindustry.gen.Building && ((mindustry.gen.Building)b).power != null && !(b instanceof PowerTower.PowerTowerBuild);
         };
         Objects.requireNonNull(newTargets);
         var10000.eachBlock(var10001, var10002, var10003, newTargets::add);
         Iterator var2 = newTargets.iterator();

         Building build;
         while(var2.hasNext()) {
            build = (Building)var2.next();
            if (!this.targets.contains(build)) {
               this.targets.addUnique(build);
               this.power.links.addUnique(build.pos());
               build.power.links.addUnique(this.pos());
               this.power.graph.addGraph(build.power.graph);
            }
         }

         var2 = this.targets.iterator();

         while(var2.hasNext()) {
            build = (Building)var2.next();
            if (!newTargets.contains(build)) {
               build.power.links.removeValue(this.pos());
               this.power.links.removeValue(build.pos());
               PowerGraph newgraph = new PowerGraph();
               newgraph.reflow(this);
               if (build.power.graph != newgraph) {
                  PowerGraph og = new PowerGraph();
                  og.reflow(build);
               }

               this.targets.remove(build);
            } else if (!this.power.links.contains(build.pos())) {
               this.power.links.addUnique(build.pos());
               build.power.links.addUnique(this.pos());
               this.power.graph.addGraph(build.power.graph);
            }
         }

      }

      public void drawSelect() {
         super.drawSelect();
         Drawf.dashSquare(PowerTower.this.baseColor, this.x, this.y, (float)(PowerTower.this.linkRange * 8));
      }

      public void draw() {
         super.draw();
         if (!Mathf.zero(Renderer.laserOpacity)) {
            Draw.z(70.0F);
            Draw.color(PowerTower.this.laserColor1, PowerTower.this.laserColor2, (1.0F - this.power.graph.getSatisfaction()) * 0.86F + Mathf.absin(3.0F, 0.1F));
            Draw.alpha(Renderer.laserOpacity);
            float w = PowerTower.this.laserWidth + Mathf.absin(PowerTower.this.pulseScl, PowerTower.this.pulseMag);

            for(int i = 0; i < 4; ++i) {
               if (this.dests[i] != null && this.links[i].wasVisible && this.links[i].block instanceof PowerTower) {
                  int dst = Math.max(Math.abs(this.dests[i].x - this.tile.x), Math.abs(this.dests[i].y - this.tile.y));
                  if (dst > 1 + PowerTower.this.size / 2) {
                     Point2 point = Geometry.d4[i];
                     float poff = 4.0F;
                     Drawf.laser(PowerTower.this.laser, PowerTower.this.laserEnd, this.x + poff * (float)PowerTower.this.size * (float)point.x, this.y + poff * (float)PowerTower.this.size * (float)point.y, this.dests[i].worldx() - poff * (float)point.x, this.dests[i].worldy() - poff * (float)point.y, w);
                  }
               }
            }

            Draw.reset();
         }
      }
   }
}