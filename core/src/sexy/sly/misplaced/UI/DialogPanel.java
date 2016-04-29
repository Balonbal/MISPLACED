package sexy.sly.misplaced.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import sexy.sly.misplaced.managers.CharacterManager;
import sexy.sly.misplaced.story.DialogElement;
import sexy.sly.misplaced.story.TextDialog;

import java.util.ArrayList;

public class DialogPanel implements InteractionHelper {

    private ArrayList<DialogElement> queue;
    private Table table;
    private Label title, text;
    private CharacterManager characterManager;

    public DialogPanel(Skin skin, CharacterManager manager) {
        queue = new ArrayList<DialogElement>();
        table = new Table(skin).center().bottom();

        table.sizeBy(350f, 150f);

        table.setPosition(Gdx.graphics.getWidth() /2 - table.getWidth()/2, 30f);

        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white2", new Texture(pixmap));
        table.background(skin.newDrawable("white2", 0, 0, 0, .8f));

        title = new Label("", skin);
        text = new Label("", skin);
        text.setWrap(true);
        table.add(title).right().row();
        table.add(text).width(300f).expandY();
        table.setVisible(false);

        characterManager = manager;
    }

    @Override
    public void showDialogue(String title, String message) {

    }

    @Override
    public void showChoice(String title, String... choices) {

    }

    @Override
    public void rename(int id, String newName) {

    }

    public void showDialogue() {
        if (queue.size() > 0) {
            if (!table.isVisible())table.setVisible(true);
            DialogElement element = queue.get(0);
            if (element.hasDialog()) {
                if (element instanceof TextDialog) { displayText(characterManager.getCharacter(Integer.parseInt(element.getTitle())).getName(), element.getText());}
            }

            queue.remove(0);
        } else {
            table.setVisible(false);
        }
    }

    public void displayText(String title, String text) {
        this.title.setText(title);
        this.text.setText(text);
    }

    @Override
    public void push(DialogElement element) {
        queue.add(element);
    }

    public Table getTable() {
        return table;
    }
}
