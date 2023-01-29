package ebike;

import java.sql.DriverManager;

import ebike.core.application.impl.BikeAppService;
import ebike.core.application.impl.DockingStationAppService;
import ebike.core.application.impl.UserAppService;
import ebike.core.domain.service.impl.BarcodeService;
import ebike.core.domain.service.impl.RentalBikeCostPolicyService;
import ebike.core.domain.service.impl.RentalBikeService;
import ebike.infrastructure.bank.BankService;
import ebike.infrastructure.db.migration.DbMigration;
import ebike.infrastructure.db.repository.BikeRepo;
import ebike.infrastructure.db.repository.CreditCardRepo;
import ebike.infrastructure.db.repository.DockingStationRepo;
import ebike.infrastructure.db.repository.PaymentRepo;
import ebike.infrastructure.db.repository.RentalTxRepo;
import ebike.infrastructure.db.repository.UserRepo;
import ebike.infrastructure.idgenerator.IDGeneratorService;
import ebike.view.ApplicationUI;

public class Startup {
    public static void main(String[] args) {
        var url = "jdbc:sqlite:app/db/test.db";
        try {
            var conn = DriverManager.getConnection(url);
            // init db
            DbMigration.runScript(conn, "app/db/initdb.sql");

            // repository
            var bikeRepo = new BikeRepo(conn);
            var creditCardRepo = new CreditCardRepo(conn);
            var dockingStationRepo = new DockingStationRepo(conn);
            var paymentRepo = new PaymentRepo(conn);
            var rentalTxRepo = new RentalTxRepo(conn);
            var userRepo = new UserRepo(conn);

            // id generator
            var idGeneratorService = new IDGeneratorService();

            // bank
            var bankService = new BankService(idGeneratorService);

            // domain service
            var barcodeService = new BarcodeService();
            var rentalBikeService = new RentalBikeService(idGeneratorService);
            var rentalBikeCostPolicy = new RentalBikeCostPolicyService();

            // application service
            var bikeAppService = new BikeAppService(bikeRepo, rentalTxRepo, creditCardRepo, userRepo, paymentRepo,
                    dockingStationRepo, barcodeService, bankService, rentalBikeService, rentalBikeCostPolicy);
            var dockingStationAppService = new DockingStationAppService(dockingStationRepo, bikeRepo);
            var userAppService = new UserAppService(creditCardRepo, userRepo, bankService);

            // ui
            new ApplicationUI(bikeAppService, dockingStationAppService, userAppService);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
