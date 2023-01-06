package ebike.core.base;

public interface PaymentTx {
    public int getId();

    public int getBalance();

    public String getDescription();

    public CreditCard getCreditCard();

    public User getUser();
}
