package pl.mazsak.mag;

import com.jogamp.opengl.GL2;
import pl.mazsak.mag.shader.Shader;

public interface Render {

    void render(GL2 gl2, Shader shader);
}
