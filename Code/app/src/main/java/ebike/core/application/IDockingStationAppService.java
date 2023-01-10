package ebike.core.application;

import java.util.List;

import ebike.core.application.dto.output.DockStationDetailOutput;
import ebike.core.application.dto.output.DockStationPreviewOutput;

public interface IDockingStationAppService {
    public List<DockStationPreviewOutput> getListDockingStation();

    public DockStationDetailOutput getDockingStationDetailById(int id);
}
