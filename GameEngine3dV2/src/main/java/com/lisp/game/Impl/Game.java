package com.lisp.game.Impl;

import com.lisp.engine.Object.Pyramid;
import com.lisp.engine.base.domain.*;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.physics.Transform;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Shader;
import com.lisp.engine.render.domain.Texture;
import com.lisp.engine.render.domain.Vertex;
import com.lisp.game.CyberGame;
import org.lwjgl.input.Keyboard;

/* author:Qinzijing
 *  date: 2019/11/10
 *  description:游戏类 游戏里的逻辑在里面
 */
public class Game implements CyberGame {


    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Camera camera;
    private Texture texture;
    private Pyramid pyramid;
    /*
     * 构造函数
     * */
    public Game() {
        transform = new Transform();
        camera = new Camera();
        Transform.setCamera(camera);
        texture = ResourceLoader.loadTexture("test.png");
        pyramid = new Pyramid(texture);
    }

    /*
     * 开始
     * */
    public void start() {
    }

    /*
    * 输入事件
    * */
    public void input(){
        camera.input();
    }

    float temp = 0.0f;
    /*
     * 更新
     * */
    public void update() {

        temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);
        transform.setTranslation(sinTemp, 0, 5);
        transform.setRotation(0, sinTemp * 180, 0);

        pyramid.getShader().setUniform("transform", transform.getProjectedTransformation());

    }

    /*
     * 渲染
     * */
    public void render() {
        pyramid.render();
    }

}
