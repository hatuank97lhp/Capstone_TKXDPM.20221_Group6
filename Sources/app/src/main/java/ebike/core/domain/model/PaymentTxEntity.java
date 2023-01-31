package ebike.core.domain.model;

public class PaymentTxEntity implements Entity {
    private Long id;
    private Long balance;
    private String description;
    private Long creditCardId;

    public PaymentTxEntity(Long id, Long balance, String description, Long creditCardId) {
        this.id = id;
        this.balance = balance;
        this.description = description;
        this.creditCardId = creditCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

}
