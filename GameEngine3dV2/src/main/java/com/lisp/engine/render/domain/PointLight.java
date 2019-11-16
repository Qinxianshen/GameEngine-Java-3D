package com.lisp.engine.render.domain;

import com.lisp.engine.base.domain.Vector3f;

/* author:Qinzijing
*  date: 2019/11/16
*  description:点光源
*/
public class PointLight {
    private BaseLight baseLight;
    private Attenuation attenuation;
    private Vector3f position;
    public PointLight(BaseLight baseLight, Attenuation attenuation, Vector3f position) {
        this.baseLight = baseLight;
        this.attenuation = attenuation;
        this.position = position;
    }
    public BaseLight getBaseLight() {
        return baseLight;
    }
    public void setBaseLight(BaseLight baseLight) {
        this.baseLight = baseLight;
    }
    public Attenuation getAttenuation() {
        return attenuation;
    }
    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }
    public Vector3f getPosition() {
        return position;
    }
    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
