package io.radmc.client.shader;

import io.radmc.util.Disposable;

import static org.lwjgl.opengl.GL20C.*;

public final class ShaderProgram implements Disposable {

    private final int program;
    private final Shader[] shaders;

    private ShaderProgram(Shader... shaders) {
        program = createProgram(shaders);
        this.shaders = shaders;
        bindAttributes();
    }

    public static ShaderProgram from(Shader vertexShader, Shader fragmentShader) {
        return new ShaderProgram(vertexShader, fragmentShader);
    }

    private static int createProgram(Shader... shaders) {
        var programId = glCreateProgram();
        for (var shader : shaders) {
            glAttachShader(programId, shader.id());
        }
        glLinkProgram(programId);
        return programId;
    }

    private void bindAttributes() {
        for (var attribute : Attribute.values()) {
            bindAttribute(attribute);
        }
    }

    private void bindAttribute(Attribute attribute) {
        glBindAttribLocation(program, attribute.location(), attribute.shaderVar());
    }

    public void start() {
        glUseProgram(program);
    }

    public void stop() {
        glUseProgram(0);
    }

    @Override
    public void dispose() {
        stop();
        for (var shader : shaders) {
            glDetachShader(program, shader.id());
            shader.dispose();
        }
        glDeleteProgram(program);
    }
}
