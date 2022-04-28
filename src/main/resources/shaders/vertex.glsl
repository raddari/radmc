#version 460 core

layout (location = 0) in vec3 a_position;

out vec4 f_colour;

void main() {
  gl_Position = vec4(a_position, 1.0);
  f_colour = vec4(a_position + 0.5, 1.0);
}
