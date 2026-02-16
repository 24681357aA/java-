package tqlslib.core;

import java.io.IOException;
import java.io.Writer;

import arc.ApplicationListener;
import arc.util.Log;
import mindustry.Vars;
import mindustry.mod.Mods.LoadedMod;
import mindustry.type.Planet;
import tqlslib.DTVars;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class SpaceStationIO implements ApplicationListener {

   public static void load() {
      try {
         SpaceStationIO io = new SpaceStationIO();
         io.read();
      } catch (Exception e) {
         Log.err("SpaceStationIO: Failed to load", e);
      }
   }

   public void read() throws IOException {
      try {
         Vars.content.setCurrentMod(Vars.mods.getMod(DTVars.modName));
         
         String content = DTVars.spaceStationFi.readString();
         if (content.isEmpty()) {
            Log.info("SpaceStationIO: No saved space stations found.");
            return;
         }

         String[] lines = content.split("\n");
         for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split("\\|");
            if (parts.length >= 4) {
               try {
                  String planetName = parts[0];
                  String stationId = parts[1];
                  String stationName = parts[2];
                  long createTime = Long.parseLong(parts[3]);
                  int stationNumber = parts.length > 4 ? Integer.parseInt(parts[4]) : 1;

                  Planet parent = Vars.content.planet(planetName);
                  if (parent != null) {
                     Log.info("SpaceStationIO: Loading space station " + stationName + " for planet " + planetName);
                     SpaceStation.create(parent, stationId, stationName, stationNumber);
                  }
               } catch (Exception e) {
                  Log.err("SpaceStationIO: Failed to parse line: " + line, e);
               }
            }
         }

         Vars.content.setCurrentMod((LoadedMod)null);
         Log.info("SpaceStationIO: Read complete. Loaded " + DTVars.spaceStationInfos.size + " space stations.");
      } catch (Exception e) {
         Log.err("SpaceStationIO: Failed to read space stations", e);
      }
   }

   public void exit() {
      try {
         Writer writer = DTVars.spaceStationFi.writer(false);
         
         for (SpaceStationInfo info : DTVars.spaceStationInfos) {
            writer.write(info.parent.name);
            writer.append('|');
            writer.write(info.id);
            writer.append('|');
            writer.write(info.name);
            writer.append('|');
            writer.write(String.valueOf(info.createTime));
            writer.append('|');
            writer.write(String.valueOf(info.stationNumber));
            writer.append('\n');
         }

         writer.flush();
         writer.close();
         Log.info("SpaceStationIO: Saved " + DTVars.spaceStationInfos.size + " space stations.");
      } catch (IOException e) {
         Log.err("SpaceStationIO: Failed to save space stations", e);
      }
   }
}
