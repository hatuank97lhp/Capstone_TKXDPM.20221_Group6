package ebike.core.domain.repository;

import java.util.List;

import ebike.core.domain.model.DockingStation;

public interface IDockingStationRepo {
    List<DockingStation> getListDockingStation();
}
