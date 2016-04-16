package sexy.sly.misplaced.resources;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Resource> resources;

    public Inventory() {
        resources = new ArrayList<Resource>();
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void addResource(Resource res) {
        resources.add(res);
    }

    public Resource getResource(String id) {
        for (Resource r: resources) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Resource> getVisible() {
        ArrayList<Resource> r = new ArrayList<Resource>();

        for (Resource res: resources) {
            if (res.isVisible()) r.add(res);
        }

        return r;
    }
}
