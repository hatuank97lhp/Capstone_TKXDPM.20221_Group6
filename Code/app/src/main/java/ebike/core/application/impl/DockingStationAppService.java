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

        o.numAvailableBike = o.availableBikes.size();
        o.numAvailableDock = dock.getDockCapacity() - o.availableBikes.size();

        return o;
    }

}
