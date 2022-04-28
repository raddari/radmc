package io.radmc.client.model;

import io.radmc.util.Disposable;
import lombok.Getter;

public abstract class Mesh implements Disposable {

    protected final int[] indices;
    protected final Vertex[] vertices;
    private final @Getter int vertexCount;

    protected Mesh(int[] indices, Vertex[] vertices) {
        this.indices = indices;
        this.vertices = vertices;
        vertexCount = indices.length;
    }

    public abstract void bind();
    public abstract void unbind();

    public void dispose() {
        unbind();
    }

}
