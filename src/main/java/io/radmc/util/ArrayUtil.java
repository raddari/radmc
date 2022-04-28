package io.radmc.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class ArrayUtil {

    public float[] combine(float[] first, float[] second) {
        var cat = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, cat, first.length, second.length);
        return cat;
    }

}
