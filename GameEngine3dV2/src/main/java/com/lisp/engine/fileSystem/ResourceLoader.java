package com.lisp.engine.fileSystem;


import java.io.BufferedReader;
import java.io.FileReader;

/* author:Qinzijing
*  date: 2019/11/14
*  description:资源管理器
*/
public class ResourceLoader {
    public static String loadShader(String fileName)
    {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;

        try
        {
            shaderReader = new BufferedReader(new FileReader("./resources/shaders/" + fileName));
            String line;

            while((line = shaderReader.readLine()) != null)
            {
                shaderSource.append(line).append("\n");
            }

            shaderReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }


        return shaderSource.toString();
    }
}
