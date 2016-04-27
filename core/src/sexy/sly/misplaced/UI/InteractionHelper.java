package sexy.sly.misplaced.UI;

import sexy.sly.misplaced.story.DialogElement;

public interface InteractionHelper {

    void showDialogue(String title, String message);
    void showChoice(String title, String ... choices);
    void rename(int id, String newName);

    void push(DialogElement element);
    //Character getCharacter
}
