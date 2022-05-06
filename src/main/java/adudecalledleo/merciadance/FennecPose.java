package adudecalledleo.merciadance;

import java.util.random.RandomGenerator;

public record FennecPose(int body, int leftArm, int rightArm, int leftLeg, int rightLeg) {
    public static FennecPose parse(String str) {
        if (str.length() != 5) {
            throw new IllegalArgumentException("pose string must be exactly 5 characters");
        }
        char[] chars = str.toCharArray();
        int body, larm, rarm, lleg, rleg;
        try {
            body = parsePoseInt("body", chars[0]);
            larm = parsePoseInt("left arm", chars[1]);
            rarm = parsePoseInt("right arm", chars[2]);
            lleg = parsePoseInt("left leg", chars[3]);
            rleg = parsePoseInt("right leg", chars[4]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("out of bounds part index", e);
        }
        return new FennecPose(body, larm, rarm, lleg, rleg);
    }

    private static int parsePoseInt(String name, char c) {
        int i = Integer.parseUnsignedInt(Character.toString(c));
        if (i < 1 || i > 4) {
            throw new IndexOutOfBoundsException("%s index must be between 1 and 4 (inclusive), but was %d"
                    .formatted(name, i));
        }
        return i - 1;
    }

    public static FennecPose getRandom(RandomGenerator rand) {
        int body = rand.nextInt(4);
        int larm = rand.nextInt(4);
        int rarm = rand.nextInt(4);
        int lleg = rand.nextInt(4);
        int rleg = rand.nextInt(4);
        return new FennecPose(body, larm, rarm, lleg, rleg);
    }

    public FennecLimbOffset leftArmOffset() {
        return FennecPartOffsets.VALUES[body].leftArmPose();
    }

    public FennecLimbOffset rightArmOffset() {
        return FennecPartOffsets.VALUES[body].rightArmPose();
    }

    public FennecLimbOffset leftLegOffset() {
        return FennecPartOffsets.VALUES[body].leftLegPose();
    }

    public FennecLimbOffset rightLefOffset() {
        return FennecPartOffsets.VALUES[body].rightLegPose();
    }
}
