package com.lisp.engine.render.domain;



import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.util.Util;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/* author:Qinzijing
*  date: 2019/11/14
*  description:网格类 材质渲染
*/
public class Mesh {
    /*
    * Vertices 顶点的数据
    * */
    private int vbo;
    /*
    * Indices 顶点的连接顺序
    * */
    private int ibo;

    private int size;

    public Mesh()
    {
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }
    /*
    *
    * 默认是不加入法向量
    * */
    public void addVertices(Vertex[] vertices, int[] indices)
    {
        addVertices(vertices, indices, false);
    }
    /*
    * 只是加入点 2维
    * */
    public void addVertices(Vertex[] vertexs)
    {
        size = vertexs.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertexs), GL_STATIC_DRAW);
    }
    /*
    * 加入点 与点的连接顺序 3维
    * */
    public void addVertices(Vertex[] vertexs,int[] indices,boolean calcNormals)
    {

        if(calcNormals)
        {
            calcNormals(vertexs, indices);
        }
        size = indices.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertexs), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);

    }
    public void addVertices(FloatBuffer vertexs, int[] indices)
    {
        size = indices.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexs, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);

    }
    /*
    * 如果想生成2维平面 配合用void addVertices(Vertex[] vertexs)
    * */
    public void draw2d()
    {
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);


        glDrawArrays(GL_TRIANGLES, 0, size);

        glDisableVertexAttribArray(0);

    }
    /*
    * 默认是draw 3d的数据 配合用void addVertices(Vertex[] vertexs,int[] indices)
    * */
    public void draw()
    {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size,GL_UNSIGNED_INT,0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);

    }

    private void calcNormals(Vertex[] vertices, int[] indices)
    {
        for(int i = 0; i < indices.length; i += 3)
        {
            int i0 = indices[i];
            int i1 = indices[i + 1];
            int i2 = indices[i + 2];

            Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());

            Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());


            Vector3f normal = v1.cross(v2).normalized();

            vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
            vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
            vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
        }

        for(int i = 0; i < vertices.length; i++)
            vertices[i].setNormal(vertices[i].getNormal().normalized());
    }

}
