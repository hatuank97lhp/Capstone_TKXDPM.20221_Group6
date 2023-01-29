package infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

import ebike.infrastructure.db.repository.UserRepo;
import infrastructure.repository.common.DbMigration;

public class UserRepoTest {
    @Test
    public void getById() {
        var url = "jdbc:sqlite:db/test.db";
        try (var conn = DriverManager.getConnection(url)) {
            DbMigration.runScript(conn);
            var userRepo = new UserRepo(conn);
            var id = 1L;
            var user = userRepo.getById(id);

            assertEquals(user.getId(), id);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
