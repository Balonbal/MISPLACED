package sexy.sly.misplaced.actions;

import sexy.sly.misplaced.lib.Reference;

public class LootAction extends Action {

    public LootAction(int interactee) {
        switch (interactee) {
            case Reference.INTERACT_OBJECT_WRECKAGE:
                name = "Looting wreckage...";
                progress = 0;
                maxTime = 15;
        }
    }

    @Override
    void onCompletion() {
        //TODO stuff
    }
}
