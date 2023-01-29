package ebike.infrastructure.db.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ebike.core.domain.model.DockingStationEnity;
import ebike.core.domain.repository.IDockingStationRepo;
import ebike.core.domain.repository.Repository;

public class DockingStationRepo implements Repository, IDockingStationRepo {
    private Connection con;

    public DockingStationRepo(Connection con) {
        this.con = con;
    }

    @Override
    public List<DockingStationEnity> getListDockingStation() {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery("select * from stations");

            var stations = new ArrayList<DockingStationEnity>();

            while (result.next()) {
                stations.add(mappingResultSetToDockingStationEntity(result));
            }

            return stations;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public DockingStationEnity getDockingStationById(Long id) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeQuery("select * from stations");

            if (result.next()) {
                return mappingResultSetToDockingStationEntity(result);
            }

            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean save(DockingStationEnity dock) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeUpdate(String.format(
                    "insert into stations (id, name, area, address, dockCapacity, numAvailableBike) values (%s, '%s', %s, '%s', %s, %s) on conflict(id) do update set name=excluded.name, area=excluded.area, address=excluded.address, dockCapacity=excluded.dockCapacity, numAvailableBike=excluded.numAvailableBike",
                    dock.getId(), dock.getName(), dock.getArea(), dock.getAddress(), dock.getDockCapacity(),
                    dock.getNumAvailableBike()));
            return result > 0;

        } catch (Exception ex) {
            return false;
        }
    }

    private DockingStationEnity mappingResultSetToDockingStationEntity(ResultSet rs) throws SQLException {
        return new DockingStationEnity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("area"),
                rs.getString("address"),
                rs.getInt("dockCapacity"),
                rs.getInt("numAvailableBike"));
    }
}
