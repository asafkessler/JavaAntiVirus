package Input;

import FilesLogic.FilesScanner;
import FilesLogic.FilesState;
import Handlers.FilesContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class MainGui {
    private JPanel panel1;
    private JSpinner threadSpinner;
    private JTextField pathField;
    private JTextField totalFilesField;
    private JTextField resultField;
    private JButton checkPathButton;
    private JButton scanButton;
    private JButton FIXButton;
    private FilesScanner filesScanner;

    private MainGui() {
        threadSpinner.setModel(new SpinnerNumberModel(1, 1, 10000, 1));

        checkPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dir = pathField.getText();
                if (new File(dir).exists()) {
                    FileFetcher fileFetcher = new FileFetcher(dir);
                    FilesContainer container = new FilesContainer();
                    File[] files = fileFetcher.getFilesTree();
                    container.init(files);
                    List<File> allFiles = container.getFiles();
                    totalFilesField.setText(String.valueOf(allFiles.size()));
                    filesScanner = new FilesScanner(allFiles);
                    totalFilesField.setDisabledTextColor(Color.GREEN);
                } else {
                    totalFilesField.setText("err");
                    totalFilesField.setDisabledTextColor(Color.RED);
                }

            }
        });
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filesScanner != null) {
                    filesScanner.scan((Integer) threadSpinner.getValue());

                    int size = filesScanner.getState().getFalseFiles().size();

                    resultField.setText("Total time: " + filesScanner.getLastScanTime() + " | " +
                            "Number Of Invalid Files: " + size);
                    resultField.setDisabledTextColor(size == 0 ? Color.GREEN : Color.RED);
                }
            }
        });
        FIXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filesScanner != null) {
                    FilesState state = filesScanner.getState();
                    int numberOfFiles = state.getFalseFiles().size();
                    int remain = state.clearFalseFiles();
                    resultField.setText("Removed: " + (numberOfFiles - remain) + " out of: " + numberOfFiles + " viruses");
                    resultField.setDisabledTextColor(remain == 0 ? Color.GREEN : Color.RED);
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
        frame.getContentPane().setBackground(Color.DARK_GRAY);


    }
}
