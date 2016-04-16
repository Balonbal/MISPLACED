package sexy.sly.misplaced.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import sexy.sly.misplaced.UI.HUD;
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
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private HUD hud;
    private float d;

    protected PlayState(GameStateManager manager) {
        super(manager);

        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

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

        map = new TmxMapLoader().load("tilemaps/beachmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        player = new Player(50, 50, map);

        hud = new HUD(player);
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
            player.interact();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

        player.update(deltaTime);

        crashedPlane.update(deltaTime);
        effect.update(deltaTime);
        effect.start();

        //Move camera
        camera.position.set(player.getPosition());
        camera.update();

        hud.update(deltaTime);
        hud.act(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(new int[] { 0 });

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);


        label.setPosition(crashedPlane.getPosition().x + (crashedPlane.getTexture().getWidth() / 2), crashedPlane.getPosition().y + crashedPlane.getTexture().getHeight() / 2);
        label.draw(batch, 1);
        if (player.currentAction != null) {
            actionLabel.setText(player.currentAction.getText());
            actionLabel.setPosition(player.getPosition().x - 10, player.getPosition().y + player.getTexture().getHeight() + 15);
            actionLabel.draw(batch, 1);
        }
        batch.end();

        hud.render();

        //Render foreground on top
        tiledMapRenderer.render(new int[] { 1 });

        //Render HUD topmost
        if (player.currentAction != null) drawProgressBar(new Vector3(player.getPosition().x + player.getTexture().getWidth() / 2, player.getPosition().y + player.getTexture().getHeight() + 5, 0),
                player.getTexture().getWidth()*2, 5, player.currentAction.getProgress());


    }

    @Override
    public void dispose() {

    }

    public void drawProgressBar(Vector3 position, int width, int height, float progress) {
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
        shapeRenderer.rect(startX + padding, startY + padding, (width - padding * 2) * progress, height - padding*2); //Draw progress
        shapeRenderer.end();
    }
}
