package io.radmc.client.model;

import io.radmc.client.shader.Attribute;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.EnumMap;
import java.util.Map;

public final class Vertex {

    private final Map<Attribute, FloatBuffer> attributeMap;

    private Vertex(Vector3f position, Vector4f colour) {
        attributeMap = new EnumMap<>(Attribute.class);
        putAttribute(Attribute.POSITION, position);
        putAttribute(Attribute.COLOUR, colour);
    }

    private void putAttribute(Attribute attribute, Vector3fc data) {
        var buffer = createBuffer(attribute.size());
        attributeMap.put(attribute, data.get(buffer));
    }

    private void putAttribute(Attribute attribute, Vector4fc data) {
        var buffer = createBuffer(attribute.size());
        attributeMap.put(attribute, data.get(buffer));
    }

    private static FloatBuffer createBuffer(int capacity) {
        return BufferUtils.createFloatBuffer(capacity);
    }

    public static Vertex from(Vector3fc position, Vector4fc colour) {
        return new Vertex(new Vector3f(position), new Vector4f(colour));
    }

    public float[] get(Attribute attribute) {
        var arr = new float[attribute.size()];
        attributeMap.get(attribute).get(arr);
        return arr;
    }

    public int size() {
        return 7 * Float.BYTES;
    }

}
