package com.lisp.engine.Object;


import com.lisp.engine.base.domain.Vector2f;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Vertex;

/* author:Qinzijing
*  date: 2019/11/16
*  description:平面类
*/
public class Plane {

    private Mesh mesh;
    private float fieldDepth;
    private float fieldWidth;

    public Plane() {
        mesh = new Mesh();
        fieldDepth = 10.0f;
        fieldWidth = 10.0f;

        Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
                2, 1, 3};

        mesh.addVertices(vertices, indices,true);
    }

    public Plane(float fieldDepth, float fieldWidth) {
        this.fieldDepth = fieldDepth;
        this.fieldWidth = fieldWidth;
        mesh = new Mesh();
        Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

        int indices[] = { 0, 1, 2,
                2, 1, 3};

        mesh.addVertices(vertices, indices,true);
    }

    public float getFieldDepth() {
        return fieldDepth;
    }

    public void setFieldDepth(float fieldDepth) {
        this.fieldDepth = fieldDepth;
    }

    public float getFieldWidth() {
        return fieldWidth;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setFieldWidth(float fieldWidth) {
        this.fieldWidth = fieldWidth;
    }
}
