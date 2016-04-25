package sexy.sly.misplaced.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sexy.sly.misplaced.states.State;
import sexy.sly.misplaced.story.StoryParser;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;
    private StoryParser parser;

    public GameStateManager() {
        states = new Stack<State>();
        parser = new StoryParser(Gdx.files.internal("data/Dialog.xml"));
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public void setStates(State state) {
        states.pop();
        states.push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public StoryParser getParser() {
        return parser;
    }
}
