package sexy.sly.misplaced.story;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

import java.util.ArrayList;

public class Story {

    private ArrayList<StoryFragment> introduction = new ArrayList<StoryFragment>();
    private long lastRender;
    private StoryFragment lastFragment;
    private GlyphLayout layout;


    public Story() {
        introduction.add(new StoryFragment("Hello!", 3000));
        introduction.add(new StoryFragment("Thanks for checking out my game!", 7000));
        introduction.add(new StoryFragment("Anyways, here's the story so far:", 7000));
        introduction.add(new StoryFragment("You recall getting on Americ Flight 916 in Los Angeles\n heading for Sidney, Australia.\nSomething must have happened,\nbecause you are no longer on that plane.\nPresumably the plane has crash landed\nsomewhere in the pacific ocean.\nLuckily for you and 50 other survivors,\nthe plane landed on an island.\n\n   Well, half of it anyway.", 25000));
        lastRender = 0;
        layout = new GlyphLayout();
    }

    public void renderNext(SpriteBatch batch, BitmapFont font) {
        StoryFragment current;
        if (lastRender == 0 || System.currentTimeMillis() > lastRender + lastFragment.getDuration()) {
            current = introduction.get(lastFragment == null ? 0 : introduction.indexOf(lastFragment) + 1);
            lastRender = System.currentTimeMillis();
            lastFragment = current;
        } else {
            current = lastFragment;
        }
        layout.setText(font, current.getText());
        while (layout.width > Gdx.graphics.getWidth() - 40) {
            font.getData().setScale(font.getData().scaleX - .1F);
            layout.setText(font, current.getText());
        }
        font.draw(batch, current.getText(), (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);
    }

    public boolean hasMore() {
        return lastFragment == null || (introduction.indexOf(lastFragment) < introduction.size() - 1);
    }

}
