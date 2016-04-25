package sexy.sly.misplaced.story;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import sexy.sly.misplaced.UI.InteractionHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StoryParser {

    FileHandle f;

    public StoryParser(FileHandle storyfile) {
        f = storyfile;
    }

    public void showDialogue(int dialogueID, InteractionHelper helper) {
        XmlReader.Element dialogue = getDialogue(dialogueID);
        if (dialogue == null) return;
        //Perform in order
        for (int i = 0; i < dialogue.getChildCount(); i++) {
            XmlReader.Element action = dialogue.getChild(i);
            //Message
            if (action.getName().equals("text")) {
                helper.showDialogue(action.getAttribute("speaker"), action.getText());
            } else if (action.getName().equals("action")) {
                //Do something
                if (action.getAttribute("type").equals("rename")) {
                    helper.rename(Integer.parseInt(action.getAttribute("target")), action.getAttribute("param"));
                }
            } else if (action.getName().equals("choice")) {
                Array<XmlReader.Element> options = action.getChildrenByName("option");
                String[] choices = new String[options.size];
                for (int j = 0; j < choices.length; j++) {
                    choices[j] = options.get(j).getText();
                }

                helper.showChoice(action.getAttribute("text"), choices);
            }
        }
    }

    private XmlReader.Element getDialogue(int id) {
        XmlReader reader = new XmlReader();
        try {
            XmlReader.Element root = reader.parse(f);
            Array<XmlReader.Element> dialogues = root.getChildrenByName("dialogue");
            for (XmlReader.Element d: dialogues) {
                //Get the requested dialogue
                if (Integer.parseInt(d.getAttribute("id")) == id) {
                    return d;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
