/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author Jatin
 */
public class new_file {

    new_file(RSyntaxTextArea textArea) {
        int n = JOptionPane.showConfirmDialog(
                null,
                "Save File?",
                "",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            JFileChooser fileSaver = new JFileChooser();
            int ret = fileSaver.showDialog(null, "Save file");

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileSaver.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file.toString());
                    BufferedWriter bw = new BufferedWriter(writer);
                    textArea.write(bw);
                    bw.close();
                    textArea.setText("");
                } catch (Exception e2) {
                }
            }
        } else {
            textArea.setText("");
        }
    }

}
