package ebike.view.components;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import ebike.view.ApplicationUI;

public class ReturnBikePreview extends JPanel {
    private ApplicationUI applicationUI;

    public ReturnBikePreview(ApplicationUI applicationUI) {
        super();
        this.applicationUI = applicationUI;
    }

    public void init(long toDockId) {
        var bike = applicationUI.getBikeAppService()
                .getCurrentRentalBikeDetailOfUser(applicationUI.getStateStore().userId);

        if (bike == null) {
            add(new JLabel("No current rent bike"));
            revalidate();
            return;
        }

        var tx = bike.currentRentalTxOutput;

        var licensePlate = new JLabel(String.format("Plate %s", bike.licensePlates));
        var currentBattery = new JLabel(String.format("Battery %s", bike.currentBattery));
        var bikeType = new JLabel(bike.type.toString());
        var bikeImg = new JLabel(
                new ImageIcon(
                        new ImageIcon("app/resources/img/dock_img.png").getImage().getScaledInstance(120, 120,
                                Image.SCALE_DEFAULT)));

        var bikeInfo = new JPanel();
        bikeInfo.setMaximumSize(new Dimension(1000, 120));
        bikeInfo.setLayout(new GridLayout(1, 2));

        bikeInfo.add(bikeImg);
        bikeInfo.add(Style.justifyBetweenVertical(licensePlate, currentBattery, bikeType));

        var fromDockInfo = applicationUI.getDockingStationAppService().getDockingStationById(tx.fromDock);

        var fromDock = new JLabel(String.format("From station: %s", fromDockInfo.name));
        var startAt = new JLabel(
                String.format("Start at: %s", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                        .withZone(ZoneId.systemDefault()).format(tx.startAt)));
        var estimateCost = new JLabel(String.format("Estimate current cost: %s", tx.currentCost));
        var depositCost = new JLabel(String.format("Deposit cost: %s", bike.depositCost));

        var toDockInfo = applicationUI.getDockingStationAppService().getDockingStationById(toDockId);
        var toDock = new JLabel(String.format("Return to station: %s", toDockInfo.name));

        var txInfo = new JPanel();
        txInfo.setMaximumSize(new Dimension(1000, 120));
        txInfo.setLayout(new BoxLayout(txInfo, BoxLayout.Y_AXIS));
        txInfo.add(Style.justifyBetweenVertical(fromDock, toDock, startAt, depositCost, estimateCost));

        var returnBtn = new JButton("Confirm");

        returnBtn.addActionListener(e -> {
            applicationUI.getBikeAppService().returnBike(bike.id, toDockId);
            applicationUI.resetBalanceView();
            applicationUI.changeToReturnResultScreen();
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(bikeInfo);
        add(Box.createVerticalStrut(20));
        add(txInfo);
        add(Box.createVerticalStrut(20));
        add(returnBtn);

        revalidate();
    }
}
