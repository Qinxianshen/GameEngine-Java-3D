package com.lisp.engine.Object;

import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Vertex;

/* author:Qinzijing
*  date: 2019/11/16
*  description:立方块
*/
public class Cube {
    private Mesh mesh;
    private float size;

    public Cube() {
        size = 1.0f;
        Vertex[] vertices = new Vertex[] {
                new Vertex(new Vector3f(-size, -size,  -size)),
                new Vertex(new Vector3f(-size,  size,  -size)),
                new Vertex(new Vector3f( size,  size,  -size)),
                new Vertex(new Vector3f( size, -size,  -size)),
                new Vertex(new Vector3f( size, -size,   size)),
                new Vertex(new Vector3f( size,  size,   size)),
                new Vertex(new Vector3f(-size,   size,  size)),
                new Vertex(new Vector3f(-size, -size,  size))
        };
        int indices[] = {
                0, 1, 3, //   1: face arrière
                0, 2, 3,
                3, 2, 5, //   2: face droite
                3, 5, 4,
                5, 2, 1, //   3: face dessue
                5, 1, 6,
                3, 4, 7, //   4: face dessous
                3, 7, 0,
                0, 7, 6, //   5: face gauche
                0, 6, 1,
                4, 5, 6, //   6: face avant
                4, 6, 7
        };
        mesh = new Mesh();
        mesh.addVertices(vertices,indices,true);
    }

    public Cube(float size_) {
        this.size = size_;
        Vertex[] vertices = new Vertex[] {
                new Vertex(new Vector3f(-size, -size,  -size)),
                new Vertex(new Vector3f(-size,  size,  -size)),
                new Vertex(new Vector3f( size,  size,  -size)),
                new Vertex(new Vector3f( size, -size,  -size)),
                new Vertex(new Vector3f( size, -size,   size)),
                new Vertex(new Vector3f( size,  size,   size)),
                new Vertex(new Vector3f(-size,   size,  size)),
                new Vertex(new Vector3f(-size, -size,  size))
        };
        int indices[] = {
                0, 1, 3, //   1: face arrière
                0, 2, 3,
                3, 2, 5, //   2: face droite
                3, 5, 4,
                5, 2, 1, //   3: face dessue
                5, 1, 6,
                3, 4, 7, //   4: face dessous
                3, 7, 0,
                0, 7, 6, //   5: face gauche
                0, 6, 1,
                4, 5, 6, //   6: face avant
                4, 6, 7
        };
        mesh = new Mesh();
        mesh.addVertices(vertices,indices,true);
    }

    public Mesh getMesh() {
        return mesh;
    }
}
