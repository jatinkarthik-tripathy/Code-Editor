/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_editor;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jatin
 */
public class op_cmd {

    op_cmd(String dir_path) {
        if (dir_path != null) {
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd.exe /c start", null, new File(dir_path));
            } catch (IOException ex) {
                Logger.getLogger(Code_Editor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Save File First");
        }
    }

}
