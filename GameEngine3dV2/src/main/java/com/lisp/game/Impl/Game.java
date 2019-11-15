package com.lisp.game.Impl;

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
    /*
     * 构造函数
     * */
    public Game() {
        //新建网格类
        mesh = new Mesh();
        shader = new Shader();
        transform = new Transform();
        //添加点
        Vertex[] data = new Vertex[] { new Vertex(new Vector3f(-1, -1, 0)), new Vertex(new Vector3f(0, 1, 0)),
                new Vertex(new Vector3f(1, -1, 0)) };

        mesh.addVertices(data);

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
        if(Input.getKeyDown(Input.KEY_UP))
            System.out.println("Up键已经被按下");
        if (Input.getKeyUp(Input.KEY_UP)){
            System.out.println("Up建已经被抬起");
        }
        if(Input.getMouseDown(1)) {
            System.out.println("右键被按下");
        }
        if(Input.getMouseUp(1)){
            System.out.println("右键被抬起");
        }
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

        transform.setTranslation(sinTemp, 0, 0);
        transform.setRotation(0, 0, sinTemp * 180);
        transform.setScale(sinTemp, sinTemp, sinTemp);

        /*
        * 将变化加入着色器 令他生效
        * */
        shader.setUniform("transform", transform.getTransformation());

    }

    /*
     * 渲染
     * */
    public void render() {
        shader.bind();
        mesh.draw();

    }

}
