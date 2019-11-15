package com.lisp.engine.render.domain;


import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

/* author:Qinzijing
*  date: 2019/11/15
*  description:着色器
*/
public class Shader {
    private int program;

    /*
    * 构造函数
    * */
    public Shader() {
        program = glCreateProgram();

        if(program == 0)
        {
            System.err.println("着色器创建失败：无法匹配对应的程序");
            System.exit(1);
        }
    }
    /*
    * 绑定程序
    * */
    public void bind()
    {
        glUseProgram(program);
    }
    /*
    * 添加顶点类着色器
    * */
    public void addVertexShader(String text)
    {
        addProgram(text, GL_VERTEX_SHADER);
    }
    /*
    * 添加Geometry着色类
    * */
    public void addGeometryShader(String text)
    {
        addProgram(text,GL_GEOMETRY_SHADER);
    }
    /*
    * 添加片段的着色器
    * */
    public void addFragmentShader(String text)
    {
        addProgram(text, GL_FRAGMENT_SHADER);
    }

    public void compileShader()
    {
        glLinkProgram(program);

        if(glGetProgram(program, GL_LINK_STATUS) == 0)
        {
            System.err.println(glGetProgramInfoLog(program, 1024));
            System.exit(1);
        }

        glValidateProgram(program);

        if(glGetProgram(program, GL_VALIDATE_STATUS) == 0)
        {
            System.err.println(glGetProgramInfoLog(program, 1024));
            System.exit(1);
        }
    }
    /*
    * 添加程序
    * */
    private void addProgram(String text, int type)
    {
        int shader = glCreateShader(type);

        if(shader == 0)
        {
            System.err.println("着色器创建失败：无法匹配对应的程序");
            System.exit(1);
        }

        glShaderSource(shader, text);
        glCompileShader(shader);

        if(glGetShader(shader, GL_COMPILE_STATUS) == 0)
        {
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(1);
        }

        glAttachShader(program, shader);
    }
}
