package pl.mazsak.mag;

import com.jogamp.opengl.GL2;
import com.sun.javafx.geom.Vec3f;
import pl.mazsak.mag.mesh.Mesh;
import pl.mazsak.mag.shader.Shader;
import pl.mazsak.mag.utils.ModelUtils;

import java.io.File;
import java.io.IOException;

public class Model implements Render {

    private final Mesh mesh;

    public Model(File obj) throws IOException {
        this.mesh = ModelUtils.loadMesh(obj);
        this.mesh.init();
    }

    @Override
    public void render(GL2 gl2, Shader shader) {
        gl2.glEnableVertexAttribArray(shader.getInPositionLocation());
        gl2.glEnableVertexAttribArray(shader.getInColorLocation());
//
//        gl2.glBindBuffer(GL2.GL_ARRAY_BUFFER,mesh.getVertexBuffer().remaining());

        gl2.glVertexAttribPointer(shader.getInPositionLocation(), 3, GL2.GL_FLOAT, false, 0, mesh.getVertexBuffer().rewind());
        gl2.glVertexAttribPointer(shader.getInColorLocation(), 3, GL2.GL_FLOAT, false, 0, mesh.getNormalBuffer().rewind());
        gl2.glDrawElements(GL2.GL_TRIANGLES, mesh.getIndexes().size(), GL2.GL_UNSIGNED_INT, mesh.getIndexBuffer().rewind());

        gl2.glDisableVertexAttribArray(shader.getInPositionLocation());
        gl2.glDisableVertexAttribArray(shader.getInColorLocation());

//        gl2.glBegin(GL2.GL_TRIANGLES);
//        gl2.glVertex3f(0.5f,0.5f,0.5f);
//        gl2.glVertex3f(0f,0.5f,0.5f);
//        gl2.glVertex3f(0.5f,0f,0.5f);
//gl2.glVertex3f(1f,0.5f,0.5f);
//        gl2.glVertex3f(1f,0.5f,0f);
//        gl2.glVertex3f(1f,0f,0.5f);
//
//        gl2.glVertex3f(1.1f,0.13f,-1.22f);
//        gl2.glVertex3f(1.1f,-0.13f,-1.22f);
//        gl2.glVertex3f(1.1f,-0.13f,-0.24f);

//        gl2.glVertex3fv(mesh.getVertexBuffer());
//        for (Vec3f vec : mesh.getVertices()) {
//            gl2.glVertex3f(vec.x, vec.y, vec.z);
//        }
//        gl2.glVertex3f(mesh.getVertices().get(0).x, mesh.getVertices().get(0).y, mesh.getVertices().get(0).z);
//        gl2.glVertex3f(mesh.getVertices().get(1).x, mesh.getVertices().get(1).y, mesh.getVertices().get(1).z);
//        gl2.glVertex3f(mesh.getVertices().get(2).x, mesh.getVertices().get(2).y, mesh.getVertices().get(2).z);


//        gl2.glEnd();

    }
}
