package tqlslib.ui;

import arc.scene.ui.TextField;
import arc.util.Log;
import mindustry.type.Planet;
import mindustry.ui.dialogs.BaseDialog;
import tqlslib.core.SpaceStationManager;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public class SpaceStationDialogs {

   public static void showManagerDialog() {
      try {
         Log.info("SpaceStationDialogs: Showing manager dialog");
         
         BaseDialog dialog = new BaseDialog("Space Station Manager");
         
         dialog.cont.add("Space Station Manager").row();
         dialog.cont.add("Total stations: " + SpaceStationManager.getStationCount()).row();
         
         for (SpaceStationInfo info : SpaceStationManager.getAllStations()) {
            dialog.cont.add(info.name + " (" + info.parent.name + " #"+ info.stationNumber + ")").row();
         }
         
         dialog.cont.button("Close", dialog::hide).size(120, 50);
         dialog.show();
         
         Log.info("SpaceStationDialogs: Manager dialog shown");
      } catch (Exception e) {
         Log.err("SpaceStationDialogs: Error showing manager dialog", e);
      }
   }

   public static void showSelectDialog(Planet planet, StationSelectCallback callback) {
      try {
         if (planet == null) {
            Log.err("SpaceStationDialogs: Planet cannot be null");
            return;
         }
         
         Log.info("SpaceStationDialogs: Showing select dialog for planet " + planet.name);
         
         BaseDialog dialog = new BaseDialog("Select Space Station");
         
         dialog.cont.add("Select a station for " + planet.localizedName).row();
         
         var stations = SpaceStationManager.getStationsByPlanet(planet);
         
         if (stations.isEmpty()) {
            dialog.cont.add("No stations found for this planet").row();
         } else {
            for (SpaceStationInfo info : stations) {
               dialog.cont.button(info.name + " (#" + info.stationNumber + ")", () -> {
                  SpaceStation station = SpaceStationManager.getStationById(info.id);
                  if (station != null && callback != null) {
                     callback.onSelect(station);
                  }
                  dialog.hide();
               }).size(200, 50).row();
            }
         }
         
         dialog.cont.button("Create New", () -> {
            dialog.hide();
            showCreateDialog(planet, station -> {
               if (callback != null) {
                  callback.onSelect(station);
               }
            });
         }).size(120, 50).row();
         
         dialog.cont.button("Cancel", dialog::hide).size(120, 50);
         dialog.show();
         
         Log.info("SpaceStationDialogs: Select dialog shown");
      } catch (Exception e) {
         Log.err("SpaceStationDialogs: Error showing select dialog", e);
      }
   }

   public static void showCreateDialog(Planet planet, StationCreateCallback callback) {
      try {
         if (planet == null) {
            Log.err("SpaceStationDialogs: Planet cannot be null");
            return;
         }
         
         Log.info("SpaceStationDialogs: Showing create dialog for planet " + planet.name);
         
         BaseDialog dialog = new BaseDialog("Create Space Station");
         
         dialog.cont.add("Create a new station for " + planet.localizedName).row();
         dialog.cont.add("Next station number: " + SpaceStationManager.getNextStationNumber(planet)).row();
         
         TextField field = dialog.cont.field("", text -> {}).size(300, 50).get();
         field.setMessageText("Station Name");
         
         dialog.cont.button("Create", () -> {
            String name = field.getText();
            if (name == null || name.trim().isEmpty()) {
               name = planet.localizedName + " Station #" + SpaceStationManager.getNextStationNumber(planet);
            }
            SpaceStation station = SpaceStationManager.createStation(planet, name);
            if (station != null && callback != null) {
               callback.onCreate(station);
            }
            dialog.hide();
         }).size(120, 50).row();
         
         dialog.cont.button("Cancel", dialog::hide).size(120, 50);
         dialog.show();
         
         Log.info("SpaceStationDialogs: Create dialog shown");
      } catch (Exception e) {
         Log.err("SpaceStationDialogs: Error showing create dialog", e);
      }
   }

   public static void showDeleteConfirmDialog(SpaceStation station, Runnable onConfirm) {
      try {
         if (station == null) {
            Log.err("SpaceStationDialogs: Station cannot be null");
            return;
         }
         
         Log.info("SpaceStationDialogs: Showing delete confirm dialog for station " + station.localizedName);
         
         BaseDialog dialog = new BaseDialog("Confirm Delete");
         
         dialog.cont.add("Are you sure you want to delete").row();
         dialog.cont.add("'" + station.localizedName + "'?").row();
         dialog.cont.add("This action cannot be undone!").row();
         
         dialog.cont.button("Delete", () -> {
            if (onConfirm != null) {
               onConfirm.run();
            }
            SpaceStationManager.removeStation(station.stationId);
            dialog.hide();
         }).size(120, 50).row();
         
         dialog.cont.button("Cancel", dialog::hide).size(120, 50);
         dialog.show();
         
         Log.info("SpaceStationDialogs: Delete confirm dialog shown");
      } catch (Exception e) {
         Log.err("SpaceStationDialogs: Error showing delete confirm dialog", e);
      }
   }

   public static void showRenameDialog(SpaceStation station, NameChangeCallback onRename) {
      try {
         if (station == null) {
            Log.err("SpaceStationDialogs: Station cannot be null");
            return;
         }
         
         Log.info("SpaceStationDialogs: Showing rename dialog for station " + station.localizedName);
         
         BaseDialog dialog = new BaseDialog("Rename Station");
         
         dialog.cont.add("Rename '" + station.localizedName + "'").row();
         
         TextField field = dialog.cont.field(station.localizedName, text -> {}).size(300, 50).get();
         field.setMessageText("New Name");
         
         dialog.cont.button("Rename", () -> {
            String newName = field.getText();
            if (newName != null && !newName.trim().isEmpty()) {
               SpaceStationManager.renameStation(station.stationId, newName);
               if (onRename != null) {
                  onRename.onRename(newName);
               }
            }
            dialog.hide();
         }).size(120, 50).row();
         
         dialog.cont.button("Cancel", dialog::hide).size(120, 50);
         dialog.show();
         
         Log.info("SpaceStationDialogs: Rename dialog shown");
      } catch (Exception e) {
         Log.err("SpaceStationDialogs: Error showing rename dialog", e);
      }
   }

   public interface StationSelectCallback {
      void onSelect(SpaceStation station);
   }

   public interface StationCreateCallback {
      void onCreate(SpaceStation station);
   }

   public interface NameChangeCallback {
      void onRename(String newName);
   }
}
