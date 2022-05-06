package adudecalledleo.merciadance;

public record FennecPartOffsets(FennecLimbOffset leftArmPose, FennecLimbOffset rightArmPose,
                                FennecLimbOffset leftLegPose, FennecLimbOffset rightLegPose) {
    public static final int[] LEFT_LEG_HEIGHTS = { 279, 68, 206, 33 };
    public static final int[] RIGHT_LEG_HEIGHTS = { 286, 81, 211, 40 };

    public static final FennecPartOffsets[] VALUES = {
            // front view
            new FennecPartOffsets(new FennecLimbOffset(148, 213, false), new FennecLimbOffset(230, 210, false),
                    new FennecLimbOffset(163, 367, false), new FennecLimbOffset(212, 364, false)),
            // side view
            new FennecPartOffsets(new FennecLimbOffset(174, 210, false), new FennecLimbOffset(199, 211, false),
                    new FennecLimbOffset(208, 349, false), new FennecLimbOffset(206, 353, true)),
            // bent over (facing camera)
            new FennecPartOffsets(new FennecLimbOffset(90, 320, false), new FennecLimbOffset(173, 296, false),
                    new FennecLimbOffset(173, 361, false), new FennecLimbOffset(218, 360, false)),
            // bent over (facing away from camera)
            new FennecPartOffsets(new FennecLimbOffset(283, 292, true), new FennecLimbOffset(221, 281, true),
                    new FennecLimbOffset(188, 374, true), new FennecLimbOffset(153, 373, true))
    };
}
