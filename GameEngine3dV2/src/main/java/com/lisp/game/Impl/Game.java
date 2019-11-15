package com.lisp.game.Impl;

import com.lisp.engine.base.domain.Camera;
import com.lisp.engine.base.domain.Input;
import com.lisp.engine.base.domain.Time;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.physics.Transform;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Shader;
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
    /*
     * 构造函数
     * */
    public Game() {
        //新建网格类
        //写的原生方法
//        mesh = ResourceLoader.loadMesh("man.obj");
        //封装了第三方库后导入的
        mesh = ResourceLoader.loadMeshByObjLoader("DoomhammerObj.obj");
        shader = new Shader();
        transform = new Transform();
        /*
        * 初始化摄像机
        * */
        camera = new Camera();
        Transform.setCamera(camera);


        /*
        * 为网格类加上材质
        * */

//        shader.addVertexShader(ResourceLoader.loadShader("UniformVertex.vs"));
        shader.addVertexShader(ResourceLoader.loadShader("TransformVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("UniformFragment.fs"));
        shader.compileShader();

        /*
        * 添加均匀的处理方法
        * */
//        shader.addUniform("uniformFloat");

        /*
        * 添加变化的处理方法
        * */
        shader.addUniform("transform");


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
        /*
        * 均匀化 颜色变化
        * */
//        shader.setUniformf("uniformFloat", (float)Math.abs(Math.sin(temp)));
        /*
        * 均匀化 物理变化
        * */
        float sinTemp = (float)Math.sin(temp);

        //x 左右 y 上下 z前后
        transform.setTranslation(sinTemp, 0, 5);
        transform.setRotation(0, sinTemp * 180, 0);

        /*
        * 将变化加入着色器 令他生效
        * */
        shader.setUniform("transform", transform.getProjectedTransformation());

    }

    /*
     * 渲染
     * */
    public void render() {
        shader.bind();
        mesh.draw();

    }

}
