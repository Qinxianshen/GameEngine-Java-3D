package com.lisp.engine.render.domain;



import com.lisp.engine.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/* author:Qinzijing
*  date: 2019/11/14
*  description:网格类 材质渲染
*/
public class Mesh {
    private int vbo;
    private int size;

    public Mesh()
    {
        vbo = glGenBuffers();
        size = 0;
    }


    public void addVertices(Vertex[] vertexs)
    {
        size = vertexs.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertexs), GL_STATIC_DRAW);
    }

    public void draw()
    {
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);


        glDrawArrays(GL_TRIANGLES, 0, size);

        glDisableVertexAttribArray(0);

    }
}
