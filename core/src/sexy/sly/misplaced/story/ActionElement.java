package sexy.sly.misplaced.story;

public class ActionElement extends  DialogElement {

    public String getAction() {
        return action;
    }

    public String getTarget() {
        return target;
    }

    public String[] getParams() {
        return params;
    }

    private String action, target;
    private String[] params;

    @Override
    boolean isDone() {
        return false;
    }

    public ActionElement(String action, String target, String ... params) {
        this.action = action;
        this.target = target;
        this.params = params;
    }
}
