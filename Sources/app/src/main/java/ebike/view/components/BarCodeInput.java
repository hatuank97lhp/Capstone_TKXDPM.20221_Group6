package ebike.view.components;

import javax.swing.*;
import java.awt.*;

import ebike.view.ApplicationUI;

public class BarCodeInput extends JPanel {
    private ApplicationUI applicationUI;

    public BarCodeInput(ApplicationUI applicationUI) {
        super();
        this.applicationUI = applicationUI;
        init();
    }

    public void init() {
        removeAll();
        var backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            applicationUI.changeToStationListScreen();
        });

        var code = new JLabel("Code");
        var input = new JTextField();
        var submitBtn = new JButton("Rent");

        submitBtn.addActionListener(e -> {
            applicationUI.changeToRentBikePreviewScreen(input.getText());
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Style.leftJustify(backBtn));
        add(Box.createVerticalStrut(20));
        var wraper = Style.wrappHorizontal(code, Box.createHorizontalStrut(20), input, Box.createHorizontalStrut(20),
                submitBtn);
        wraper.setMaximumSize(new Dimension(1000, 50));
        add(wraper);
        revalidate();
    }
}
