package io.radmc.client.shader;

import lombok.Getter;

public enum Attribute {

    POSITION(0, 3, "a_position"),
    COLOUR(1, 4, "a_colour");

    private final @Getter int location;
    private final @Getter int size;
    private final @Getter String shaderVar;

    Attribute(int location, int size, String shaderVar) {
        this.location = location;
        this.size = size;
        this.shaderVar = shaderVar;
    }

}
