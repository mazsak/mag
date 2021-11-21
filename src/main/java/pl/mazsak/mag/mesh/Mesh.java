package pl.mazsak.mag.mesh;

import com.jogamp.common.nio.Buffers;
import com.sun.javafx.geom.Vec2f;
import com.sun.javafx.geom.Vec3f;
import pl.mazsak.mag.utils.ModelUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private final List<Vec3f> vertices;
    private final List<Vec2f> uvs;
    private final List<Vec3f> normals;
    private final List<Integer> indexes;

    private FloatBuffer vertexBuffer;
    private IntBuffer indexBuffer;
    private FloatBuffer normalBuffer;

    public Mesh() {
        vertices = new ArrayList<>();
        uvs = new ArrayList<>();
        normals = new ArrayList<>();
        indexes = new ArrayList<>();
    }

    public void addVertex(Vec3f vec) {
        vertices.add(vec);
    }

    public void addUv(Vec2f vec) {
        uvs.add(vec);
    }

    public void addNormal(Vec3f vec) {
        normals.add(vec);
    }

    public void addIndex(int index) {
        indexes.add(index);
    }

    public List<Vec3f> getVertices() {
        return vertices;
    }

    public List<Vec2f> getUvs() {
        return uvs;
    }

    public List<Vec3f> getNormals() {
        return normals;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void init() {
        this.vertexBuffer = Buffers.newDirectFloatBuffer(vertices.size() * 3);
        this.indexBuffer = Buffers.newDirectIntBuffer(indexes.size() * 3);
        this.normalBuffer = Buffers.newDirectFloatBuffer(normals.size() * 3);

        this.vertexBuffer.put(ModelUtils.mapListToArrayFloat(vertices));
        this.indexBuffer.put(ModelUtils.mapListToArrayInteger(indexes));
        this.normalBuffer.put(ModelUtils.mapListToArrayFloat(normals));
    }

    public FloatBuffer getVertexBuffer() {
        if (vertexBuffer == null) {
            init();
        }
        return vertexBuffer;
    }

    public IntBuffer getIndexBuffer() {
        if (indexBuffer == null) {
            init();
        }
        return indexBuffer;
    }

    public FloatBuffer getNormalBuffer() {
        if (normalBuffer == null) {
            init();
        }
        return normalBuffer;
    }
}
