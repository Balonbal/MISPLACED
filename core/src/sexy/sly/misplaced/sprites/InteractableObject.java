package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public abstract class InteractableObject {

    private Texture texture;
    private Vector3 position;

    protected InteractableObject(Texture texture, int x, int y) {
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
