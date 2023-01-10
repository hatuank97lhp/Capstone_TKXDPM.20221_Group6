package ebike.core.application.impl;

import java.util.List;
import java.util.stream.Collectors;

import ebike.core.application.ApplicationService;
import ebike.core.application.IDockingStationAppService;
import ebike.core.application.dto.output.DockStationPreviewOutput;
import ebike.core.domain.repository.IDockingStationRepo;
import ebike.core.domain.repository.IRentalBikeRepo;
import ebike.core.application.dto.output.BikePreviewOutput;
import ebike.core.application.dto.output.DockStationDetailOutput;

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

        return docks.stream().map(dock -> {
            var o = new DockStationPreviewOutput();
            o.id = dock.getId();
            o.address = dock.getAddress();
            o.area = dock.getArea();
            o.name = dock.getName();
            o.numAvailableBike = dock.getNumAvailableBike();
            o.numAvailableDock = dock.getNumAvailableDock();
            return o;
        }).collect(Collectors.toList());
    }

    @Override
    public DockStationDetailOutput getDockingStationDetailById(int id) {
        var dock = dockingStationRepo.getDockingStationById(id);

        if (dock == null) {
            return null;
        }

        var o = new DockStationDetailOutput();
        o.id = dock.getId();
        o.address = dock.getAddress();
        o.area = dock.getArea();
        o.name = dock.getName();
        o.numAvailableBike = dock.getNumAvailableBike();
        o.numAvailableDock = dock.getNumAvailableDock();

        o.availableBikes = rentalBikeRepo.getAvailableBikeInDock(id).stream().map(bike -> {
            var b = new BikePreviewOutput();
            b.id = bike.getId();
            b.licensePlates = bike.getLicensePlates();
            b.currentBattery = bike.getCurrentBattery();
            b.depositCost = bike.getDepositCost();
            b.status = bike.getStatus();
            b.type = bike.getType();
            return b;
        }).collect(Collectors.toList());

        return o;
    }

}
