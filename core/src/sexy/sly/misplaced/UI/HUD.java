package sexy.sly.misplaced.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sexy.sly.misplaced.resources.Resource;
import sexy.sly.misplaced.sprites.Player;

import java.util.HashMap;

public class HUD extends Stage {

    private Table inventory;
    private Skin skin;
    private Player player;
    private ProgressBar.ProgressBarStyle barStyle;

    private HashMap<String, Label> amounts;

    public HUD(Player player) {
        super();
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        inventory = new Table(skin).right().bottom();
        amounts = new HashMap<String, Label>();

        //inventory.debug();
        //inventory.setPosition(0, Gdx.graphics.getHeight());
        inventory.setSize(200f, 25f + 20f * player.getInventory().getVisible().size());
        inventory.setBackground(new NinePatchDrawable(getNinePatch("UI/inventory.9.png")));
        inventory.pad(2f, 2f, 0, 0);

        this.player = player;

        inventory.add("Inventory").colspan(2).center().row(); //Header
        for (Resource r: player.getInventory().getVisible()) {
            inventory.add(r.getLabel() + ":").size(100f, 20f);
            Label res = new Label(r.getAmount() + "", skin);
            amounts.put(r.getId(), res);
            inventory.add(res).size(100f, 20f).row();
        }

        this.addActor(inventory);
    }

    public void update(float delta) {
        for (Resource r : player.getInventory().getVisible()) {
            if (amounts.get(r.getId()) == null) continue; //TODO Create new rows and resize table
            amounts.get(r.getId()).setText(r.getAmount() + "");
        }

        if (player.currentAction != null) {
            ProgressBar bar = new ProgressBar(0, 0, .1f, false, skin);
            bar.setValue(player.progress);
            this.addActor(bar);
        }

        this.act(delta);
    }

    public void render() {
        this.draw();
    }

    private NinePatch getNinePatch(String fname) {
        final Texture t = new Texture(Gdx.files.internal(fname));

        return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), 4, 4, 4, 4);

    }
}
