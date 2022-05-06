package adudecalledleo.merciadance;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.random.RandomGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;

public final class Main {
    private static final Color BACKGROUND = Color.WHITE;

    private static final String ARG_POSE_PREFIX = "/pose:";

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

        FennecPose pose = null;
        for (String arg : args) {
            if (arg.startsWith(ARG_POSE_PREFIX)) {
                arg = arg.substring(ARG_POSE_PREFIX.length());
                try {
                    pose = FennecPose.parse(arg);
                } catch (IllegalArgumentException e) {
                    System.err.println("Failed to parse argument");
                    e.printStackTrace();
                    System.exit(1);
                    return;
                }
            } else {
                System.err.printf("Unrecognized argument \"%s\"%n", arg);
            }
        }

        if (pose == null) {
            pose = FennecPose.getRandom(RandomGenerator.getDefault());
        }

        int llegH = FennecPartOffsets.LEFT_LEG_HEIGHTS[pose.leftLeg()];
        int rlegH = FennecPartOffsets.RIGHT_LEG_HEIGHTS[pose.rightLeg()];

        BufferedImage image = new BufferedImage(816, 624, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setBackground(BACKGROUND);
        g.clearRect(0, 0, image.getWidth(), image.getHeight());

        int heightOffset;
        if (llegH >= rlegH) {
            heightOffset = llegH - rlegH;
        } else {
            heightOffset = rlegH - llegH;
        }

        BufferedImage bodyImage = Resources.get(Resources.BODIES[pose.body()]);
        int x = image.getWidth() / 2 - bodyImage.getWidth() / 2;
        int y = 100 - heightOffset; // TODO figure base Y pos out

        FennecRenderer.drawMercia(g, pose, x, y);

        g.dispose();

        Path outPath = Paths.get(".", "output.png").getFileName();
        try (OutputStream out = Files.newOutputStream(outPath)) {
            ImageIO.write(image, "PNG", out);
        } catch (IOException e) {
            System.err.printf("Failed to save output to \"%s\"!%n", outPath.toString());
            e.printStackTrace();
        }
    }
}
