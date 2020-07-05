package fr.leroideskiwis.piscine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.leroideskiwis.piscine.utils.ThermicalTransfer;

public class Main extends ApplicationAdapter {
	private ShapeRenderer shapeRenderer;
	private Piscine piscine;
	private float delta;
	
	@Override
	public void create () {
		this.shapeRenderer = new ShapeRenderer();
		this.piscine = new Piscine(50);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//if(delta > 0.001){
			delta = 0;
			piscine.tick();
		//}
		delta += Gdx.graphics.getDeltaTime();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		piscine.render(shapeRenderer);
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
