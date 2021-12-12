package adudecalledleo.merciadance;

import java.awt.*;
import java.awt.geom.*;
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

        boolean poseSet = false;
        int body = 0, larm = 0, rarm = 0, lleg = 0, rleg = 0;
        for (String arg : args) {
            if (arg.startsWith(ARG_POSE_PREFIX)) {
                arg = arg.substring(ARG_POSE_PREFIX.length());
                char[] chars = arg.toCharArray();
                if (chars.length != 5) {
                    System.err.println("Pose value must be 5 characters long");
                    System.exit(1);
                    return;
                }
                try {
                    body = parsePoseInt("Body", chars[0]);
                    larm = parsePoseInt("Left arm", chars[1]);
                    rarm = parsePoseInt("Right arm", chars[2]);
                    lleg = parsePoseInt("Left leg", chars[3]);
                    rleg = parsePoseInt("Right leg", chars[4]);
                } catch (IllegalArgumentException e) {
                    System.err.println("Failed to parse argument");
                    e.printStackTrace();
                    System.exit(1);
                    return;
                }
                poseSet = true;
            } else {
                System.err.printf("Unrecognized argument \"%s\"%n", arg);
            }
        }

        if (!poseSet) {
            RandomGenerator random = RandomGenerator.getDefault();
            body = random.nextInt(Resources.BODIES.length);
            larm = random.nextInt(Resources.LEFT_ARMS.length);
            rarm = random.nextInt(Resources.RIGHT_ARMS.length);
            lleg = random.nextInt(Resources.LEFT_LEGS.length);
            rleg = random.nextInt(Resources.RIGHT_LEGS.length);
        }

        int llegH = BodyPose.LEFT_LEG_HEIGHTS[lleg];
        int rlegH = BodyPose.RIGHT_LEG_HEIGHTS[rleg];

        BufferedImage image = new BufferedImage(816, 624, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setBackground(BACKGROUND);
        g.clearRect(0, 0, image.getWidth(), image.getHeight());

        BodyPose bodyPose = BodyPose.VALUES[body];
        int heightOffset;
        if (llegH >= rlegH) {
            heightOffset = llegH - rlegH;
        } else {
            heightOffset = rlegH - llegH;
        }

        BufferedImage bodyImage = Resources.get(Resources.BODIES[body]);
        int x = image.getWidth() / 2 - bodyImage.getWidth() / 2;
        int y = 100 - heightOffset; // TODO figure base Y pos out

        drawLimb(g, Resources.LEFT_LEGS[lleg], x, y, bodyPose.leftLegPose());
        drawLimb(g, Resources.RIGHT_LEGS[rleg], x, y, bodyPose.rightLegPose());
        drawLimb(g, Resources.LEFT_ARMS[larm], x, y, bodyPose.leftArmPose());
        drawLimb(g, Resources.RIGHT_ARMS[rarm], x, y, bodyPose.rightArmPose());
        g.drawImage(bodyImage, x, y, null);

        g.dispose();

        Path outPath = Paths.get(".", "output.png").getFileName();
        try (OutputStream out = Files.newOutputStream(outPath)) {
            ImageIO.write(image, "PNG", out);
        } catch (IOException e) {
            System.err.printf("Failed to save output to \"%s\"!%n", outPath.toString());
            e.printStackTrace();
        }
    }

    private static int parsePoseInt(String name, char c) {
        int i = Integer.parseUnsignedInt(Character.toString(c));
        if (i < 1 || i > 4) {
            throw new IllegalArgumentException("%s index must be between 1 and 4 (inclusive), but was %d!"
                    .formatted(name, i));
        }
        return i - 1;
    }

    private static void drawLimb(Graphics2D g, String imageName, int baseX, int baseY, LimbPose pose) {
        BufferedImage img = Resources.get(imageName);
        AffineTransform at = new AffineTransform();
        if (pose.mirrored()) {
            at = new AffineTransform();
            at.setToTranslation(baseX + pose.x() + img.getWidth() / 2, baseY + pose.y() - img.getHeight() / 2);
            at.scale(-1, 1);
        } else {
            at.setToTranslation(baseX + pose.x() - img.getWidth() / 2, baseY + pose.y() - img.getHeight() / 2);
        }

        g.drawImage(img, at, null);
    }
}
