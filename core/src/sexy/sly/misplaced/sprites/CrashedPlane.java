package sexy.sly.misplaced.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CrashedPlane extends InteractibleObject {

    public CrashedPlane( int x, int y) {
        super(new Texture(Gdx.files.internal("Plane.png")), x, y);
    }

    @Override
    public void onInteract() {

    }
}
