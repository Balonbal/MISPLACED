package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public abstract class InteractibleObject {

    private Texture texture;
    private Vector3 position;

    protected InteractibleObject(Texture texture, int x, int y) {
        this.texture = texture;
        this.position = new Vector3(x, y, 0);
    }

    public abstract void onInteract();

    public Texture getTexture() {
        return texture;
    }

    public Vector3 getPosition() {
        return position;
    }
}
