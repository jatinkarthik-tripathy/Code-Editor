package code_editor;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author Jatin
 */
public class Code_Editor {

    JFrame f;
    RSyntaxTextArea textArea;
    RTextScrollPane sp;
    JMenuBar mb;
    JMenu fl, edt, run, help;
    JMenuItem nw, op, sv, sv_as, ext, font, font_sz, shortcuts, lang, run_code, report, abt;
    String fnt = "monospaced";
    int fnt_size = 20;
    String path = null;
    String dir_path = null;

    Code_Editor() {
        f = new JFrame("Code Editor");
        textArea = new RSyntaxTextArea();
        textArea.setFont(new Font(fnt, Font.PLAIN, fnt_size));
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        sp = new RTextScrollPane(textArea);

        mb = new JMenuBar();

        fl = new JMenu("File");
        edt = new JMenu("Prefs");
        run = new JMenu("Run");
        help = new JMenu("Help");

        nw = new JMenuItem("New");
        nw.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        nw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
        op = new JMenuItem("Open");
        op.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        op.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
        sv = new JMenuItem("Save");
        sv.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        sv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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

        });
        sv_as = new JMenuItem("Save As");
        sv_as.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        });
        ext = new JMenuItem("Exit");
        ext.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        ext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = JOptionPane.showConfirmDialog(
                        null,
                        "Would you like quit?",
                        "",
                        JOptionPane.YES_NO_OPTION);
                if (ret == 0) {
                    f.dispose();
                }
            }
        });

        fl.add(nw);
        fl.add(op);
        fl.add(sv);
        fl.add(sv_as);
        fl.add(new JSeparator());
        fl.add(ext);

        font = new JMenuItem("Font Manager");
        font.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] font_types = {"Sanserif", "Serif", "Monospaced", "Dialog"};
                JComboBox fcb = new JComboBox(font_types);

                int ret = JOptionPane.showConfirmDialog(null, fcb, "Please Select", JOptionPane.YES_NO_OPTION);
                if (ret == 0) {
                    fnt = fcb.getSelectedItem().toString();
                    textArea.setFont(new Font(fnt, Font.PLAIN, fnt_size));
                }
            }

        });
        font_sz = new JMenuItem("Size Manager");
        font_sz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] font_sizes = {"15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
                JComboBox scb = new JComboBox(font_sizes);

                int ret = JOptionPane.showConfirmDialog(null, scb, "Please Select", JOptionPane.YES_NO_OPTION);
                if (ret == 0) {
                    fnt_size = Integer.valueOf(scb.getSelectedItem().toString());
                    textArea.setFont(new Font(fnt, Font.PLAIN, fnt_size));
                }
            }

        });
        shortcuts = new JMenuItem("Keyboard Shortcuts");
        lang = new JMenuItem("Language");
        lang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
        });

        edt.add(font);
        edt.add(font_sz);
        edt.add(lang);

        run_code = new JMenuItem("Run Project");
        run_code.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        run_code.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        run.add(run_code);

        report = new JMenuItem("Report Issue...");

        report.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(
                        null,
                        "Drop a mail to\n jatinkarthikt@gmail.com"
                );
            }

        }
        );
        abt = new JMenuItem("About");

        abt.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(
                        null,
                        "                         Made by\n"
                        + "              Jatin Karthik Tripathy \n"
                        + "                       (c) 2018\n"
                        + "         If you feel you make it better,\n"
                        + "Feel free to check out the source code \n"
                        + "                    [ github link ]"
                );
            }

        }
        );

        help.add(report);

        help.add(new JSeparator());
        help.add(abt);

        mb.add(fl);

        mb.add(edt);

        mb.add(run);

        mb.add(help);

        f.add(mb);

        f.setJMenuBar(mb);

        f.add(sp);

        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

        f.pack();

        f.setSize(
                800, 600);
        f.setVisible(
                true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Code_Editor();
            }
        });
    }
}
