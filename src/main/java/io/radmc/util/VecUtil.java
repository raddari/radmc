package io.radmc.util;

import lombok.experimental.UtilityClass;
import org.joml.Vector2fc;
import org.joml.Vector3fc;
import org.joml.Vector4fc;

@UtilityClass
public class VecUtil {

    public float[] vecToArray(Vector2fc vec) {
        return new float[] {vec.x(), vec.y()};
    }

    public float[] vecToArray(Vector3fc vec) {
        return new float[] {vec.x(), vec.y(), vec.z()};
    }

    public float[] vecToArray(Vector4fc vec) {
        return new float[] {vec.x(), vec.y(), vec.z(), vec.w()};
    }

}
