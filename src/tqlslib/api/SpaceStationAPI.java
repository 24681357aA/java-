package tqlslib.api;

import arc.struct.Seq;
import mindustry.type.Planet;
import tqlslib.type.SpaceStation;
import tqlslib.type.SpaceStationInfo;

public interface SpaceStationAPI {
    SpaceStation createStation(Planet parent, String name);
    boolean removeStation(String stationId);
    boolean renameStation(String stationId, String newName);
    Seq<SpaceStationInfo> getStationsByPlanet(Planet planet);
    SpaceStation getStationById(String stationId);
    SpaceStationInfo getStationInfoById(String stationId);
    int getNextStationNumber(Planet planet);
    boolean stationExists(String stationId);
    Seq<SpaceStationInfo> getAllStations();
    int getStationCount();
    int getStationCountByPlanet(Planet planet);
}
