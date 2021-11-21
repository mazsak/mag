package pl.mazsak.mag.shader;

import com.jogamp.opengl.GL2;
import pl.mazsak.mag.utils.ShaderUtils;

import java.io.IOException;

public class Shader {
    private int program;
    private int vertexShader;
    private int fragmentShader;
    private int inPositionLocation;
    private int inColorLocation;
    private int pvMatrixLocation;

    public Shader(GL2 gl2, String vertexShaderPath, String fragmentShaderPath) throws IOException {
        String vertexShaderCode = ShaderUtils.loadShader(vertexShaderPath);
        String fragmentShaderCode = ShaderUtils.loadShader(fragmentShaderPath);
        program = gl2.glCreateProgram();
        if (!vertexShaderCode.equals("") && !fragmentShaderCode.equals("")) {
            vertexShader = ShaderUtils.createShader(gl2, program, vertexShaderCode, GL2.GL_VERTEX_SHADER);
            fragmentShader = ShaderUtils.createShader(gl2, program, fragmentShaderCode, GL2.GL_FRAGMENT_SHADER);
            ShaderUtils.linkProgram(gl2, program);
        }
    }

    public void destroy(GL2 gl2) {
        gl2.glDeleteProgram(program);
        gl2.glDeleteShader(vertexShader);
        gl2.glDeleteShader(fragmentShader);
    }

    public int getProgram() {
        return program;
    }

    public int getVertexShader() {
        return vertexShader;
    }

    public int getFragmentShader() {
        return fragmentShader;
    }

    public int getInPositionLocation() {
        return inPositionLocation;
    }

    public int getInColorLocation() {
        return inColorLocation;
    }

    public int getPvMatrixLocation() {
        return pvMatrixLocation;
    }
}
