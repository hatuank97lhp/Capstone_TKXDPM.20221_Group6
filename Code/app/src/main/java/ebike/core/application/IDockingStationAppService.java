package ebike.core.application;

import java.util.List;

import ebike.core.application.dto.output.DockStationDetailOutput;
import ebike.core.application.dto.output.DockStationPreviewOutput;

public interface IDockingStationAppService {
    public List<DockStationPreviewOutput> getListDockingStation();

    public DockStationPreviewOutput getDockingStationById(Long id);

    public DockStationDetailOutput getDockingStationDetailById(Long id);
}
