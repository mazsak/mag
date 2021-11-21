package pl.mazsak.mag;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import pl.mazsak.mag.renderer.Renderer;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(int width, int height, String title) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(new Renderer());
        glcanvas.setSize(width, height);


        setTitle(title);
        getContentPane().add(glcanvas);
        setSize(getContentPane().getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
