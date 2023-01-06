package ebike.core.base;

import java.util.Date;

public interface CreditCard {
    public int getId();

    public String getBrand();

    public String getNumber();

    public String getCvv();

    public String getName();

    public Date getExpiry();

}
