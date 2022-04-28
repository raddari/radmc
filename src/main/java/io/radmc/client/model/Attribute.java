package io.radmc.client.model;

import lombok.Getter;

public enum Attribute {

    POSITION(0, 3, "a_position");

    private final @Getter int location;
    private final @Getter int size;
    private final @Getter String shaderVar;

    Attribute(int location, int size, String shaderVar) {
        this.location = location;
        this.size = size;
        this.shaderVar = shaderVar;
    }

}
