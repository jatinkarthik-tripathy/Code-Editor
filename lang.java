/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_editor;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 *
 * @author Jatin
 */
public class lang {

    lang(RSyntaxTextArea textArea) {
        String[] options = {"C++", "JAVA", "PYTHON"};
        JComboBox cb = new JComboBox(options);
        int ret = JOptionPane.showConfirmDialog(null, cb, "Select Your Preference", JOptionPane.YES_NO_OPTION);
        if (ret == 0) {
            if (cb.getSelectedItem().toString().equals("C++")) {
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
            } else if (cb.getSelectedItem().toString().equals("JAVA")) {
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            } else if (cb.getSelectedItem().toString().equals("PYTHON")) {
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
            }
        }
    }

}
