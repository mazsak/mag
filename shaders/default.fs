in vec3 inColor;
in vec3 inPosition;

out vec4 outColor;

void main()
{
    gl_Position = vec4(inPosition, 1.0);
    outColor = inColor;
}