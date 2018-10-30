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
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author Jatin
 */
public class save_file {

    save_file(String path, String dir_path, RSyntaxTextArea textArea) {
        if (path != null) {
            try {
                FileWriter writer = new FileWriter(path);
                BufferedWriter bw = new BufferedWriter(writer);
                textArea.write(bw);
                bw.close();
            } catch (Exception e2) {
            }
        }
        if (path == null) {
            JFileChooser fileSaver = new JFileChooser();
            int ret = fileSaver.showDialog(null, "Save file");

            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileSaver.getSelectedFile();
                try {
                    path = file.toString();
                    dir_path = path.replace(file.getName(), "");
                    FileWriter writer = new FileWriter(file.toString());
                    BufferedWriter bw = new BufferedWriter(writer);
                    textArea.write(bw);
                    bw.close();
                } catch (Exception e2) {
                }
            }
        }
    }

}
