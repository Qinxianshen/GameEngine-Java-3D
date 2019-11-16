package com.lisp.game.Impl;

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

	public Game() {
//		mesh = ResourceLoader.loadMesh("box.obj");
 		mesh = new Mesh();
		/*
		* 材质 亮度与贴图
		* */
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1,1,1));
		shader = PhoneShader.getInstance();
		camera = new Camera();

		//网格类
		Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),		new Vector2f(0.5f, 0.0f)),
				new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),		new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(0.0f, 1.0f, 0.0f),      new Vector2f(0.5f, 1.0f)) };

		int indices[] = { 0, 3, 1,
				1, 3, 2,
				2, 3, 0,
				1, 2, 0 };

		mesh.addVertices(vertices, indices,true);

//		mesh = ResourceLoader.loadMesh("DoomhammerObj.obj");
		
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
		transform = new Transform();
		PhoneShader.setAmbientLight(new Vector3f(1f,1f,1f));
		PhoneShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
	}

	public void input() {
		
		camera.input();
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();

		float sinTemp = (float) Math.sin(temp);

		transform.setTranslation(0, 0, 5);
		transform.setRotation(0,sinTemp * 180, 0);

	}

	public void render() {
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
