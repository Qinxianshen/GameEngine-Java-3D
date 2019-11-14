package com.lisp.engine.base.domain;

/* author:Qinzijing
 *  date: 2019/11/14
 *  description:2维向量
 */
public class Vector2f {
    private float x;
    private float y;

    /*
     * 构造函数
     * */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    // Getter Setter
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /*
    * 求模 向量长度
    * */
    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }
    /*
    * 向量点积
    * */
    public float dot(Vector2f r) {
        return x * r.getX() + y * r.getY();
    }
    /*
    * 向量加法
    * */
    public Vector2f add(Vector2f r)
    {
        return new Vector2f(x + r.getX(), y + r.getY());
    }

    public Vector2f add(float r)
    {
        return new Vector2f(x + r, y + r);
    }
    /*
     * 向量减法
     * */
    public Vector2f sub(Vector2f r)
    {
        return new Vector2f(x - r.getX(), y - r.getY());
    }

    public Vector2f sub(float r)
    {
        return new Vector2f(x - r, y - r);
    }
    /*
     * 向量乘法
     * */
    public Vector2f mul(Vector2f r)
    {
        return new Vector2f(x * r.getX(), y * r.getY());
    }

    public Vector2f mul(float r)
    {
        return new Vector2f(x * r, y * r);
    }
    /*
    * 向量除法
    * */
    public Vector2f div(Vector2f r)
    {
        return new Vector2f(x / r.getX(), y / r.getY());
    }

    public Vector2f div(float r)
    {
        return new Vector2f(x / r, y / r);
    }

    /*
    * 向量单位化
    * */
    public Vector2f normalize() {
        float length = length();

        x /= length;
        y /= length;

        return this;
    }

    /*
    * 向量旋转
    * */
    public Vector2f rotate(float angle) {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2f((float)(x * cos - y * sin),(float)(x * sin + y * cos));
    }


}
