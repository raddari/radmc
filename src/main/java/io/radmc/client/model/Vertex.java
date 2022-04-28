package io.radmc.client.model;

import io.radmc.util.ArrayUtil;
import io.radmc.util.VecUtil;
import org.joml.Vector3fc;
import org.joml.Vector4fc;

public final class Vertex {

    private final float[] combinedData;

    private Vertex(float[] combinedData) {
        this.combinedData = combinedData;
    }

    public static Vertex from(Vector3fc position, Vector4fc colour) {
        var posData = VecUtil.vecToArray(position);
        var colourData = VecUtil.vecToArray(colour);
        return new Vertex(ArrayUtil.combine(posData, colourData));
    }

    public float[] combined() {
        return combinedData.clone();
    }

    public int size() {
        return 3 * combinedData.length;
    }

}
