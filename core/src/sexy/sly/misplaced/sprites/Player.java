package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private Vector3 position;
    private Vector3 velocity;
    private Vector3 maxSpeed;

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

    public Player(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        maxSpeed = new Vector3(50, 50, 0);
        texture = new Texture(Gdx.files.internal("Player.png"));
    }

    public void update(float dt) {
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1/dt);
    }

    public Vector3 getMaxSpeed() {
        return maxSpeed;
    }
}
