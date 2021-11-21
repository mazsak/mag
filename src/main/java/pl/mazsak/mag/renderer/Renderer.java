package pl.mazsak.mag.renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import pl.mazsak.mag.Model;
import pl.mazsak.mag.shader.Shader;

import java.io.File;
import java.io.IOException;

public class Renderer implements GLEventListener {

    private Shader shader;
    private Model model;

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        String vertexShaderSource = "shaders/default.vs";
        String fragmentShaderSource = "shaders/default.fs";

        try {
            System.out.println("Wczytywanie shadera");
            shader = new Shader(gl2, vertexShaderSource, fragmentShaderSource);
            System.out.println("Wczytywanie modelu");
            model = new Model(new File("objects/wall.obj"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        GLU glu = GLU.createGLU(gl2);
        glu.gluPerspective(45.0f, 1, 0.1f,100.0f);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();
        gl2.glViewport(0, 0, 1000, 800);
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glDepthMask(true);

        gl2.glClearColor(0f, 0f, 0f, 1);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();
        shader.destroy(gl2);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl2 = drawable.getGL().getGL2();

        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl2.glUseProgram(shader.getProgram());

//        setCamera(gl2, GLU.createGLU(gl2));


        model.render(gl2, shader);

        gl2.glUseProgram(0);
    }

    private void setCamera(GL2 gl2, GLU glu) {
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();
        glu.gluPerspective(50.0, 1.0, 3.0, 7.0);
        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();
        glu.gluLookAt(0,  30, 0,
                0, 0,  0,
                0,  0,  0);

        gl2.glPushMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
//        final GL2 gl = drawable.getGL().getGL2();
//        GLU glu = GLU.createGLU(gl);
//        // get the OpenGL 2 graphics object
//        if(height <=0)
//            height =1;
//        //preventing devided by 0 exception height =1;
//        final float h = (float) width / (float) height;
//        // display area to cover the entire window
//        gl.glViewport(0, 0, width, height);
//        //transforming projection matrix
//        gl.glMatrixMode(GL2.GL_PROJECTION);
//        gl.glLoadIdentity();
//        glu.gluPerspective(45.0f, h, 1.0, 20.0);
//        //transforming model view gl.glLoadIdentity();
//        gl.glMatrixMode(GL2.GL_MODELVIEW);
//        gl.glLoadIdentity();
    }
}
