package ebike.core.domain.model;

public class DockingStationEnity implements Entity {
    private Integer id;
    private String name;
    private Integer area;
    private String address;
    private Integer dockCapacity;

    public DockingStationEnity(Integer id, String name, Integer area, String address, Integer dockCapacity) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.address = address;
        this.dockCapacity = dockCapacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
