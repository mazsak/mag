package pl.mazsak.mag;

import com.jogamp.opengl.GL2;

import java.io.FileNotFoundException;
import java.io.IOException;

//Class: Shader
//This class should load in shaders from files and compile them.
public class ShaderUtils {
    private ShaderUtils() {

    }

    public static String loadShader(String path) throws IOException {
        StringBuilder shaderSource = new StringBuilder();
        //put the reader into try catch resources
        try (java.io.BufferedReader shaderReader = new java.io.BufferedReader(new java.io.FileReader(path))) {
            //read the file line by line
            String line;
            while ((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        }
        return shaderSource.toString();
    }

    //Create and compile a shader in the shader program
    //As parameter, pass gl2, program id, the shader code and shader type
    //Make the method throw an exception
    public static int createShader(GL2 gl, int program, String shaderCode, int shaderType) throws FileNotFoundException {
        int shader = gl.glCreateShader(shaderType);
        gl.glShaderSource(shader, 1, new String[]{shaderCode}, null);
        gl.glCompileShader(shader);
        gl.glAttachShader(program, shader);
        return shader;
    }

    //Method that links the shaders within created shader program.
    //As parameter, pass gl2, program id
    //Make the method throw an exception
    public static void linkProgram(GL2 gl, int program) throws FileNotFoundException {
        gl.glLinkProgram(program);
        //validate the program
        gl.glValidateProgram(program);
    }
}
