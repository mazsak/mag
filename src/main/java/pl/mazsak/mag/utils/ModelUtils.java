package pl.mazsak.mag.utils;

import com.sun.javafx.geom.Vec2f;
import com.sun.javafx.geom.Vec3f;
import pl.mazsak.mag.mesh.Mesh;
import pl.mazsak.mag.mesh.PackedIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelUtils {


    private ModelUtils() {
    }

    public static Mesh loadMesh(File file) throws IOException {
        List<Vec3f> vertices = new ArrayList<>();
        List<Vec2f> uvs = new ArrayList<>();
        List<Vec3f> normals = new ArrayList<>();
        List<Integer> vertexIndexes = new ArrayList<>();
        List<Integer> uvIndexes = new ArrayList<>();
        List<Integer> normalIndexes = new ArrayList<>();
        try (BufferedReader fileMash = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileMash.readLine()) != null) {
                if (line.length() != 0) {
                    String[] elements = line.split(" ");
                    if (elements[0].equals("v")) {
                        vertices.add(new Vec3f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2]), Float.parseFloat(elements[3])));
                    } else if (elements[0].equals("vt")) {
                        uvs.add(new Vec2f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2])));
                    } else if (elements[0].equals("vn")) {
                        normals.add(new Vec3f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2]), Float.parseFloat(elements[3])));
                    } else if (elements[0].equals("f")) {
                        for (int i = 1; i < elements.length; i++) {
                            String[] indexes = elements[i].split("/");
                            vertexIndexes.add(Integer.parseInt(indexes[0]));
                            uvIndexes.add(Integer.parseInt(indexes[1].equals("") ? "0" : indexes[1]));
                            normalIndexes.add(Integer.parseInt(indexes[2]));
                        }
                    }
                }
            }
        }

        Mesh mesh = new Mesh();
        Map<PackedIndex, Integer> mapIndexes = new HashMap<>();
        int index = 0;
        for (int i = 0; i < vertexIndexes.size(); i++) {
            PackedIndex suspected = new PackedIndex(vertexIndexes.get(i), uvIndexes.get(i), normalIndexes.get(i));
            if (mapIndexes.containsKey(suspected)) {
                mesh.addIndex(mapIndexes.get(suspected));
            } else {
                mesh.addIndex(index);
                mapIndexes.put(suspected, index);
                mesh.addVertex(vertices.get(suspected.getV() - 1));
//                mesh.addUv(uvs.get(suspected.getU() - 1));
                mesh.addNormal(normals.get(suspected.getN() - 1));
                index++;
            }
        }

        return mesh;
    }

    public static float[] mapListToArrayFloat(List<Vec3f> list) {
        float[] array = new float[list.size() * 3];
        int index = 0;
        for (Vec3f vec : list) {
            array[index++] = vec.x;
            array[index++] = vec.y;
            array[index++] = vec.z;
        }

        return array;
    }

    public static int[] mapListToArrayInteger(List<Integer> list) {
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

}
