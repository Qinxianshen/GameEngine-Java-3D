package com.base;

import com.base.engine.domain.Time;
import com.base.engine.domain.Window;
import com.base.game.Impl.Game;

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
    private Game game;

    /*
    * 构造函数
    * */
    public MainComponent() {
        isRunning = false;
        game = new Game();
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

        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (isRunning){

            boolean render = false;

            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime/(double)Time.SECOND;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime){
                render = true;

                unprocessedTime -= frameTime;

                if(Window.isCloseRequested())
                    stop();

                Time.setDelta(frameTime);

                /*
                 * 游戏逻辑更新
                 * */
                game.update();

                if(frameCounter >= Time.SECOND){
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
                if (render){
                    render();
                    frames++;
                }else {
                    try {
                        Thread.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

        }
        destroy();
    }
    /*
    * 渲染
    * */
    private void render(){
        game.render();
        Window.render();
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
