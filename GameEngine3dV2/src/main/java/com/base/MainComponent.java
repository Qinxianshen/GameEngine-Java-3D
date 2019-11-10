package com.base;

import com.base.engine.domain.Window;

/* author:Qinzijing
*  date: 2019/11/10
*  description:引擎的入口类
*
*/
public class MainComponent {

    /*
    * 窗体长宽 标题 与祯时间
    * */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Cyber";
    public static final double FRAME_CAP = 5000.0;

    /*
    * 判断引擎是否还在运行
    * */
    private boolean isRunning;

    /*
    * 构造函数
    * */
    public MainComponent() {
        isRunning = false;
    }

    /*
    * 引擎生命周期:开始
    * */
    public void start(){

        if (isRunning)
            return;
        run();

    }
    /*
     * 引擎生命周期:停止
     * */
    public void stop(){

        if(!isRunning)
            return;
        isRunning = false;
    }

    /*
    * 运行
    * */
    private void run() {
        isRunning = true;

        while (isRunning){
            if(Window.isCloseRequested())
                stop();

        }
        destroy();
    }
    /*
    * 渲染
    * */
    private void render(){

    }
    /*
    * 销毁
    * */
    private void destroy(){
        Window.dispose();
    }
    /*
    * 入口
    * */
    public static void main(String[] args) {
        Window.createWindows(WIDTH,HEIGHT,TITLE);
        MainComponent engine = new MainComponent();
        engine.start();
    }



}
