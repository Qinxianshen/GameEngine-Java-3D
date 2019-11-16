package com.lisp.engine.util;


import com.lisp.engine.base.domain.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

/* author:Qinzijing
*  date: 2019/11/14
*  description:渲染工具 完成清屏初始化的工作
*/
public class RenderUtil {
    /*
    * 清屏
    * */
    public static void clearScreen()
    {
        //TODO: Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    /*
    * 初始化界面
    * */
    public static void initGraphics()
    {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        //TODO: Depth clamp for later

        glEnable(GL_FRAMEBUFFER_SRGB);
    }
    /*
    * 打印版本号
    * */
    public static String getOpenGLVersion()
    {
        return glGetString(GL_VERSION);
    }

    /*
    * 是否允许贴图加载
    * */
    public static void setTextures(boolean enabled)
    {
        if(enabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    public static void unbindTextures()
    {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static void setClearColor(Vector3f color)
    {
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }

}
