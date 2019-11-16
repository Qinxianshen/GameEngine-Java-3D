package com.lisp.engine.render.domain.shader;

import com.lisp.engine.base.domain.Matrix4f;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.physics.Transform;
import com.lisp.engine.render.domain.BaseLight;
import com.lisp.engine.render.domain.DirectionalLight;
import com.lisp.engine.render.domain.Material;
import com.lisp.engine.render.domain.Shader;
import com.lisp.engine.util.RenderUtil;

/* author:Qinzijing
*  date: 2019/11/16
*  description:Phong模型
*/
public class PhoneShader extends Shader{
    private static final PhoneShader instance = new PhoneShader();

    public static PhoneShader getInstance()
    {
        return instance;
    }

    private static Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(0,0,0), 0), new Vector3f(0,0,0));


    private PhoneShader()
    {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex1.vs"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment1.fs"));
        compileShader();

        addUniform("transform");
        addUniform("transformProjected");
        addUniform("baseColor");
        addUniform("ambientLight");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");


        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
    {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
        setUniform("directionalLight", directionalLight);

        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());

        setUniform("eyePos", Transform.getCamera().getPos());
    }

    public static Vector3f getAmbientLight()
    {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight)
    {
        PhoneShader.ambientLight = ambientLight;
    }
    public static void setDirectionalLight(DirectionalLight directionalLight)
    {
        PhoneShader.directionalLight = directionalLight;
    }

    public void setUniform(String uniformName, BaseLight baseLight)
    {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniform(String uniformName, DirectionalLight directionalLight)
    {
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
}
