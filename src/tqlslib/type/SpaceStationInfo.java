package tqlslib.type;

import mindustry.type.Planet;

public class SpaceStationInfo {
   public String id;
   public Planet parent;
   public String name;
   public long createTime;
   public int stationNumber;

   public SpaceStationInfo(String id, Planet parent, String name, long createTime, int stationNumber) {
      this.id = id;
      this.parent = parent;
      this.name = name;
      this.createTime = createTime;
      this.stationNumber = stationNumber;
   }

   public SpaceStationInfo(String id, Planet parent, String name) {
      this(id, parent, name, System.currentTimeMillis(), 1);
   }
}