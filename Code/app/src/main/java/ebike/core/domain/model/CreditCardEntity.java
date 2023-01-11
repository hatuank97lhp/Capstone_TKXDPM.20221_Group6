package ebike.core.domain.model;

public class CreditCardEntity implements Entity {
    private int id;
    private String cardHolderName;
    private String cardNumber;
    private String bank;
    private String expirationDate;
    private String code;

    public CreditCardEntity(int id, String cardHolderName, String cardNumber, String bank, String expirationDate,
            String code) {
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.expirationDate = expirationDate;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
