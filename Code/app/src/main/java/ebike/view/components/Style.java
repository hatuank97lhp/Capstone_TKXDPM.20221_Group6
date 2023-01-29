package ebike.view.components;

import java.awt.*;

import javax.swing.Box;;

public class Style {
    public static Component leftJustify(Component c) {
        var b = Box.createHorizontalBox();
        b.add(c);
        b.add(Box.createHorizontalGlue());
        return b;
    }

    public static Component topJustify(Component c) {
        var b = Box.createVerticalBox();
        b.add(c);
        b.add(Box.createVerticalGlue());
        return b;
    }

    public static Component justifyBetweenVertical(Component... cs) {
        var b = Box.createVerticalBox();

        for (var i = 0; i < cs.length; i++) {
            b.add(cs[i]);
            if (i != cs.length - 1) {
                b.add(Box.createVerticalGlue());
            }
        }
        return b;
    }

    public static Component wrappHorizontal(Component... cs) {
        var b = Box.createHorizontalBox();

        for (var c : cs) {
            b.add(c);
        }

        return b;
    }

    public static Component wrappVertical(Component... cs) {
        var b = Box.createVerticalBox();

        for (var c : cs) {
            b.add(c);
        }

        return b;
    }
}
