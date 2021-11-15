package pl.mazsak.mag.mesh.load;

import com.sun.javafx.geom.Vec2f;
import com.sun.javafx.geom.Vec3f;
import pl.mazsak.mag.mesh.Mesh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadObj {


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
                        normals.add(new Vec3f(Float.parseFloat(elements[1]), Float.parseFloat(elements[2]),Float.parseFloat(elements[3])));
                    } else if (elements[0].equals("f")) {
                        for(int i = 1; i < elements.length; i++){
                            String[] indexes = elements[i].split("/");
                            vertexIndexes.add(Integer.parseInt(indexes[0]));
                            uvIndexes.add(Integer.parseInt(indexes[1]));
                            normalIndexes.add(Integer.parseInt(indexes[2]));
                        }
                    }
                }
            }
        }

        Mesh mesh = new Mesh();
//        int index = 0;
//        for (int i = 0; i < vertexIndexes.size(); i++) {
//            PackedIndex suspected = {vertexIndices[i], uvIndices[i], normalIndices[i]};
//            auto it = mapIndexs.find(suspected);
//
//            if (it != mapIndexs.end()) {
//                indexs.push_back(it->second);
//            } else {
//                indexs.push_back(index);
//                mapIndexs[suspected] = index;
//                vertices.push_back(temp_vertices[suspected.v - 1]);
//                uvs.push_back(temp_uvs[suspected.u - 1]);
//                normals.push_back(temp_normals[suspected.n - 1]);
//                index++;
//            }
//        }

        return mesh;
    }
}
