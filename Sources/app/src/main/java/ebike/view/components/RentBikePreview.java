package ebike.view.components;

import javax.swing.*;
import java.awt.*;

import ebike.core.domain.model.def.RentalBikePolicy;
import ebike.view.ApplicationUI;

public class RentBikePreview extends JPanel {
    private ApplicationUI applicationUI;

    public RentBikePreview(ApplicationUI applicationUI) {
        this.applicationUI = applicationUI;
    }

    public void init(String code) {
        removeAll();

        var bike = applicationUI.getBikeAppService().getBikePreviewByBarcode(code);

        var licensePlate = new JLabel(String.format("Plate %s", bike.licensePlates));
        var currentBattery = new JLabel(String.format("Battery %s", bike.currentBattery));
        var bikeType = new JLabel(bike.type.toString());
        var depositCost = new JLabel(String.format("Deposit cost %s", bike.depositCost));
        var bikeImg = new JLabel(
                new ImageIcon(
                        new ImageIcon("app/resources/img/bike.jpg").getImage().getScaledInstance(120, 120,
                                Image.SCALE_DEFAULT)));

        var info = new JPanel();
        info.setMaximumSize(new Dimension(1000, 120));
        info.setLayout(new GridLayout(1, 2));

        info.add(bikeImg);
        info.add(Style.justifyBetweenVertical(licensePlate, currentBattery, bikeType, depositCost));

        var confirmBtn = new JButton("Confirm");

        confirmBtn.addActionListener(e -> {
            applicationUI.getBikeAppService().rentBike(bike.id, RentalBikePolicy.NORMAL,
                    applicationUI.getStateStore().userId);
            applicationUI.resetBalanceView();
            applicationUI.changeToCurrentRentTxScreen();
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(info);
        add(Box.createVerticalStrut(20));
        add(confirmBtn);

        revalidate();
    }
}
