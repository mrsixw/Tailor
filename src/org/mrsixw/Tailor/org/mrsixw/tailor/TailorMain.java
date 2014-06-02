package org.mrsixw.tailor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Main application class for Tailor
 * Created by mrsixw on 30/05/2014.
 */
public class TailorMain extends JFrame {

    public TailorMain() {
        super();

        this.addWindowMenuBar();

        Object[][] tableData = {
                {"1", "Log Message"},
                {"2", "Log Message 2"}
        };
        Object[] headings = {"Line Number", "Line Data"};
        this.logTable = new JTable(tableData, headings);

        addScrollableTablePane();
    }


    private void addScrollableTablePane() {
        Container mainContainer = this.getContentPane();

        JScrollPane scroller = new JScrollPane(this.logTable);

        mainContainer.add(scroller);

    }

    private void addWindowMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        this.addFileMenu(menuBar);
        this.setJMenuBar(menuBar);
    }

    /**
     * Populates the File menu for the application
     *
     * @param parent parent JMenuBar which this will be added to
     */
    private void addFileMenu(JMenuBar parent) {
        JMenu fileMenu = new JMenu("File");

        fileMenu.addSeparator();

        // Exit option
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (JOptionPane.showConfirmDialog(TailorMain.this,
                        "Are you sure you want to exit?",
                        "Exit?",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                ) == JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        fileMenu.add(exitItem);

        parent.add(fileMenu);
    }

    public static void main (String[] args){


        // work out if we are running on a Mac...
        String lcOSName = System.getProperty("os.name").toLowerCase();
        IS_MAC = lcOSName.startsWith("mac os x");

        // If we are a Mac, we want to look right with the menu bar and stuff, so do this BEFORE we create
        // any JFrames....
        if (IS_MAC) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            // This doesnt work on post lion builds, or is happeing too late. It can be worked around with
            // -Xdock:name="<name>" as a VM option
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Tailor");
        }

        final TailorMain lvm = new TailorMain();

        // set the UI L&F to be native
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Add window adapter to close on clicking the X
        lvm.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                // No Action
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                lvm.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                // No Action
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
                // No Action
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
                // No Action
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
                // No Action
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
                // No Action
            }
        });

        // set the default window size
        lvm.setSize(1024, 768);

        // center the window
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        lvm.setLocation((int) (screenDimensions.getWidth() / 2 - lvm.getWidth() / 2),
                (int) (screenDimensions.getHeight() / 2 - lvm.getHeight() / 2));


        // show the window.
        lvm.setVisible(true);
    }


    public static boolean IS_MAC = false;

    private JTable logTable = null;

}
