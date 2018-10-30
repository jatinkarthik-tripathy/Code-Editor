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
public class size {

    size(String fnt, int fnt_size, RSyntaxTextArea textArea) {
        String[] font_sizes = {"15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        JComboBox scb = new JComboBox(font_sizes);

        int ret = JOptionPane.showConfirmDialog(null, scb, "Please Select", JOptionPane.YES_NO_OPTION);
        if (ret == 0) {
            fnt_size = Integer.valueOf(scb.getSelectedItem().toString());
            textArea.setFont(new Font(fnt, Font.PLAIN, fnt_size));
        }
    }

}
