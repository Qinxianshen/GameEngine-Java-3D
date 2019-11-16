package com.lisp.engine.Object;

import com.lisp.engine.base.domain.Vector2f;
import com.lisp.engine.base.domain.Vector3f;
import com.lisp.engine.fileSystem.ResourceLoader;
import com.lisp.engine.render.domain.Mesh;
import com.lisp.engine.render.domain.Shader;
import com.lisp.engine.render.domain.Texture;
import com.lisp.engine.render.domain.Vertex;

/* author:Qinzijing
*  date: 2019/11/16
*  description:三棱锥实体类
*/
public class Pyramid {

    private Texture texture;
    private Mesh mesh;
    private Shader shader;

    public Pyramid(Texture texture) {
        this.texture = texture;
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
                new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f,0)),
                new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
                new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f,1.0f))};

        int[] indices = new int[] { 3, 1, 0,
                2, 1, 3,
                0, 1, 2,
                0, 2, 3 };
        mesh = new Mesh();
        mesh.addVertices(vertices, indices);
        shader = new Shader();
        shader.addVertexShader(ResourceLoader.loadShader("simpleTextureVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("simpleTextureFragment.fs"));
        shader.compileShader();
    }

    public Pyramid(Texture texture, Mesh mesh, Shader shader) {
        this.texture = texture;
        this.mesh = mesh;
        this.shader = shader;
        this.shader.compileShader();
    }
    public void render(){
        shader.bind();
        texture.bind();
        mesh.draw();

    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
        this.shader.compileShader();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
