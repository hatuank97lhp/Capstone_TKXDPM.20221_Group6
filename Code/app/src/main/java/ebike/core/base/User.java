package ebike.core.base;

import java.util.Date;

public interface User {
    public int getId();

    public String getFullName();

    public String getPhone();

    public Date getDOB();

    public String getProvince();

    public String getAddress();

    public String getEmail();

    public String getPassword();

    public CreditCard getCreditCard();
}
