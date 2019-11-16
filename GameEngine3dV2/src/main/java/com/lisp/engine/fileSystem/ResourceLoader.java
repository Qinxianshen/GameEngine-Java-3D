package com.lisp.engine.fileSystem;


import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Texture;
import com.lisp.engine.render.domain.Vertex;
import com.lisp.engine.util.Util;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/* author:Qinzijing
*  date: 2019/11/14
*  description:资源管理器
*/
public class ResourceLoader {
    /*
    * 着色器加载
    * */
    public static String loadShader(String fileName)
    {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;

        try
        {
            shaderReader = new BufferedReader(new FileReader("src/main/resources/shaders/" + fileName));
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



    /*
    * 通过加载obj文件 生成网格类
    * */

    public static Mesh loadMesh(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];

        if(!ext.equals("obj"))
        {
            System.err.println("Error: 该文件不能导入为OBJ文件格式: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        BufferedReader meshReader = null;

        try
        {
            meshReader = new BufferedReader(new FileReader("src/main/resources/models/" + fileName));
            String line;

            while((line = meshReader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v"))
                {
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                            Float.valueOf(tokens[2]),
                            Float.valueOf(tokens[3]))));
                }
                else if(tokens[0].equals("f"))
                {
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);

                    if(tokens.length > 4)
                    {
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }

            meshReader.close();

            Mesh res = new Mesh();
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);

            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);

            res.addVertices(vertexData, Util.toIntArray(indexData));

            return res;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    /*
    * 使用第三方库导入的OBJloader :https://github.com/javagl/Obj
    * */
    public static Mesh loadMeshByObjLoader(String fileName)
    {
        File file = new File("src/main/resources/models/" + fileName);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            Obj obj = ObjUtils.convertToRenderable(
                    ObjReader.read(inputStream));
            int[] indices = ObjData.getFaceVertexIndicesArray(obj);
            FloatBuffer vertices = ObjData.getVertices(obj);

            Mesh res = new Mesh();
            res.addVertices(vertices, indices);

            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;


    }

    /*
    * 纹理贴图加载
    * */
    public static Texture loadTexture(String fileName)
    {
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length - 1];

        try
        {
            int id = TextureLoader.getTexture(ext, new FileInputStream(new File("src/main/resources/texture/" + fileName))).getTextureID();

            return new Texture(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }


}
