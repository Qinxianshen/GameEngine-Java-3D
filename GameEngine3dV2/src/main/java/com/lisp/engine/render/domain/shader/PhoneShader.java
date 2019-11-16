package com.lisp.engine.render.domain.shader;

import com.lisp.engine.base.domain.Matrix4f;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.render.domain.Material;
import com.lisp.engine.render.domain.Shader;
import com.lisp.engine.util.RenderUtil;

public class PhoneShader extends Shader{
    private static final PhoneShader instance = new PhoneShader();

    public static PhoneShader getInstance()
    {
        return instance;
    }

    private static Vector3f ambientLight;

    private PhoneShader()
    {
        super();

        addVertexShader(ResourceLoader.loadShader("phoneVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("phoneFragment.fs"));
        compileShader();

        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
    }

    public static Vector3f getAmbientLight()
    {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight)
    {
        PhoneShader.ambientLight = ambientLight;
    }
}
