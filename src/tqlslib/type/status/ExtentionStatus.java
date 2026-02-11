package tqlslib.type.status;

import arc.util.Log;
import arc.util.Time;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class ExtentionStatus extends StatusEffect {
   public int armor = 10;

   public ExtentionStatus(String name) {
      super(name);
   }

   public void update(Unit unit, float time) {
      super.update(unit, time);
      if (time - Time.delta <= 0.0F) {
         unit.armor -= (float)this.armor;
      }

      Log.info(unit.armor);
   }

   public void setStats() {
      if (this.armor > 0) {
         this.stats.add(Stat.armor, (float)this.armor, StatUnit.perSecond);
      }

      super.setStats();
   }

   public void applied(Unit unit, float time, boolean extend) {
      super.applied(unit, time, extend);
      if (!extend) {
         unit.armor += (float)this.armor;
      }

   }
}