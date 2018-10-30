/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_editor;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author Jatin
 */
public class font {

    font(String fnt, int fnt_size, RSyntaxTextArea textArea) {
        String[] font_types = {"Sanserif", "Serif", "Monospaced", "Dialog"};
        JComboBox fcb = new JComboBox(font_types);

        int ret = JOptionPane.showConfirmDialog(null, fcb, "Please Select", JOptionPane.YES_NO_OPTION);
        if (ret == 0) {
            fnt = fcb.getSelectedItem().toString();
            textArea.setFont(new Font(fnt, Font.PLAIN, fnt_size));
        }
    }

}
