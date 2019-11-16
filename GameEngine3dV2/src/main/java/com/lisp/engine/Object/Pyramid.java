package com.lisp.engine.Object;

import com.lisp.engine.base.domain.Camera;
import com.lisp.engine.base.domain.Vector2f;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.physics.Transform;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Shader;
import com.lisp.engine.render.domain.Texture;
import com.lisp.engine.render.domain.Vertex;

/* author:Qinzijing
*  date: 2019/11/16
*  description:三棱锥实体类
*/
public class Pyramid {

    private Mesh mesh;

    public  Pyramid() {
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
                new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f,0)),
                new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
                new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f,1.0f))};

        int[] indices = new int[] { 3, 1, 0,
                2, 1, 3,
                0, 1, 2,
                0, 2, 3 };
        mesh = new Mesh();
        mesh.addVertices(vertices, indices);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
