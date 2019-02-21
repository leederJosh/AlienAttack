package world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A DialogNode is a single node in a tree of dialog.
 * @param <CharSequence>
 * @author Sam Robinson
 */
public class DialogNode<CharSequence> implements Iterable<DialogNode> {

    String text;
    ArrayList<String> choices;
    DialogNode<String> parent;
    LinkedList<DialogNode> children;

    public DialogNode(String text) {
        this.text = text;
        this.children = new LinkedList<DialogNode>();
    }

    /**
     * Sets the possible choices for the dialog.
     * @param choices
     */
    public void setChoices(ArrayList<String> choices) {
        //Separate method so as choices can change depending on gameplay.
        this.choices = choices;
    }
    @Override
    public Iterator<DialogNode> iterator() {
        return null;
    }

    public void addChild(DialogNode child) {
        children.add(child);
    }

    public LinkedList getChildren() {
        return children;
    }

    public java.lang.CharSequence getText() {
        return text;
    }
}
