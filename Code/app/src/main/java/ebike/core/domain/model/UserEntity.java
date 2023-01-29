package ebike.core.domain.model;

public class UserEntity implements Entity {
    private Long id;
    private String fullname;
    private Long creditCardId;

    public UserEntity(Long id, String fullname, Long creditCardId) {
        this.id = id;
        this.fullname = fullname;
        this.creditCardId = creditCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }
}
