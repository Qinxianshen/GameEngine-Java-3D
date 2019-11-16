package com.lisp.engine.render.domain;

import com.lisp.engine.base.domain.Vector3f;

/* author:Qinzijing
*  date: 2019/11/16
*  description:材质 为之后加光线纹理做准备
*/
public class Material {
    private Texture texture;
    private Vector3f color;
    private float specularIntensity;
    private float specularPower;

    public Material(Texture texture)
    {
        this(texture, new Vector3f(1,1,1));
    }
    public Material(Texture texture, Vector3f color) {
        this(texture, color, 2, 32);
    }

    public Material(Texture texture, Vector3f color, float specularIntensity, float specularPower) {
        this.texture = texture;
        this.color = color;
        this.specularIntensity = specularIntensity;
        this.specularPower = specularPower;
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

    public float getSpecularIntensity() {
        return specularIntensity;
    }

    public void setSpecularIntensity(float specularIntensity) {
        this.specularIntensity = specularIntensity;
    }

    public float getSpecularPower() {
        return specularPower;
    }

    public void setSpecularPower(float specularPower) {
        this.specularPower = specularPower;
    }
}
