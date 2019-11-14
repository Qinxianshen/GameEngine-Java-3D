package com.lisp.engine.base.domain;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/* author:Qinzijing
*  date: 2019/11/10
*  description:窗口实体类
*/
public class Window {

    /*
    * 窗体类创建
    * */
    public static void createWindows(int width,int height,String title){
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
    * 窗体更新
    * */
    public static void render(){
        Display.update();
    }
    /*
    * 窗体销毁
    * */
    public static void dispose(){
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    /*
    * 判断是否选择关闭窗口
    * */
    public static boolean isCloseRequested(){
        return Display.isCloseRequested();
    }
    /*
    * 获取窗体的名字 长宽 标题
    * */
    public static int getHeight(){
        return Display.getDesktopDisplayMode().getHeight();
    }
    public static int getWidth(){
        return Display.getDesktopDisplayMode().getWidth();
    }
    public static String getTitle(){
        return Display.getTitle();
    }
}
