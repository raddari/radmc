package io.radmc.client.model;

import io.radmc.util.Disposable;
import lombok.Getter;

public abstract class Mesh implements Disposable {

    private final @Getter int vertexCount;

    protected Mesh(Vertex[] vertices) {
        vertexCount = vertices.length;
    }

    public abstract void bind();
    public abstract void unbind();

    public void dispose() {
        unbind();
    }

}
