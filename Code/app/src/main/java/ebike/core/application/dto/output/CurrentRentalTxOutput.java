package ebike.core.application.dto.output;

import java.util.Date;

import ebike.core.domain.model.def.RentalBikePolicy;

public class CurrentRentalTxOutput {
    public Integer id;
    public Date startAt;
    public Double currentCost;
    public Integer fromDock;
    public RentalBikePolicy rentPolicy;
}
