package sexy.sly.misplaced.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sexy.sly.misplaced.managers.GameStateManager;
import sexy.sly.misplaced.sprites.CrashedPlane;
import sexy.sly.misplaced.sprites.Player;

public class PlayState extends State {
    private Player player;
    private CrashedPlane crashedPlane;
    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private Label label;

    protected PlayState(GameStateManager manager) {
        super(manager);

        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        player = new Player(50, 50);
        crashedPlane = new CrashedPlane(150, 150);

        font = new BitmapFont(Gdx.files.internal("bobcayge.fnt"));
        labelStyle = new Label.LabelStyle(font, Color.LIGHT_GRAY);
        label = new Label("Wreckage", labelStyle);
        label.setFontScale(.5f);
    }

    @Override
    protected void handleInput() {
        Vector3 vel = new Vector3();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vel.add(0, player.getMaxSpeed().y, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vel.add(0, -player.getMaxSpeed().y, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            vel.add(player.getMaxSpeed().x, 0, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            vel.add(-player.getMaxSpeed().x, 0, 0);
        }
        player.getVelocity().set(vel);
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        player.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(crashedPlane.getTexture(), crashedPlane.getPosition().x, crashedPlane.getPosition().y);
        label.setPosition(crashedPlane.getPosition().x + (crashedPlane.getTexture().getWidth() / 2), crashedPlane.getPosition().y + crashedPlane.getTexture().getHeight() / 2);
        label.draw(batch, 1);
        batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
