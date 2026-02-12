package tqlslib.graphics;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.util.Log;
import mindustry.gen.Building;
import mindustry.graphics.Pal;

public class TQDrawf {

   public static void drawBeamNodeConnection(Building from, Building to) {
      if (from == null || to == null || !from.isAdded() || !to.isAdded()) return;
      
      Draw.color(Pal.power);
      Lines.line(from.x, from.y, to.x, to.y);
      Draw.color();
   }

   public static void drawSmartPowerConnection(Building from, Building to) {
      if (from == null || to == null || !from.isAdded() || !to.isAdded()) return;
      
      Draw.color(Pal.power);
      Lines.line(from.x, from.y, to.x, to.y);
      Draw.color();
   }

   public static void drawPayloadRailConnection(Building from, Building to) {
      if (from == null || to == null || !from.isAdded() || !to.isAdded()) return;
      
      Draw.color(Pal.accent);
      Lines.line(from.x, from.y, to.x, to.y);
      Draw.color();
   }

   public static void drawDirection(int x, int y, int direction) {
      Point2 dir = Geometry.d4(direction);
      float px = x * 8f + 4f + dir.x * 4f;
      float py = y * 8f + 4f + dir.y * 4f;
      
      Draw.color(Pal.accent);
      Lines.line(x * 8f + 4f, y * 8f + 4f, px, py);
      Draw.color();
   }
   
   public static void init() {
      Log.info("Tranqol: TQDrawf initialized.");
   }
}