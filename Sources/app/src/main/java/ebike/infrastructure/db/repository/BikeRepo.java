package ebike.infrastructure.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ebike.core.domain.model.BikeEnity;
import ebike.core.domain.model.def.BikeType;
import ebike.core.domain.repository.IBikeRepo;
import ebike.core.domain.repository.Repository;

public class BikeRepo implements Repository, IBikeRepo {

    private Connection con;

    public BikeRepo(Connection con) {
        this.con = con;
    }

    @Override
    public BikeEnity getById(Long id) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery(String.format("select * from bikes where id = %s", id));

            if (result.next()) {
                return mappingResultSetToBikeEntity(result);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public BikeEnity getCurrentRentalBikeOfUser(Long userId) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery(
                    String.format(
                            "select bikes.* from rentalTxs join bikes on bikes.id = rentalTxs.bikeId where endAt is null and userId = %s",
                            userId));

            if (result.next()) {
                return mappingResultSetToBikeEntity(result);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<BikeEnity> getAvailableBikeInDock(Long dockId) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery(
                    String.format(
                            "select * from bikes where currentDockId = %s",
                            dockId));
            var bikes = new ArrayList<BikeEnity>();

            while (result.next()) {
                bikes.add(mappingResultSetToBikeEntity(result));
            }

            return bikes;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean save(BikeEnity bike) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeUpdate(String.format(
                    "insert into bikes (id, type, price, currentBattery, currentDockId, currentRentalTxId, licensePlates) values (%s, %s, %s, %s, %s, %s, '%s') on conflict(id) do update set type=excluded.type, price=excluded.price, currentBattery=excluded.currentBattery, currentDockId=excluded.currentDockId, currentRentalTxId=excluded.currentRentalTxId, licensePlates=excluded.licensePlates",
                    bike.getId(), bike.getType().ordinal(), bike.getPrice(), bike.getCurrentBattery(),
                    bike.getCurrentDockId(),
                    bike.getCurrentRentalTxId(), bike.getLicensePlates()));
            return result > 0;

        } catch (Exception ex) {
            return false;
        }
    }

    private BikeEnity mappingResultSetToBikeEntity(ResultSet result) throws SQLException {
        return new BikeEnity(
                result.getLong("id"),
                BikeType.values()[result.getInt("type")],
                result.getInt("price"),
                result.getInt("currentBattery"),
                result.getLong("currentDockId") != 0 ? result.getLong("currentDockId") : null,
                result.getLong("currentRentalTxId") != 0 ? result.getLong("currentRentalTxId") : null,
                result.getString("licensePlates"));
    }
}
