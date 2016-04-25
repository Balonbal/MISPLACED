package sexy.sly.misplaced.UI;

public interface InteractionHelper {

    void showDialogue(String title, String message);
    void showChoice(String title, String ... choices);
    void rename(int id, String newName);
    //Character getCharacter
}
