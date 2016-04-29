package sexy.sly.misplaced.story;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Character {

    protected final int id; //Should be constant
    protected String name;
    protected Image portrait; //TODO Create chat portraits

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
