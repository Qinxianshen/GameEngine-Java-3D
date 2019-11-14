package com.lisp.engine.base.domain;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

/* author:Qinzijing
*  date: 2019/11/14
*  description:输入类
*/
public class Input {
    /*
    * 键盘与鼠标的状态数
    * */
    public static final int NUM_KEYCODES = 256;
    public static final int NUM_MOUSEBUTTONS = 5;

    /*
    * 上次的按键与鼠标
    * */
    private static ArrayList<Integer> lastKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> lastMouse = new ArrayList<Integer>();



    public static void update(){
        lastKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++)
            if(getKey(i))
                lastKeys.add(i);

        lastMouse.clear();

        for(int i = 0; i < NUM_MOUSEBUTTONS; i++)
            if(getMouse(i))
                lastMouse.add(i);
    }
    /*
    * 创建键盘与鼠标的抬起与按下的判断
    * */
    public static boolean getKey(int keyCode)
    {
        return Keyboard.isKeyDown(keyCode);
    }

    public static boolean getKeyDown(int keyCode)
    {
        return getKey(keyCode) && !lastKeys.contains(keyCode);
    }

    public static boolean getKeyUp(int keyCode)
    {
        return !getKey(keyCode) && lastKeys.contains(keyCode);
    }

    public static boolean getMouse(int mouseButton)
    {
        return Mouse.isButtonDown(mouseButton);
    }

    public static boolean getMouseDown(int mouseButton)
    {
        return getMouse(mouseButton) && !lastMouse.contains(mouseButton);
    }

    public static boolean getMouseUp(int mouseButton)
    {
        return !getMouse(mouseButton) && lastMouse.contains(mouseButton);
    }
    /*
    * 获取鼠标当前位置
    * */
    public static Vector2f getMousePosition()
    {
        return new Vector2f(Mouse.getX(), Mouse.getY());
    }


}
