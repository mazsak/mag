package pl.mazsak.mag;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.nio.FloatBuffer;

public class MainWindow extends JFrame implements GLEventListener {

    public MainWindow(int width, int height, String title) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        final GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(this);
        glcanvas.setSize(width, height);

        final FPSAnimator animator = new FPSAnimator(glcanvas, 400, true);
        animator.start();


        setTitle(title);
        getContentPane().add(glcanvas);
        setSize(getContentPane().getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glBegin(GL2.GL_TRIANGLES);

        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2d(0, 0.5);
        gl.glVertex2d(-0.5, -0.5);
        gl.glVertex2d(0.5, -0.5);

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }
}
