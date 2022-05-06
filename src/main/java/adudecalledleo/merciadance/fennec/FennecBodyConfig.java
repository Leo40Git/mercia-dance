package adudecalledleo.merciadance.fennec;

record FennecBodyConfig(FennecLimbConfig leftArm, FennecLimbConfig rightArm,
                               FennecLimbConfig leftLeg, FennecLimbConfig rightLeg) {
    public static final int[] LEFT_LEG_HEIGHTS = { 279, 68, 206, 33 };
    public static final int[] RIGHT_LEG_HEIGHTS = { 286, 81, 211, 40 };

    public static final FennecBodyConfig[] VALUES = {
            // front view
            new FennecBodyConfig(new FennecLimbConfig(148, 213, false), new FennecLimbConfig(230, 210, false),
                    new FennecLimbConfig(163, 367, false), new FennecLimbConfig(212, 364, false)),
            // side view
            new FennecBodyConfig(new FennecLimbConfig(174, 210, false), new FennecLimbConfig(199, 211, false),
                    new FennecLimbConfig(208, 349, false), new FennecLimbConfig(206, 353, true)),
            // bent over (facing camera)
            new FennecBodyConfig(new FennecLimbConfig(90, 320, false), new FennecLimbConfig(173, 296, false),
                    new FennecLimbConfig(173, 361, false), new FennecLimbConfig(218, 360, false)),
            // bent over (facing away from camera)
            new FennecBodyConfig(new FennecLimbConfig(283, 292, true), new FennecLimbConfig(221, 281, true),
                    new FennecLimbConfig(188, 374, true), new FennecLimbConfig(153, 373, true))
    };
}
