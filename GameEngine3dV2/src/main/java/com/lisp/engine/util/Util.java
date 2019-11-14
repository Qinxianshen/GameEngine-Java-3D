package com.lisp.engine.util;

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

}
