package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player extends Sprite {
    private Vector3 position;
    private Vector3 velocity;
    private Vector3 maxSpeed;
    private TiledMap map;
    private MapObjects collisions;

    public Texture getTexture() {
        return texture;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    private Texture texture;

    public Player(int x, int y, TiledMap map) {
        super(new Texture(Gdx.files.internal("Player.png")));
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        maxSpeed = new Vector3(50, 50, 0);
        texture = new Texture(Gdx.files.internal("Player.png"));

        this.map = map;
        collisions = map.getLayers().get("Collision").getObjects();
    }

    public void update(float dt) {
        velocity.scl(dt);

        for (RectangleMapObject object: collisions.getByType(RectangleMapObject.class)) {
            if (velocity.x != 0) {
                //Check if new x position overlaps
                if (Intersector.overlaps(object.getRectangle(), new Rectangle(getPosition().x + velocity.x, getPosition().y, getWidth(), getHeight()))) {
                    velocity.x = 0; //Stop movement
                }
            }
            if (velocity.y != 0) {
                //Check if new y position overlaps
                if (Intersector.overlaps(object.getRectangle(), new Rectangle(getPosition().x, getPosition().y + velocity.y, getWidth(), getHeight()))) {
                    velocity.y = 0; //Stop movement
                }
            }
        }


        position.add(velocity);
        velocity.scl(1/dt);
    }

    public Vector3 getMaxSpeed() {
        return maxSpeed;
    }
}
