package ebike.infrastructure.db.repository;

import java.sql.Connection;

import ebike.core.domain.model.PaymentTxEntity;
import ebike.core.domain.repository.IPaymentTxRepo;
import ebike.core.domain.repository.Repository;

public class PaymentRepo implements Repository, IPaymentTxRepo {

    private Connection con;

    public PaymentRepo(Connection con) {
        this.con = con;
    }

    @Override
    public boolean save(PaymentTxEntity tx) {
        try {
            var stmt = con.createStatement();
            var result = stmt.executeUpdate(String.format(
                    "insert into paymentTxs (id, balance, description, creditCardId) values (%s, %s, '%s', %s) on conflict(id) do update set balance=excluded.balance, description=excluded.description, creditCardId=excluded.creditCardId",
                    tx.getId(), tx.getBalance(), tx.getDescription(), tx.getCreditCardId()));
            return result > 0;

        } catch (Exception ex) {
            return false;
        }
    }

}
