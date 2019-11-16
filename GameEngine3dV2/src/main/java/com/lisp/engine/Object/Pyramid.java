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
        //网格类
        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),		new Vector2f(0.5f, 0.0f)),
                new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),		new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(0.0f, 1.0f, 0.0f),      new Vector2f(0.5f, 1.0f)) };

        int indices[] = { 0, 3, 1,
                1, 3, 2,
                2, 3, 0,
                1, 2, 0 };
        mesh = new Mesh();
        mesh.addVertices(vertices, indices,true);
    }

    public Mesh getMesh() {
        return mesh;
    }

}
