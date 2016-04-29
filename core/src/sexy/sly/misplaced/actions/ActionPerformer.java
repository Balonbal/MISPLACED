package sexy.sly.misplaced.actions;

import sexy.sly.misplaced.managers.CharacterManager;
import sexy.sly.misplaced.story.ActionElement;

public class ActionPerformer {

    private CharacterManager characterManager;

    public ActionPerformer(CharacterManager c) {
        characterManager = c;
    }

    public void parseAction(ActionElement element) {
        //TODO Make each action a class, might be easier in the long run
        if (element.getAction().equals("rename")) {
            int id = Integer.parseInt(element.getTarget());
            String newName = join(" ", element.getParams());

            characterManager.getCharacter(id).setName(newName);

            System.out.println(id +"'s name was set to'" + newName + "'");
        }
    }

    //TODO move to util class
    public String join(String glue, String ... s) {
        //No need to join empty strings
        if (s.length == 0) return null;

        StringBuilder sb = new StringBuilder(s[0]);

        for (int i = 1; i < s.length; i++) {
            sb.append(glue).append(s[i]);
        }

        return sb.toString();
    }
}
