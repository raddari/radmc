package io.radmc.client.shader;

import io.radmc.util.Disposable;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL20C.*;

@Log4j2
public final class Shader implements Disposable {

    private final @Getter int id;

    public Shader(String fileName, int shaderType) throws IOException {
        id = loadShader(fileName, shaderType);
    }

    private static int loadShader(String fileName, int shaderType) throws IOException {
        var lines = Files.readString(Path.of(fileName));
        var shaderId = glCreateShader(shaderType);

        glShaderSource(shaderId, lines);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            LOGGER.error("Could not compile shader from {}: {}", fileName, glGetShaderInfoLog(shaderId));
            throw new IOException("compiling shader");
        }

        return shaderId;
    }

    @Override
    public void dispose() {
        glDeleteShader(id);
    }
}
