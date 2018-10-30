/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author Jatin
 */
public class open_file {

    open_file(String path, String dir_path, RSyntaxTextArea textArea) {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Open file");

        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                path = file.toString();
                System.out.println(path);
                dir_path = path.replace(file.getName(), "");
                FileReader reader = new FileReader(path);
                BufferedReader br = new BufferedReader(reader);
                textArea.read(br, null);
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Code_Editor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Code_Editor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
