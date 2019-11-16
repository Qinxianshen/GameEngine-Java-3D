package com.lisp.engine.render.domain;



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
    public void addVertices(Vertex[] vertexs,int[] indices)
    {
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

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size,GL_UNSIGNED_INT,0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

    }

}
