package sexy.sly.misplaced.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import sexy.sly.misplaced.managers.GameStateManager;

public class MenuState extends State {

    private Texture playButton;

    public MenuState(GameStateManager manager) {
        super(manager);
        playButton = new Texture(Gdx.files.internal("PlayButton.png"));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            manager.setStates(new PlayState(manager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(playButton, (Gdx.graphics.getWidth() - playButton.getWidth()) / 2, (Gdx.graphics.getHeight() - playButton.getHeight()) / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
