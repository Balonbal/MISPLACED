package sexy.sly.misplaced.story;

public class StoryFragment {
    private String text;
    private long duration;

    public StoryFragment(String text, long duration) {
        this.text = text;
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public long getDuration() {
        return duration;
    }
}
