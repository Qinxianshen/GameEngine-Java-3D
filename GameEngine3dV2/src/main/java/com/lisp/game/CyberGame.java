package com.lisp.game;

/* author:Qinzijing
 *  date: 2019/11/10
 *  description:游戏逻辑的接口
 */
public interface CyberGame {

    /*
     * 开始
     * */

    void start();

    /*
     * 更新
     * */
    void update();

    /*
     * 渲染
     * */
    void render();

    /*
    * 输入
    * */
    void input();

}
