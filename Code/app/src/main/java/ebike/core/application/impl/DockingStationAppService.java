package ebike.core.application.impl;

import java.util.List;
import java.util.stream.Collectors;

import ebike.core.application.ApplicationService;
import ebike.core.application.IDockingStationAppService;
import ebike.core.application.dto.output.DockStationPreviewOutput;
import ebike.core.domain.repository.IDockingStationRepo;
import ebike.core.domain.repository.IRentalBikeRepo;

public class DockingStationAppService implements ApplicationService, IDockingStationAppService {

    private IDockingStationRepo dockingStationRepo;
    private IRentalBikeRepo rentalBikeRepo;

    public DockingStationAppService(IDockingStationRepo dockingStationRepo, IRentalBikeRepo rentalBikeRepo) {
        this.dockingStationRepo = dockingStationRepo;
        this.rentalBikeRepo = rentalBikeRepo;
    }

    @Override
    public List<DockStationPreviewOutput> getListDockingStation() {
        var docks = dockingStationRepo.getListDockingStation();

        return docks.stream().map(d -> {
            var o = new DockStationPreviewOutput();
            o.id = d.getId();
            o.address = d.getAddress();
            o.area = d.getArea();
            o.name = d.getName();
            o.numAvailableBike = rentalBikeRepo.countNumAvailableBikeInDock(o.id);
            o.numAvailableDock = d.getDockCapacity() - o.numAvailableBike;
            return o;
        }).collect(Collectors.toList());
    }

}
