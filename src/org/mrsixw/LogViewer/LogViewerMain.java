import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by mrsixw on 30/05/2014.
 */
public class LogViewerMain extends JFrame {

    public LogViewerMain(){
        super();

        this.addWindowMenuBar();
    }

    private void addWindowMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        this.addFileMenu(menuBar);
        this.setJMenuBar(menuBar);
    }

    private void addFileMenu(JMenuBar parent) {
        JMenu fileMenu = new JMenu("File");

        fileMenu.addSeparator();

        // Exit option
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(JOptionPane.showConfirmDialog(LogViewerMain.this,
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
        if(IS_MAC == true)
        {
            System.out.println("This is a Mac...");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        final LogViewerMain lvm = new LogViewerMain();
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
                if(JOptionPane.showConfirmDialog(lvm,
                        "Are you sure you want to exit?",
                        "Exit?",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                ) == JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
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

        // show the window.
        lvm.setVisible(true);
    }


    public static boolean IS_MAC = false;

}
