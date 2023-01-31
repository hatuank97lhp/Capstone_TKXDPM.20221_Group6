package ebike.core.application.dto.output;

import ebike.core.domain.model.def.BikeType;
import ebike.core.domain.model.def.RentalBikeStatus;

public class BikeDetailOutput {
    public Long id;
    public String licensePlates;
    public Integer currentBattery;
    public BikeType type;
    public Long depositCost;
    public RentalBikeStatus status;
    public CurrentRentalTxOutput currentRentalTxOutput;
}