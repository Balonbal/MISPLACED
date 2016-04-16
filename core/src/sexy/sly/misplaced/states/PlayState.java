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
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private HUD hud;

    protected PlayState(GameStateManager manager) {
        super(manager);

        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        map = new TmxMapLoader().load("tilemaps/beachmap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        player = new Player(360, 200, map);

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

        //Move camera
        camera.position.set(player.getPosition());
        camera.position.add(player.getWidth() / 2, player.getHeight() / 2, 0);
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

        batch.end();

        //Render foreground on top
        tiledMapRenderer.render(new int[] { 1 });

        hud.render();

    }

    @Override
    public void dispose() {

    }
}
