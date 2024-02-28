package UI;

import javax.swing.*;

/**
 * Custom JLabel
 */
public class TextLabel extends JLabel {

    public TextLabel(String label, String text) {
        super(label + ": " + text);
    }


    /**
     * @param label
     * @param text
     */
    public void setText(String label, String text) {
        // TODO Auto-generated method stub
        super.setText(label + ": " + text);
    }
}
