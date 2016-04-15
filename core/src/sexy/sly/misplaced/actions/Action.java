package sexy.sly.misplaced.actions;

public abstract class Action {

    float progress;
    String name;
    float maxTime;

    public float getProgress() {
        return progress/maxTime;
    }

    public void update(float dt) {
        //Action completed
        if (progress >= maxTime) {
            onCompletion();
            return;
        }

        progress+=dt;
    }

    abstract void onCompletion();

    public String getText() {
        return name;
    }


}
