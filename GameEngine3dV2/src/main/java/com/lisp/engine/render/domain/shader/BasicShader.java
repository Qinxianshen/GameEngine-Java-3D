package com.lisp.engine.render.domain.shader;

import com.lisp.engine.base.domain.Matrix4f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.render.domain.Material;
import com.lisp.engine.render.domain.Shader;
import com.lisp.engine.util.RenderUtil;


/*
* 基本的着色器 可以改变运动与颜色的深浅
* */
public class BasicShader extends Shader {

    private static final BasicShader instance = new BasicShader();

    public static BasicShader getInstance()
    {
        return instance;
    }

    private BasicShader()
    {
        super();

        addVertexShader(ResourceLoader.loadShader("basicVertex5.vs"));
        addFragmentShader(ResourceLoader.loadShader("basicFragment5.fs"));
        compileShader();

        addUniform("transform");
        addUniform("color");
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }

}