package io.radmc.client.model;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30C.*;

public final class VaoMesh extends Mesh {

    private final int vao;
    private final List<Integer> vbos;

    private VaoMesh(Vertex[] vertices) {
        super(vertices);
        vbos = new ArrayList<>();
        vao = generateVao(vertices);
    }

    public static VaoMesh of(Vertex... vertices) {
        return new VaoMesh(vertices);
    }

    private int generateVao(Vertex[] vertices) {
        var vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        generateVbos();
        for (var vertex : vertices) {
            var pos = vertex.position();
            store(Attribute.POSITION, pos.x(), pos.y(), pos.z());
        }

        glBindVertexArray(0);
        return vaoId;
    }

    private void generateVbos() {
        for (var ignored : Attribute.values()) {
            var vboId = glGenBuffers();
            vbos.add(vboId);
        }
    }

    private void store(Attribute attribute, float... data) {
        glBindBuffer(GL_ARRAY_BUFFER, vbos.get(attribute.location()));

        var buffer = storeInBuffer(data);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attribute.location(), attribute.size(), GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private static FloatBuffer storeInBuffer(float[] data) {
        var buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        return buffer.flip();
    }

    @Override
    public void bind() {
        glBindVertexArray(vao);
    }

    @Override
    public void unbind() {
        glBindVertexArray(0);
    }

    @Override
    public void dispose() {
        super.dispose();
        glDeleteVertexArrays(vao);

        for (var vbo : vbos) {
            glDeleteBuffers(vbo);
        }
        vbos.clear();
    }
}
