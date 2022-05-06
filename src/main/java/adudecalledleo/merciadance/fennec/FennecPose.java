package adudecalledleo.merciadance.fennec;

import java.util.random.RandomGenerator;

public record FennecPose(int body, int leftArm, int rightArm, int leftLeg, int rightLeg) {
    public static FennecPose nextRandom(RandomGenerator rand) {
        int body = rand.nextInt(4);
        int larm = rand.nextInt(4);
        int rarm = rand.nextInt(4);
        int lleg = rand.nextInt(4);
        int rleg = rand.nextInt(4);
        return new FennecPose(body, larm, rarm, lleg, rleg);
    }
}
