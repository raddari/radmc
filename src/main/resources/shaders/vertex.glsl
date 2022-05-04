#version 460 core

layout (location = 0) in vec3 a_position;
layout (location = 1) in vec4 a_colour;

out vec4 f_colour;

void main() {
  gl_Position = vec4(a_position, 1.0);
  f_colour = a_colour;
}
