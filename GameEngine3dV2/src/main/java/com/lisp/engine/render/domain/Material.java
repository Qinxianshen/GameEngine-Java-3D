package com.lisp.engine.render.domain;

import com.lisp.engine.base.domain.Vector3f;

/* author:Qinzijing
*  date: 2019/11/16
*  description:材质 为之后加光线纹理做准备
*/
public class Material {
    private Texture texture;
    private Vector3f color;
    public Material(Texture texture, Vector3f color) {
        super();
        this.texture = texture;
        this.color = color;
    }
    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Vector3f getColor() {
        return color;
    }
    public void setColor(Vector3f color) {
        this.color = color;
    }
}
