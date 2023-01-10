package ebike.core.domain.repository;

import java.util.List;

import ebike.core.domain.model.DockingStationEnity;

public interface IDockingStationRepo {
    List<DockingStationEnity> getListDockingStation();

    DockingStationEnity getDockingStationById(int id);
}
