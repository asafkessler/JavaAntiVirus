package Input;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoGui {
    private JButton MICHAELBUTTONButton;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JSpinner spinner1;
    private JButton YESButton;

    DemoGui(){
        MICHAELBUTTONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setText(textField1.getText());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AntiVirus");
        frame.setContentPane(new DemoGui().panel1);
        frame.setVisible(true);
    }
}
