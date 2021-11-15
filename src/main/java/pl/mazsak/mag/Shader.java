package pl.mazsak.mag;

import com.jogamp.opengl.GL2;

import java.io.IOException;

public class Shader {
    // Attributes: program, vertexShader, fragmentShader
    private int program;
    private int vertexShader;
    private int fragmentShader;
    private boolean initialized = false;
    /**
     * Initializes the shader program.
     *
     * @param gl2 context.
     * @param vertexShaderPath file.
     * @param fragmentShaderPath file.
     * @return true if initialization was successful, false otherwise.
     */
    public boolean init(GL2 gl2, String vertexShaderPath, String fragmentShaderPath) throws IOException {
        //try loading the vertex shader code and the fragment shader code using ShaderUtils
        String vertexShaderCode = ShaderUtils.loadShader(vertexShaderPath);
        String fragmentShaderCode = ShaderUtils.loadShader(fragmentShaderPath);
        //create the program using glCreateProgram
        program = gl2.glCreateProgram();
        //if the vertex shader code and the fragment shader code could be loaded
        if (vertexShaderCode != null && fragmentShaderCode != null) {
            //create the vertex shader using ShaderUtils
            vertexShader = ShaderUtils.createShader(gl2, program,  vertexShaderCode, GL2.GL_VERTEX_SHADER);
            //create the fragment shader using ShaderUtils
            fragmentShader = ShaderUtils.createShader(gl2, program, fragmentShaderCode, GL2.GL_FRAGMENT_SHADER);
            //Link the program using shaderutils
            ShaderUtils.linkProgram(gl2, program);
            initialized = true;
        }
        return initialized;
    }

    //Destroy the shader program.
    public void destroy(GL2 gl2) {
        if (initialized) {
            gl2.glDeleteProgram(program);
            gl2.glDeleteShader(vertexShader);
            gl2.glDeleteShader(fragmentShader);
            initialized = false;
        }
    }
}
