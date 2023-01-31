package ebike.infrastructure.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ebike.core.domain.model.UserEntity;
import ebike.core.domain.repository.IUserRepo;
import ebike.core.domain.repository.Repository;

public class UserRepo implements Repository, IUserRepo {
    private Connection con;

    public UserRepo(Connection con) {
        this.con = con;
    }

    @Override
    public UserEntity getById(Long id) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery(String.format("select * from users where id = %s", id));

            if (result.next()) {
                return mappingResultSetToUserEntity(result);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    private UserEntity mappingResultSetToUserEntity(ResultSet result) throws SQLException {
        return new UserEntity(
                result.getLong("id"),
                result.getString("fullname"),
                result.getLong("creditCardId") != 0 ? result.getLong("creditCardId") : null);
    }

}
