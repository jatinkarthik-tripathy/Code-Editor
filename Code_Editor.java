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
    JMenu fl, edt, run, templates, help;
    JMenuItem nw, op, sv, sv_as, ext, font, font_sz, lang, run_code, add_temp, see_temp, report, abt;
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
        RSyntaxTextArea.setTemplatesEnabled(true);
        sp = new RTextScrollPane(textArea);

        mb = new JMenuBar();

        fl = new JMenu("File");
        edt = new JMenu("Prefs");
        run = new JMenu("Run");
        templates = new JMenu("Templates");
        help = new JMenu("Help");

        nw = new JMenuItem("New");
        nw.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        nw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new new_file(textArea);
            }
        });
        op = new JMenuItem("Open");
        op.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        op.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new open_file(path, dir_path, textArea);
            }
        });
        sv = new JMenuItem("Save");
        sv.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        sv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new save_file(path, dir_path, textArea);
            }

        });
        sv_as = new JMenuItem("Save As");
        sv_as.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new save_file_as(path, dir_path, textArea);
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
                new font(fnt, fnt_size, textArea);
            }

        });
        font_sz = new JMenuItem("Size Manager");
        font_sz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new size(fnt, fnt_size, textArea);

            }

        });
        lang = new JMenuItem("Language");
        lang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new lang(textArea);
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
                new op_cmd(dir_path);
            }
        });

        run.add(run_code);

        add_temp = new JMenuItem("Add Template");
        add_temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new templates().setVisible(true);
            }

        });

        templates.add(add_temp);

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
                        "                                     Made by\n"
                        + "                         Jatin Karthik Tripathy \n"
                        + "                                  (c) 2018\n"
                        + "                    If you feel you make it better,\n"
                        + "            Feel free to check out the source code \n"
                        + "https://github.com/jatinkarthik-tripathy/Code-Editor"
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

        mb.add(templates);

        mb.add(help);

        f.add(mb);

        f.setJMenuBar(mb);

        f.add(sp);

        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

        f.pack();

        f.setSize(800, 600);
        f.setVisible(true);
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
