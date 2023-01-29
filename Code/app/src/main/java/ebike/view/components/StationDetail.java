package ebike.view.components;

import javax.swing.*;
import java.awt.*;

import ebike.view.ApplicationUI;

public class StationDetail extends JPanel {
    private ApplicationUI applicationUI;

    public StationDetail(ApplicationUI applicationUI) {
        super();
        this.applicationUI = applicationUI;
        var a = new JLabel("Detail");
        add(a);
    }

    public void init(long id) {
        removeAll();
        var dock = applicationUI.getDockingStationAppService().getDockingStationDetailById(id);
        var backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            applicationUI.changeToStationListScreen();
        });

        var name = new JLabel(dock.name);
        var img = new JLabel(
                new ImageIcon(
                        new ImageIcon("app/resources/img/dock_img.png").getImage().getScaledInstance(120, 120,
                                Image.SCALE_DEFAULT)));
        var address = new JLabel(dock.address);
        var area = new JLabel(String.format("Area %s m2", dock.area));
        var numAvailableBike = new JLabel(String.format("Available bikes %s", dock.numAvailableBike));
        var numAvailableDock = new JLabel(String.format("Available dock %s", dock.numAvailableDock));

        var card = new JPanel();
        card.setLayout(new GridLayout(1, 2));
        card.add(Style.topJustify(img));
        card.add(Style.justifyBetweenVertical(name, address, area, numAvailableBike, numAvailableDock));
        card.setMaximumSize(new Dimension(1000, 120));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Style.leftJustify(backBtn));
        add(Box.createVerticalStrut(20));
        add(card);

        for (var i = 0; i < dock.availableBikes.size(); i += 2) {
            var wrapper = new JPanel();
            wrapper.setMaximumSize(new Dimension(1000, 120));
            wrapper.setLayout(new GridLayout(1, 2, 20, 0));

            for (var j = i; j <= i + 1; j++) {
                if (j == dock.availableBikes.size()) {
                    wrapper.add(new JPanel());
                    continue;
                }
                var bike = dock.availableBikes.get(j);
                var licensePlate = new JLabel(String.format("Plate %s", bike.licensePlates));
                var currentBattery = new JLabel(String.format("Battery %s", bike.currentBattery));
                var bikeType = new JLabel(bike.type.toString());
                var depositCost = new JLabel(String.format("Deposit cost %s", bike.depositCost));
                var bikeImg = new JLabel(
                        new ImageIcon(
                                new ImageIcon("app/resources/img/dock_img.png").getImage().getScaledInstance(120, 120,
                                        Image.SCALE_DEFAULT)));

                wrapper.add(Style.wrappHorizontal(Style.topJustify(bikeImg), Box.createHorizontalStrut(10),
                        Style.justifyBetweenVertical(bikeType, licensePlate, currentBattery, depositCost)));
            }

            add(Box.createVerticalStrut(20));
            add(wrapper);
        }

        revalidate();
    }
}
