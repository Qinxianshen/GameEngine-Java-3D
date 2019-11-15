package com.lisp.engine.util;

import com.lisp.engine.base.domain.Matrix4f;
import com.lisp.engine.render.domain.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/* author:Qinzijing
*  date: 2019/11/14
*  description:缓存类的工具
*/
public class Util {

    public static FloatBuffer createFloatBuffer(int size)
    {
        return BufferUtils.createFloatBuffer(size);
    }

    /*
    * 缓存顶点类数据
    * */
    public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
    {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

        for(int i = 0; i < vertices.length; i++)
        {
            buffer.put(vertices[i].getPos().getX());
            buffer.put(vertices[i].getPos().getY());
            buffer.put(vertices[i].getPos().getZ());
        }

        buffer.flip();

        return buffer;
    }
    /*
    * 缓存4维矩阵
    * */
    public static FloatBuffer createFlippedBuffer(Matrix4f value)
    {
        FloatBuffer buffer = createFloatBuffer(4 * 4);

        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                buffer.put(value.get(i, j));

        buffer.flip();

        return buffer;
    }

}
