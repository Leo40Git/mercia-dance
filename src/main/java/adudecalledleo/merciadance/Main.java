package adudecalledleo.merciadance;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public final class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set system L&F");
            e.printStackTrace();
        }

        try {
            Resources.load();
        } catch (IOException e) {
            System.err.println("Failed to load resources");
            e.printStackTrace();
            System.exit(1);
            return;
        }

        var env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        var screenBounds = env.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        var frame = new DanceFrame();
        frame.setSize(screenBounds.width, screenBounds.height - 1); // height - 1 so we don't block collapsible taskbars
        frame.setLocationRelativeTo(null);
        frame.startDancing();
    }
}
