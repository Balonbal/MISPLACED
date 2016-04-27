package sexy.sly.misplaced.story;

public abstract class DialogElement {

    protected String text;
    protected String title;

    abstract boolean isDone();
    boolean hasText() {return (text != null && !text.isEmpty());}
    boolean hasTitle() {return (title != null && !title.isEmpty());}
    public boolean hasDialog() {
        return hasText() || hasTitle();
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
