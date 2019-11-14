package com.lisp.engine.render.domain;

import com.lisp.engine.base.domain.Vector3f;

/* author:Qinzijing
*  date: 2019/11/14
*  description:顶点类
*/
public class Vertex {
    public static final int SIZE = 3;

    private Vector3f pos;

    public Vertex(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }
}
