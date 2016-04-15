package sexy.sly.misplaced.lib;

public enum Quantities {

    WRECKAGE;

    int getQuantities() {
        switch (this) {
            case WRECKAGE:
                //item = scrap
                //amount = 20
                return 1;
        }
        return 0;
    }
}
