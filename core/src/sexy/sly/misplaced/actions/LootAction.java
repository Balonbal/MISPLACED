package sexy.sly.misplaced.actions;

import sexy.sly.misplaced.lib.Reference;
import sexy.sly.misplaced.sprites.Player;

public class LootAction extends Action {

    private Player player;
    private int ineractee;

    public LootAction(int interactee, Player player) {
        switch (interactee) {
            case Reference.INTERACT_OBJECT_WRECKAGE:
                name = "Looting wreckage...";
                progress = 0;
                maxTime = 15;
        }
        this.player = player;
        this.ineractee = interactee;
    }

    @Override
    void onCompletion() {
        switch (ineractee) {
            case Reference.INTERACT_OBJECT_WRECKAGE:
                player.getInventory().getResource("scrap").add(20);
        }

        this.completed = true;
    }
}
