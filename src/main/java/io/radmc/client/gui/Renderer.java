package io.radmc.client.gui;

import io.radmc.client.model.Attribute;
import io.radmc.client.model.Mesh;
import lombok.experimental.UtilityClass;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL20C.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20C.glEnableVertexAttribArray;

@UtilityClass
public class Renderer {

    public void clear() {
        glClearColor(1.0F, 0.0F, 0.0F, 0.0F);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Mesh mesh) {
        mesh.bind();
        enableAttributes();

        glDrawArrays(GL_TRIANGLES, 0, mesh.vertexCount());

        disableAttributes();
        mesh.unbind();
    }

    private void enableAttributes() {
        for (var attribute : Attribute.values()) {
            glEnableVertexAttribArray(attribute.location());
        }
    }

    private void disableAttributes() {
        for (var attribute : Attribute.values()) {
            glDisableVertexAttribArray(attribute.location());
        }
    }

}
