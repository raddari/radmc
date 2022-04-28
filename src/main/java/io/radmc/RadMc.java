package io.radmc;

import io.radmc.client.gui.GlfwWindow;
import io.radmc.client.gui.Renderer;
import io.radmc.client.gui.Window;
import io.radmc.client.model.VaoMesh;
import io.radmc.client.model.Vertex;
import lombok.extern.log4j.Log4j2;
import org.joml.Vector3f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

@Log4j2
public final class RadMc implements Runnable {

    private static final GLFWErrorCallbackI ERROR_CALLBACK = (error, description) ->
            LOGGER.error("GLFW error {}: {}", error, GLFWErrorCallback.getDescription(description));
    private final Window window;

    private RadMc() {
        window = GlfwWindow.create("radmc", 1920, 1080);
        window.setKeyCallback((key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                window.exit();
            }
        });
        window.show();
        glfwSwapInterval(1);
    }

    @Override
    public void run() {
        LOGGER.info("Hellow LWJGL {}!", Version.getVersion());
        loop();
    }

    private void loop() {
        GL.createCapabilities();

        var positions = new Vector3f[] {
                new Vector3f(0.0F, 0.5F, 0.0F),
                new Vector3f(-0.5F, -0.5F, 0.0F),
                new Vector3f(0.5F, -0.5F, 0.0F),
        };

        var indices = new int[] {0, 1, 2};

        var mesh = VaoMesh.of(
                indices,
                Vertex.from(positions[0]),
                Vertex.from(positions[1]),
                Vertex.from(positions[2]));

        while (!window.shouldClose()) {
            Renderer.clear();
            Renderer.render(mesh);
            window.swapBuffers();
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("GL Thread");
        GLFWErrorCallback.create(ERROR_CALLBACK).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialise GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        new RadMc().run();

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
