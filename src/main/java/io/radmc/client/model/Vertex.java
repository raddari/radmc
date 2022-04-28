package io.radmc.client.model;

import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class Vertex {

    private final Vector3f position;

    private Vertex(Vector3f position) {
        this.position = position;
    }

    public static Vertex from(Vector3fc position) {
        return new Vertex(new Vector3f(position));
    }

    public Vector3fc position() {
        return position;
    }

    public int size() {
        return 3 * Float.BYTES;
    }

}
