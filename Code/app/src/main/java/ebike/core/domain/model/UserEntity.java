package ebike.core.domain.model;

public class UserEntity implements Entity {
    private Integer id;
    private String fullname;
    private Integer CreditCardId;

    public UserEntity(Integer id, String fullname, Integer creditCardId) {
        this.id = id;
        this.fullname = fullname;
        CreditCardId = creditCardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getCreditCardId() {
        return CreditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        CreditCardId = creditCardId;
    }
}
