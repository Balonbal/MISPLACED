package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.math.Vector3;

public interface CollisionObject {
    boolean isColliding(Vector3 position);
    void onCollision();
}
