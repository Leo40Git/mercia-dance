package adudecalledleo.merciadance;

import java.awt.image.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;

public final class Resources {
    private Resources() { }

    public static final String[] BODIES = { "body1", "body2", "body3", "body4" };
    public static final String[] LEFT_ARMS = { "larm1", "larm2", "larm3", "larm4" };
    public static final String[] RIGHT_ARMS = { "rarm1", "rarm2", "rarm3", "rarm4" };
    public static final String[] LEFT_LEGS = { "lleg1", "lleg2", "lleg3", "lleg4" };
    public static final String[] RIGHT_LEGS = { "rleg1", "rleg2", "rleg3", "rleg4" };

    private static Map<String, BufferedImage> images;

    public static void load() throws IOException {
        List<String> toLoad = new ArrayList<>();
        Collections.addAll(toLoad, BODIES);
        Collections.addAll(toLoad, LEFT_ARMS);
        Collections.addAll(toLoad, RIGHT_ARMS);
        Collections.addAll(toLoad, LEFT_LEGS);
        Collections.addAll(toLoad, RIGHT_LEGS);

        images = new HashMap<>();
        for (String name : toLoad) {
            try (InputStream in = getResourceAsStream("/%s.png".formatted(name))) {
                BufferedImage image = ImageIO.read(in);
                images.put(name, image);
            }
        }
    }

    public static BufferedImage get(String name) {
        return images.get(name);
    }

    private static InputStream getResourceAsStream(String path) throws IOException {
        var in = Resources.class.getResourceAsStream(path);
        if (in == null) {
            throw new FileNotFoundException(path);
        }
        return in;
    }
}
