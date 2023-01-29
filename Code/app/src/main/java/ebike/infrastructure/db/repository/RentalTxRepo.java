package ebike.infrastructure.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import ebike.core.domain.model.RentalTxEntity;
import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.core.domain.repository.IRentalTxRepo;
import ebike.core.domain.repository.Repository;

public class RentalTxRepo implements Repository, IRentalTxRepo {
    private Connection con;

    public RentalTxRepo(Connection con) {
        this.con = con;
    }

    @Override
    public RentalTxEntity getById(Long txId) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery(String.format("select * from rentalTxs where id = %s", txId));

            if (result.next()) {
                return mappingResultSetToRentalTxEntity(result);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean save(RentalTxEntity tx) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeUpdate(String.format(
                    "insert into rentalTxs (id, startAt, endAt, fromDockId, toDockId, rentPolicy, bikeId, userId, paymentTxRentId, paymentTxReturnId) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) on conflict(id) do update set startAt=excluded.startAt, endAt=excluded.endAt, fromDockId=excluded.fromDockId, rentPolicy=excluded.rentPolicy, bikeId=excluded.bikeId, userId=excluded.userId, paymentTxRentId=excluded.paymentTxRentId, paymentTxReturnId=excluded.paymentTxReturnId",
                    tx.getId(), tx.getStartAtInSecond(), tx.getEndAtInSecond(), tx.getFromDockId(),
                    tx.getToDockId(),
                    tx.getRentPolicy().ordinal(), tx.getBikeId(), tx.getUserId(), tx.getPaymentTxRentId(),
                    tx.getPaymentTxReturnId()));
            return result > 0;

        } catch (Exception ex) {
            return false;
        }
    }

    private RentalTxEntity mappingResultSetToRentalTxEntity(ResultSet result) throws SQLException {
        return new RentalTxEntity(
                result.getLong("id"),
                Instant.ofEpochSecond(result.getLong("startAt")),
                Instant.ofEpochSecond(result.getLong("endAt")),
                result.getLong("fromDockId") != 0 ? result.getLong("fromDockId") : null,
                result.getLong("toDockId") != 0 ? result.getLong("toDockId") : null,
                RentalBikePolicy.values()[result.getInt("rentPolicy")],
                result.getLong("bikeId"),
                result.getLong("userId"),
                result.getLong("paymentTxRentId") != 0 ? result.getLong("paymentTxRentId") : null,
                result.getLong("paymentTxReturnId") != 0 ? result.getLong("paymentTxReturnId") : null);
    }
}
