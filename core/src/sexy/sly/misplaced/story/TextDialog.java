package sexy.sly.misplaced.story;

public class TextDialog extends DialogElement {
    @Override
    boolean isDone() {
        return false;
    }

    public TextDialog(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
