package sexy.sly.misplaced.resources;

public class Resource {

    private float amount;
    private String id;
    private String label;
    private boolean visible;

    public Resource(String id, String label, boolean visible) {
        this.amount = 0;
        this.id = id;
        this.label = label;
        this.visible = visible;
    }

    public Resource(String label, boolean visible) {
        this(label.replaceAll(" ", "").toLowerCase(), label, visible);
    }

    public void add(float amount) {
        this.amount += amount;
    }

    public void remove(float amount) {
        this.amount -= amount;
    }

    public float getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isVisible() {
        return visible;
    }
}
