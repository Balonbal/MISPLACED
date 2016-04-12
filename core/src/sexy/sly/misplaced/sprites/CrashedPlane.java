package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class CrashedPlane extends InteractableObject implements CollisionObject {

    private boolean running = false;
    private float progress;
    private float completionTime = 100;

    public CrashedPlane( int x, int y) {
        super(new Texture(Gdx.files.internal("Plane.png")), x, y);
        progress = 0;
    }

    @Override
    public void onInteract() {
        running = true;
    }

    public void update(float deltaT) {
        if (running) {
            progress += deltaT*100/completionTime;
        }

        if (progress >= 100) {
            progress = 0;
            running = false;
        }
    }

    public float getProgress() {
        return progress;
    }

    @Override
    public boolean isColliding(Vector3 position) {
        return (
                position.x >= this.getPosition().x
                && position.x <= this.getPosition().x + this.getTexture().getWidth()
                && position.y >= this.getPosition().y
                && position.y <= this.getPosition().y + this.getTexture().getHeight()
                );
    }

    @Override
    public void onCollision() {

    }
}
