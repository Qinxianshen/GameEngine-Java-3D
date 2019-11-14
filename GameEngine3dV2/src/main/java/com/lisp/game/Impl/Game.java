package com.lisp.game.Impl;

import com.lisp.engine.base.domain.Input;
import com.lisp.game.CyberGame;
import org.lwjgl.input.Keyboard;

/* author:Qinzijing
 *  date: 2019/11/10
 *  description:游戏类 游戏里的逻辑在里面
 */
public class Game implements CyberGame {


    /*
     * 构造函数
     * */

    public Game() {

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
        if(Input.getKeyDown(Keyboard.KEY_UP))
            System.out.println("Up键已经被按下");
        if (Input.getKeyUp(Keyboard.KEY_UP)){
            System.out.println("Up建已经被抬起");
        }
        if(Input.getMouseDown(1)) {
            System.out.println("右键被按下");
        }
        if(Input.getMouseUp(1)){
            System.out.println("右键被抬起");
        }
    }


    /*
     * 更新
     * */
    public void update() {

    }

    /*
     * 渲染
     * */
    public void render() {


    }

}
