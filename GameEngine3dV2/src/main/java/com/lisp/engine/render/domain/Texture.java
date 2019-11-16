package com.lisp.engine.render.domain;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

/* author:Qinzijing
*  date: 2019/11/16
*  description:纹理贴图类
*/
public class Texture {
    private int id;

    public Texture(int id) {
        this.id = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getID() {
        return id;
    }
}
