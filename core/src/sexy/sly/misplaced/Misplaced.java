package sexy.sly.misplaced;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sexy.sly.misplaced.managers.GameStateManager;
import sexy.sly.misplaced.states.MenuState;

public class Misplaced extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager stateManager;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        stateManager = new GameStateManager();
        Gdx.gl.glClearColor(.1f, .1f, .1f, .7f);
        stateManager.push(new MenuState(stateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		stateManager.update(Gdx.graphics.getDeltaTime());
        stateManager.render(batch);
	}


}
