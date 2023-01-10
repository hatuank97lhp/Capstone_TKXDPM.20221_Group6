package ebike.core.application.dto.output;

import java.util.List;

public class DockStationDetailOutput {
    public Integer id;
    public String name;
    public String address;
    public Integer area;
    public Integer numAvailableBike;
    public Integer numAvailableDock;

    public List<BikePreviewOutput> availableBikes;
}
