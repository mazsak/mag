package pl.mazsak.mag.utils;

import com.jogamp.opengl.GL2;

import java.io.IOException;

public class ShaderUtils {
    private ShaderUtils() {

    }

    public static String loadShader(String path) throws IOException {
        StringBuilder shaderSource = new StringBuilder();
        try (java.io.BufferedReader shaderReader = new java.io.BufferedReader(new java.io.FileReader(path))) {
            String line;
            while ((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        }
        return shaderSource.toString();
    }

    public static int createShader(GL2 gl, int program, String shaderCode, int shaderType) {
        int shader = gl.glCreateShader(shaderType);
        gl.glShaderSource(shader, 1, new String[]{shaderCode}, null);
        gl.glCompileShader(shader);
        gl.glAttachShader(program, shader);
        return shader;
    }

    public static void linkProgram(GL2 gl, int program) {
        gl.glLinkProgram(program);
        gl.glValidateProgram(program);
    }
}
