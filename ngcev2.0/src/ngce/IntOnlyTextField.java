
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * This extension of JTextField only allows the entry of integers.
 */
class IntOnlyTextField extends JTextField {

    public
    IntOnlyTextField() {
        super();
    }

    public
    IntOnlyTextField(String str, int cols) {
        super(str, cols);
    }

    protected
    Document createDefaultModel() {
        return new IntOnlyDocument();
    }

    static
    class IntOnlyDocument extends PlainDocument {

        public
        void insertString(int offset, String str, AttributeSet a) throws BadLocationException {

            /* Don't do anything if there is nothing to insert! */
            if (str == null) {
                return;
            }

            /* Insert the string if it is formatted properly. */
            try {

                int i = Integer.parseInt(str);

            } catch (NumberFormatException e) {

                /* Don't do anything if the string is misformatted. */
                return;
            }
            super.insertString(offset, str, a);
        }
    }

    /**
     * Return whatever is in the field as an integer value.
     */
    public
    int getValue() {
        int i = 0;
        String input = "null";

        try {
            input = getText();
            if (input.indexOf("-") == -1) {
                i = Integer.parseInt(input);
            } else {
                JOptionPane.showMessageDialog(null, "The number you entered must be non-negative", "Invalid Parameters Detected", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }
}
