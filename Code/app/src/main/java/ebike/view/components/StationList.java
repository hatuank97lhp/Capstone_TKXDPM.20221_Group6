package ebike.view.components;

import javax.swing.*;

import java.awt.*;

import ebike.view.ApplicationUI;

public class StationList extends JPanel {
    private ApplicationUI applicationUI;

    public StationList(ApplicationUI applicationUI) {
        super();
        this.applicationUI = applicationUI;
        init();
    }

    public void init() {
        removeAll();

        var title = new JLabel("Stations list");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Style.leftJustify(title));

        var docks = applicationUI.getDockingStationAppService().getListDockingStation();

        for (var i = 0; i < docks.size(); i += 2) {
            var wrapperCard = new JPanel();
            wrapperCard.setMaximumSize(new Dimension(1000, 120));
            wrapperCard.setLayout(new GridLayout(1, 2, 20, 0));
            for (var j = i; j <= i + 1; j++) {
                if (j == docks.size()) {
                    wrapperCard.add(new JPanel());
                    continue;
                }

                var dock = docks.get(j);
                var name = new JLabel(dock.name);
                var img = new JLabel(
                        new ImageIcon(
                                new ImageIcon("app/resources/img/dock_img.png").getImage().getScaledInstance(120, 120,
                                        Image.SCALE_DEFAULT)));
                var address = new JLabel(dock.address);
                var area = new JLabel(String.format("Area %s m2", dock.area));
                var numAvailableBike = new JLabel(String.format("Available bikes %s", dock.numAvailableBike));
                var numAvailableDock = new JLabel(String.format("Available dock %s", dock.numAvailableDock));

                var viewBtn = new JButton("Detail");

                viewBtn.addActionListener(e -> {
                    applicationUI.changeToStationDetailScreen(dock.id);
                });

                wrapperCard.add(Style.wrappHorizontal(Style.topJustify(img), Box.createHorizontalStrut(10),
                        Style.justifyBetweenVertical(name, address, area, numAvailableBike, numAvailableDock),
                        Box.createHorizontalGlue(), viewBtn));
            }
            add(Box.createVerticalStrut(20));
            add(wrapperCard);
        }

        revalidate();
    }
}
