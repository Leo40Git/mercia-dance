package adudecalledleo.merciadance.fennec;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

import adudecalledleo.merciadance.Resources;

public final class FennecRenderer {
    private FennecRenderer() { }

    private static final AffineTransform TX = new AffineTransform();

    public static void drawMercia(Graphics2D g, FennecPose pose, int x, int y) {
        final FennecBodyConfig bodyCfg = FennecBodyConfig.VALUES[pose.body()];
        drawLimb(g, Resources.LEFT_LEGS[pose.leftLeg()], x, y, bodyCfg.leftLeg());
        drawLimb(g, Resources.RIGHT_LEGS[pose.rightLeg()], x, y, bodyCfg.rightLeg());
        drawLimb(g, Resources.LEFT_ARMS[pose.leftArm()], x, y, bodyCfg.leftArm());
        drawLimb(g, Resources.RIGHT_ARMS[pose.rightArm()], x, y, bodyCfg.rightArm());
        g.drawImage(Resources.get(Resources.BODIES[pose.body()]), x, y, null);
    }

    private static void drawLimb(Graphics2D g, String imageName, int baseX, int baseY, FennecLimbConfig limbCfg) {
        BufferedImage img = Resources.get(imageName);
        if (limbCfg.mirrored()) {
            TX.setToTranslation(baseX + limbCfg.x() + img.getWidth() / 2f, baseY + limbCfg.y() - img.getHeight() / 2f);
            TX.scale(-1, 1);
        } else {
            TX.setToTranslation(baseX + limbCfg.x() - img.getWidth() / 2f, baseY + limbCfg.y() - img.getHeight() / 2f);
        }
        g.drawImage(img, TX, null);
    }
}
