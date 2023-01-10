package ebike.core.domain.model;

import java.util.Date;

public class CreditCard {
    private int id;
    private String cardHolderName;
    private String cardNumber;
    private String bank;
    private Date expirationDate;
    private String code;

    public CreditCard(int id, String cardHolderName, String cardNumber, String bank, Date expirationDate, String code) {
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
