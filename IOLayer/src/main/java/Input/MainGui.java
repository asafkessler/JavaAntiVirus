package Input;

import FilesLogic.FilesScanner;
import Handlers.FilesContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainGui {
    private JPanel panel1;
    private JSpinner threadSpinner;
    private JTextField pathField;
    private JTextField totalFilesField;
    private JTextField resultField;
    private JButton checkPathButton;
    private JButton scanButton;
    private FilesScanner filesScanner;
    private MainGui() {
        checkPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dir = pathField.getText();
                if (new File(dir).exists()) {
                    FileFetcher fileFetcher = new FileFetcher(dir);
                    FilesContainer container = new FilesContainer();
                    File[] files = fileFetcher.getFilesTree();
                    totalFilesField.setText(String.valueOf(files.length));
                    container.init(files);
                    filesScanner = new FilesScanner(container.getFiles());
                }
                else {
                    totalFilesField.setText("Bad Path");
                }
                totalFilesField.setDisabledTextColor(Color.BLACK);

            }
        });
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filesScanner != null){
                    filesScanner.scan((Integer) threadSpinner.getValue());

                    resultField.setText("Total time: "+filesScanner.getLastScanTime()+" | "+
                            "Number Of Invalid Files: "+filesScanner.getState().getFalseFiles().size());
                    resultField.setDisabledTextColor(Color.RED);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainGui");
        frame.setContentPane(new MainGui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
