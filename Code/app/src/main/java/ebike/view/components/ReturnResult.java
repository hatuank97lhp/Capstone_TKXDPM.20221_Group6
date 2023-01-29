package ebike.view.components;

import javax.swing.*;

import ebike.view.ApplicationUI;

public class ReturnResult extends JPanel {
    private ApplicationUI applicationUI;

    public ReturnResult(ApplicationUI applicationUI) {
        super();
        this.applicationUI = applicationUI;
        init();
    }

    public void init() {
        removeAll();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Return bike success"));
        var homeBtn = new JButton("Home");
        homeBtn.addActionListener(e -> {
            applicationUI.changeToStationListScreen();
        });
        add(Box.createVerticalStrut(20));
        add(homeBtn);
        revalidate();
    }
}
