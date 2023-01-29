package ebike.core.domain.service.impl;

import ebike.core.domain.service.DomainService;
import ebike.core.domain.service.IBarcodeService;

public class BarcodeService implements DomainService, IBarcodeService {

    @Override
    public Long translateBarcodeToID(String barcode) {
        try {
            return Long.parseLong(barcode);
        } catch (Exception ex) {
            return null;
        }
    }

}
