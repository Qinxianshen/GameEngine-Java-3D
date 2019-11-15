package com.lisp.engine.physics;


import com.lisp.engine.base.domain.Matrix4f;
import com.lisp.engine.base.domain.Vector3f;

/* author:Qinzijing
*  date: 2019/11/15
*  description:引擎部分 添加变换
*/
public class Transform {

    /*
    * 平移
    * */
    private Vector3f translation;

    /*
    * 旋转
    * */
    private Vector3f rotation;

    /*
    * 缩放
    * */
    private Vector3f scale;

    /*
    * 构造函数
    * */
    public Transform() {
        translation = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
        scale = new Vector3f(1, 1, 1);
    }
    /*
    * 获得变换矩阵
    * */
    public Matrix4f getTransformation()
    {
        Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
        return translationMatrix.mul(rotationMatrix).mul(scaleMatrix);
    }

    /*
    * getter setter
    *
    * */
    public Vector3f getTranslation() {
        return translation;
    }
    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public void setTranslation(float x, float y, float z)
    {
        this.translation = new Vector3f(x, y, z);
    }

    public Vector3f getRotation() {
        return rotation;
    }
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
    public void setRotation(float x, float y, float z)
    {
        this.rotation = new Vector3f(x, y, z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    public void setScale(float x, float y, float z)
    {
        this.scale = new Vector3f(x, y, z);
    }


}
