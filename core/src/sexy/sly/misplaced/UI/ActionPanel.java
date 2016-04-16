package sexy.sly.misplaced.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ActionPanel {

    private Table table;
    private Label title;
    private ProgressBar progressBar;
    private ProgressBar.ProgressBarStyle barStyle;


    public ActionPanel(Skin skin) {
        table = new Table(skin);
        table.setHeight(30f);
        title = new Label("", skin);

        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white2", new Texture(pixmap));
        barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white2", Color.DARK_GRAY), skin.newDrawable("white2", Color.GREEN));
        barStyle.knobBefore = barStyle.knob;

        progressBar = new ProgressBar(0, 1, .01f, false, barStyle);

        table.background(skin.newDrawable("white2", 0, 0, 0, .8f));
        table.add(title).center().row();
        table.add(progressBar).expand().row();
        table.setVisible(false);
    }

    public void setLabel(String label) {
        title.setText(label);
        progressBar.setValue(0);
        table.setWidth(progressBar.getWidth()); //For some reason this has to be set for the bg
        table.setPosition((Gdx.graphics.getWidth() - table.getWidth())/2, (Gdx.graphics.getHeight())/2 + table.getHeight() + 10f); //Should be right above the player
        table.setVisible(true);
    }

    public void updateValue(float newValue) {
        progressBar.setValue(newValue);
    }

    public void hide() {
        table.setVisible(false);
    }

    public Table getTable() {
        return table;
    }

}
