package infrastructure.repository;

import org.junit.jupiter.api.Test;

import ebike.infrastructure.db.repository.BikeRepo;
import infrastructure.repository.common.DbMigration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.DriverManager;

public class BikeRepoTest {
    @Test
    public void getById() {
        var url = "jdbc:sqlite:db/test.db";
        try (var conn = DriverManager.getConnection(url)) {
            DbMigration.runScript(conn);
            var bikeRepo = new BikeRepo(conn);
            var id = 1L;
            var bike = bikeRepo.getById(id);

            assertEquals(bike.getId(), id);
        } catch (Exception e) {
            assertTrue(false);
        }

    }
}
