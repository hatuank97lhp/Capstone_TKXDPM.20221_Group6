package infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

import ebike.infrastructure.db.repository.CreditCardRepo;
import infrastructure.repository.common.DbMigration;

public class CreditCardRepoTest {
    @Test
    public void getById() {
        var url = "jdbc:sqlite:db/test.db";
        try (var conn = DriverManager.getConnection(url)) {
            DbMigration.runScript(conn);
            var creditCardRepo = new CreditCardRepo(conn);
            var id = 1L;
            var card = creditCardRepo.getById(id);

            assertEquals(card.getId(), id);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
