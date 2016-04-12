package sexy.sly.misplaced.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private ShapeRenderer shapeRenderer;
    private Label actionLabel;
    private ParticleEffect effect;

    protected PlayState(GameStateManager manager) {
        super(manager);

        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        player = new Player(50, 50);
        crashedPlane = new CrashedPlane(150, 150);

        font = new BitmapFont(Gdx.files.internal("rexliarg.fnt"));
        labelStyle = new Label.LabelStyle(font, Color.LIGHT_GRAY);
        label = new Label("Wreckage", labelStyle);
        actionLabel = new Label("", labelStyle);
        actionLabel.setFontScale(.5f);
        label.setFontScale(.75f);

        shapeRenderer = new ShapeRenderer();
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("Fire"), Gdx.files.internal("data"));
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            //Player is nearby
            if (player.getPosition().x >= crashedPlane.getPosition().x -10 && player.getPosition().x <= crashedPlane.getPosition().x + crashedPlane.getTexture().getWidth() + 10
                    && player.getPosition().y >= crashedPlane.getPosition().y -10 && player.getPosition().y <= crashedPlane.getPosition().y + crashedPlane.getTexture().getHeight() + 10) {
                crashedPlane.onInteract();
            }
        }

        if (crashedPlane.isColliding(player.getPosition())) {
            //TODO move player away
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        player.update(deltaTime);
        crashedPlane.update(deltaTime);
        effect.update(deltaTime);
        effect.start();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(crashedPlane.getTexture(), crashedPlane.getPosition().x, crashedPlane.getPosition().y);
        effect.setPosition(crashedPlane.getPosition().x + 20, crashedPlane.getPosition().y + 10);
        effect.draw(batch);
        batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        label.setPosition(crashedPlane.getPosition().x + (crashedPlane.getTexture().getWidth() / 2), crashedPlane.getPosition().y + crashedPlane.getTexture().getHeight() / 2);
        label.draw(batch, 1);
        if (crashedPlane.getProgress() > 0) {
            actionLabel.setText("Looting...");
            actionLabel.setPosition(player.getPosition().x - 10, player.getPosition().y + player.getTexture().getHeight() + 15);
            actionLabel.draw(batch, 1);
        }
        batch.end();
        if (crashedPlane.getProgress() > 0) drawProgressBar(new Vector3(player.getPosition().x + player.getTexture().getWidth() / 2, player.getPosition().y + player.getTexture().getHeight() + 5, 0),
                player.getTexture().getWidth()*2, 5, crashedPlane.getProgress());
    }

    @Override
    public void dispose() {

    }

    private void drawProgressBar(Vector3 position, int width, int height, float progress) {
        float startX = position.x - width/2;
        float startY = position.y - height/2;
        float border = 1;
        float padding = 1;

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(startX - border, startY - border, width + border*2, height + border*2); //Draw border
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(startX, startY, width, height); //Draw background
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(startX + padding, startY + padding, (width - padding * 2) * progress/100, height - padding*2); //Draw progress
        shapeRenderer.end();
    }
}
