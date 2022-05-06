package adudecalledleo.merciadance;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public final class FennecRenderer {
    private FennecRenderer() { }

    private static final AffineTransform TX = new AffineTransform();

    public static void drawMercia(Graphics2D g, FennecPose pose, int x, int y) {
        drawLimb(g, Resources.LEFT_LEGS[pose.leftLeg()], x, y, pose.leftLegOffset());
        drawLimb(g, Resources.RIGHT_LEGS[pose.rightLeg()], x, y, pose.rightLefOffset());
        drawLimb(g, Resources.LEFT_ARMS[pose.leftArm()], x, y, pose.leftArmOffset());
        drawLimb(g, Resources.RIGHT_ARMS[pose.rightArm()], x, y, pose.rightArmOffset());
        g.drawImage(Resources.get(Resources.BODIES[pose.body()]), x, y, null);
    }

    private static void drawLimb(Graphics2D g, String imageName, int baseX, int baseY, FennecLimbOffset pose) {
        BufferedImage img = Resources.get(imageName);
        if (pose.mirrored()) {
            TX.setToTranslation(baseX + pose.x() + img.getWidth() / 2f, baseY + pose.y() - img.getHeight() / 2f);
            TX.scale(-1, 1);
        } else {
            TX.setToTranslation(baseX + pose.x() - img.getWidth() / 2f, baseY + pose.y() - img.getHeight() / 2f);
        }
        g.drawImage(img, TX, null);
    }
}
