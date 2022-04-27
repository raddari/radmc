package io.radmc.client.gui;

public interface Window {

    int width();
    int height();
    void exit();
    boolean shouldClose();
    void swapBuffers();
    void show();
    void setKeyCallback(KeyCallback callback);

}
