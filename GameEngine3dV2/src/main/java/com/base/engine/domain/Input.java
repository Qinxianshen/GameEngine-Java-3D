package com.base.engine.domain;


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
    * 当前 键盘按下 当前键盘值 键盘抬起的值
    * */
    private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
    /*
     * 当前 鼠标按下 当前鼠标值 鼠标抬起的值
     * */
    private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> upMouse = new ArrayList<Integer>();

    /*
    * 更新事件
    * */
    public static void update() {
        //是否正在按下鼠标 是则压入队列
        currentMouse.clear();
        for(int i = 0; i < NUM_MOUSEBUTTONS;i++){
            if(getMouse(i)){
                currentMouse.add(i);
            }
        }
        //是否按下鼠标 是则压入队列
        downMouse.clear();
        for (int i = 0;i<NUM_MOUSEBUTTONS;i++){
            if (getMouse(i) && !currentMouse.contains(i)){
                downMouse.add(i);
            }
        }
        //是否已经抬起鼠标 是则压入队列
        upMouse.clear();
        for (int i=0;i<NUM_MOUSEBUTTONS;i++){
            if(!getMouse(i) && currentMouse.contains(i)){
                upMouse.add(i);
            }
        }
        //键盘功能同理
        upKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++)
            if(!getKey(i) && currentKeys.contains(i))
                upKeys.add(i);

        downKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++)
            if(getKey(i) && !currentKeys.contains(i))
                downKeys.add(i);

        currentKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++)
            if(getKey(i))
                currentKeys.add(i);


    }
    /*
    * 获取键盘传来的key值
    * */
    public static boolean getKey(int keyCode){
        return Keyboard.isKeyDown(keyCode);
    }
    /*
    * 获取按下键盘获得的值
    * */
    public static boolean getKeyDown(int keyCode){
        return downKeys.contains(keyCode);
    }
    /*
    * 获取抬起键盘获得的值
    * */
    public static boolean getKeyUp(int keyCode){
        return upKeys.contains(keyCode);
    }
    /*
    * 鼠标事件同理
    * */
    public static boolean getMouse(int mouseButton) {
        return Mouse.isButtonDown(mouseButton);
    }

    public static boolean getMouseDown(int mouseButton) {
        return downMouse.contains(mouseButton);
    }

    public static boolean getMouseUp(int mouseButton) {
        return upMouse.contains(mouseButton);
    }


}
