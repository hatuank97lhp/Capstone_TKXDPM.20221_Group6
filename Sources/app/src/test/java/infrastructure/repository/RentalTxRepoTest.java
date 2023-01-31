package infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

import ebike.infrastructure.db.repository.RentalTxRepo;
import infrastructure.repository.common.DbMigration;

public class RentalTxRepoTest {
    @Test
    public void getById() {
        var url = "jdbc:sqlite:db/test.db";
        try (var conn = DriverManager.getConnection(url)) {
            DbMigration.runScript(conn);
            var rentalTxRepo = new RentalTxRepo(conn);
            var id = 1L;
            var rentalTx = rentalTxRepo.getById(id);

            assertEquals(rentalTx.getId(), id);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
