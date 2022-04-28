package io.radmc.client.model;

import io.radmc.client.shader.Attribute;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30C.*;

public final class VaoMesh extends Mesh {

    private final int vao;
    private final List<Integer> vbos;

    private VaoMesh(int[] indices, Vertex[] vertices) {
        super(indices, vertices);
        vbos = new ArrayList<>();
        vao = generateVao();
    }

    public static VaoMesh of(int[] indices, Vertex... vertices) {
        return new VaoMesh(indices, vertices);
    }

    private int generateVao() {
        var vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        generateVbos();
        for (var vertex : vertices) {
            var pos = vertex.position();
            store(Attribute.POSITION, pos.x(), pos.y(), pos.z());
        }
        bindIndices();

        glBindVertexArray(0);
        return vaoId;
    }

    private void generateVbos() {
        for (var ignored : Attribute.values()) {
            var vboId = glGenBuffers();
            vbos.add(vboId);
        }
    }

    private void bindIndices() {
        var vboId = glGenBuffers();
        vbos.add(vboId);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
        var buffer = storeInBuffer(indices);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
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

    private static IntBuffer storeInBuffer(int[] data) {
        var buffer = BufferUtils.createIntBuffer(data.length);
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
