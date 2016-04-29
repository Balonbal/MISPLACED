package sexy.sly.misplaced.managers;

import sexy.sly.misplaced.story.Character;

import java.util.ArrayList;

public class CharacterManager {

    private ArrayList<Character> characters;
    private int lastID = -1;

    public CharacterManager() {
        characters = new ArrayList<Character>();
    }

    public void push(Character c) {
        characters.add(c);
        lastID = c.getId();
    }

    public void createAndPush(String name) {
        Character c = new Character(lastID++, name);
    }

    public Character getCharacter(int id) {
        for (Character c : characters) {
            if (c.getId() == id) return c;
        }

        return null;
    }

    public Character getCharacter(String name) {
        for (Character c : characters) {
            if (c.getName().equals(name)) return c;
        }

        return null;
    }
}
