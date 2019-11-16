package com.lisp.game.Impl;

import com.lisp.engine.Object.Cube;
import com.lisp.engine.Object.Plane;
import com.lisp.engine.Object.Pyramid;
import com.lisp.engine.base.domain.*;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.physics.Transform;
import com.lisp.engine.render.domain.*;
import com.lisp.engine.render.domain.shader.BasicShader;
import com.lisp.engine.render.domain.shader.PhoneShader;
import com.lisp.engine.util.RenderUtil;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private Material material;


	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f));
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f));

	public Game() {
//		mesh = ResourceLoader.loadMesh("box.obj");
 		mesh = new Mesh();
		/*
		* 材质 亮度与贴图
		* */
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1,1,1),1,8);
		shader = PhoneShader.getInstance();
		camera = new Camera();

		Cube cube = new Cube();
		mesh = cube.getMesh();




//		mesh = ResourceLoader.loadMesh("DoomhammerObj.obj");
		
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
		transform = new Transform();
		PhoneShader.setAmbientLight(new Vector3f(1f,1f,1f));
		PhoneShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));

//		PhoneShader.setPointLight(new PointLight[]{pLight1, pLight2});
	}

	public void input() {
		
		camera.input();
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();

		float sinTemp = (float) Math.sin(temp);

		transform.setTranslation(0, -1, 5);
//		transform.setRotation(0,sinTemp * 180, 0);
//		transform.setScale(0.7f*sinTemp, 0.7f*sinTemp, 0.7f*sinTemp);

		pLight1.setPosition(new Vector3f(3,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
		pLight2.setPosition(new Vector3f(7,0,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));

	}

	public void render() {
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
