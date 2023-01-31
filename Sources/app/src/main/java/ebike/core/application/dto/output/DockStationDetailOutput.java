package ebike.core.application.dto.output;

import java.util.List;

public class DockStationDetailOutput {
    public Long id;
    public String name;
    public String address;
    public Integer area;
    public Integer numAvailableBike;
    public Integer numAvailableDock;

    public List<BikePreviewOutput> availableBikes;
}
