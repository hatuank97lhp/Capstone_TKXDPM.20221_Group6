package ebike.core.domain.model;

public class DockingStationEnity implements Entity {
    private Long id;
    private String name;
    private Integer area;
    private String address;
    private Integer dockCapacity;
    private Integer numAvailableBike;

    public DockingStationEnity(Long id, String name, Integer area, String address, Integer dockCapacity,
            Integer numAvailableBike) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.address = address;
        this.dockCapacity = dockCapacity;
        this.numAvailableBike = numAvailableBike;
    }

    public void rentBike() {
        numAvailableBike -= 1;
    }

    public void returnBike() {
        numAvailableBike += 1;
    }

    public Integer getNumAvailableDock() {
        return this.dockCapacity - this.numAvailableBike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDockCapacity() {
        return dockCapacity;
    }

    public void setDockCapacity(Integer dockCapacity) {
        this.dockCapacity = dockCapacity;
    }

    public Integer getNumAvailableBike() {
        return numAvailableBike;
    }

    public void setNumAvailableBike(Integer numAvailableBike) {
        this.numAvailableBike = numAvailableBike;
    }

}
