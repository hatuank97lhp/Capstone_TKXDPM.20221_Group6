package ebike.core.domain.repository;

import java.util.List;

import ebike.core.domain.model.DockingStationEnity;

public interface IDockingStationRepo {
    public List<DockingStationEnity> getListDockingStation();

    public DockingStationEnity getDockingStationById(Long id);

    public boolean save(DockingStationEnity dock);
}
