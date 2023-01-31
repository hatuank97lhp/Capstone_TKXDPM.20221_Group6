package infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

import ebike.infrastructure.db.repository.DockingStationRepo;
import infrastructure.repository.common.DbMigration;

public class DockingStationRepoTest {
    @Test
    public void getListDockingStation() {
        var url = "jdbc:sqlite:db/test.db";
        try (var conn = DriverManager.getConnection(url)) {
            DbMigration.runScript(conn);
            var dockingStationRepo = new DockingStationRepo(conn);
            var stations = dockingStationRepo.getListDockingStation();
            assertTrue(stations.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
