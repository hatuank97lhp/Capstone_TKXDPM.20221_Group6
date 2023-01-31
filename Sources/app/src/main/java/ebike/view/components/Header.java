package ebike.view.components;

import javax.swing.*;

import ebike.view.ApplicationUI;

public class Header extends JPanel {
    private ApplicationUI applicationUI;

    public Header(ApplicationUI applicationUI) {
        super();
        this.applicationUI = applicationUI;
        init();
    }

    public void init() {
        removeAll();

        var logo = new JLabel("EBIKE");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        var user = applicationUI.getUserAppService().getById(applicationUI.getStateStore().userId);
        applicationUI.getStateStore().userName = user.fullname;
        applicationUI.getStateStore().balance = user.balance;

        var rentBtn = new JButton("Rent bike");

        rentBtn.addActionListener(e -> {
            applicationUI.changeToBarCodeInputScreen();
        });

        var currentRentalTx = new JButton("Current rent");

        currentRentalTx.addActionListener(e -> {
            applicationUI.changeToCurrentRentTxScreen();
        });

        add(logo);
        add(Box.createHorizontalGlue());
        add(Box.createVerticalStrut(50));

        add(rentBtn);
        add(Box.createHorizontalStrut(10));
        add(currentRentalTx);
        add(Box.createHorizontalStrut(10));
        add(new JLabel(applicationUI.getStateStore().userName));
        add(Box.createHorizontalStrut(10));
        add(new JLabel(String.valueOf(applicationUI.getStateStore().balance)));

        revalidate();
    }
}
