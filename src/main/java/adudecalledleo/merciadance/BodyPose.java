package adudecalledleo.merciadance;

public record BodyPose(LimbPose leftArmPose, LimbPose rightArmPose,
                       LimbPose leftLegPose, LimbPose rightLegPose) {
    public static final BodyPose[] BODY_POSES = {
            // front view
            new BodyPose(new LimbPose(148, 213, false), new LimbPose(230, 210, false),
                    new LimbPose(163, 367, false), new LimbPose(212, 364, false)),
            // side view
            new BodyPose(new LimbPose(174, 210, false), new LimbPose(199, 211, false),
                    new LimbPose(208, 349, false), new LimbPose(206, 353, true)),
            // bent over (facing camera)
            new BodyPose(new LimbPose(90, 320, false), new LimbPose(173, 296, false),
                    new LimbPose(173, 361, false), new LimbPose(218, 360, false)),
            // bent over (facing away from camera)
            new BodyPose(new LimbPose(283, 292, true), new LimbPose(221, 281, true),
                    new LimbPose(188, 374, true), new LimbPose(153, 373, true))
    };
}
