package ebike.infrastructure.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ebike.core.domain.model.CreditCardEntity;
import ebike.core.domain.repository.ICreditCardRepo;
import ebike.core.domain.repository.Repository;

public class CreditCardRepo implements Repository, ICreditCardRepo {
    private Connection con;

    public CreditCardRepo(Connection con) {
        this.con = con;
    }

    @Override
    public CreditCardEntity getById(Long id) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery(String.format("select * from creditCards where id = %s", id));

            if (result.next()) {
                return mappingResultSetToCreditCardEntity(result);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    private CreditCardEntity mappingResultSetToCreditCardEntity(ResultSet result) throws SQLException {
        return new CreditCardEntity(
                result.getLong("id"),
                result.getString("cardHolderName"),
                result.getString("cardNumber"),
                result.getString("bank"),
                result.getString("expirationDate"),
                result.getString("code"));

    }

}
