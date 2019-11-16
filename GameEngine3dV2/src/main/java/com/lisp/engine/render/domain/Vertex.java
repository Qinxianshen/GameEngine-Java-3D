package com.lisp.engine.render.domain;

import com.lisp.engine.base.domain.Vector2f;
import com.lisp.engine.base.domain.Vector3f;

/* author:Qinzijing
*  date: 2019/11/14
*  description:顶点类
*/
public class Vertex {
    /*
    * pos 3 xyz textCoord 2 xy 法向量3
    * */
    public static final int SIZE = 8;

    private Vector3f pos;
    private Vector2f texCoord;
    private Vector3f normal;//法向量

    public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal) {
        this.pos = pos;
        this.texCoord = texCoord;
        this.normal = normal;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }

    /*
    * 构造函数
    * */
    public Vertex(Vector3f pos)
    {
        this(pos, new Vector2f(0,0));
    }

    public Vertex(Vector3f pos, Vector2f texCoord) {
        this(pos, texCoord, new Vector3f(0,0,0));
    }

    /*
    * getter setter
    * */
    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector2f getTexCoord() {
        return texCoord;
    }

    public void setTexCoord(Vector2f texCoord) {
        this.texCoord = texCoord;
    }

}
