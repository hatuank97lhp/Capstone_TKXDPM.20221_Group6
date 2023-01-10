package ebike.core.domain.model;

public class PaymentTxEntity implements Entity {
    private Integer id;
    private Integer balance;
    private Integer description;
    private Integer userId;
    private Integer creditCardId;

    public PaymentTxEntity(Integer id, Integer balance, Integer description, Integer userId, Integer creditCardId) {
        this.id = id;
        this.balance = balance;
        this.description = description;
        this.userId = userId;
        this.creditCardId = creditCardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

}
