package io.radmc.client.gui;

import lombok.Getter;
import org.lwjgl.system.NativeResource;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class GlfwWindow implements Window, NativeResource {

    private final long pWindow;
    private @Getter int width;
    private @Getter int height;

    private GlfwWindow(String title, int width, int height) {
        pWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        this.width = width;
        this.height = height;
    }

    public static GlfwWindow create(String title, int width, int height) {
        var window = new GlfwWindow(title, width, height);
        var vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window.pWindow,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2);
        glfwMakeContextCurrent(window.pWindow);
        return window;
    }

    @Override
    public void exit() {
        glfwSetWindowShouldClose(pWindow, true);
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(pWindow);
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(pWindow);
    }

    @Override
    public void show() {
        glfwShowWindow(pWindow);
    }

    @Override
    public void setKeyCallback(KeyCallback callback) {
        try (var old = glfwSetKeyCallback(pWindow, (window, key, scancode, action, mods) ->
                callback.invoke(key, scancode, action, mods))) {
        }
    }

    @Override
    public void free() {
        glfwFreeCallbacks(pWindow);
        glfwDestroyWindow(pWindow);
    }
}
