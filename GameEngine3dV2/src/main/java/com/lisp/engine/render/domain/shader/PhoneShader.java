package com.lisp.engine.render.domain.shader;

import com.lisp.engine.base.domain.Matrix4f;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.physics.Transform;
import com.lisp.engine.render.domain.*;
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

    private static final int MAX_POINT_LIGHTS = 4;
    private static PointLight[] pointLights = new PointLight[] {};

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

        for(int i = 0; i < MAX_POINT_LIGHTS; i++)
        {
            addUniform("pointLights[" + i + "].base.color");
            addUniform("pointLights[" + i + "].base.intensity");
            addUniform("pointLights[" + i + "].attenuation.constant");
            addUniform("pointLights[" + i + "].attenuation.linear");
            addUniform("pointLights[" + i + "].attenuation.exponent");
            addUniform("pointLights[" + i + "].position");
        }
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

        for(int i = 0; i < pointLights.length; i++)
            setUniform("pointLights[" + i + "]", pointLights[i]);


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

    public void setUniform(String uniformName, PointLight pointLight)
    {
        setUniform(uniformName + ".base", pointLight.getBaseLight());
        setUniformf(uniformName + ".attenuation.constant", pointLight.getAttenuation().getConstant());
        setUniformf(uniformName + ".attenuation.linear", pointLight.getAttenuation().getLinear());
        setUniformf(uniformName + ".attenuation.exponent", pointLight.getAttenuation().getExponent());
        setUniform(uniformName + ".position", pointLight.getPosition());
    }
    public static void setPointLight(PointLight[] pointLights)
    {
        if(pointLights.length > MAX_POINT_LIGHTS)
        {
            System.err.println("Error: You passed in too many point lights. Max allowed is " + MAX_POINT_LIGHTS + ", you passed in " + pointLights.length);
            new Exception().printStackTrace();
            System.exit(1);
        }

        PhoneShader.pointLights = pointLights;
    }
}
