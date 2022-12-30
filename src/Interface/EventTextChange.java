
package Interface;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Phạm Văn Chánh
 */
public interface EventTextChange extends DocumentListener{
    
    void updateText(DocumentEvent e);

    @Override
    default void insertUpdate(DocumentEvent e) {
        updateText(e);
    }
    @Override
    default void removeUpdate(DocumentEvent e) {
        updateText(e);
    }
    @Override
    default void changedUpdate(DocumentEvent e) {
        updateText(e);
    }
}
