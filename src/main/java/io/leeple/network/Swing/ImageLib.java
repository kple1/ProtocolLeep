package io.leeple.network.Swing;


import javax.swing.*;
import java.awt.*;

public class ImageLib extends JFrame {

    public void changeSize(JFrame frame, String path) {
        JLabel imgLabel = new JLabel();
        ImageIcon image1 = new ImageIcon(path);

        Image img = image1.getImage();
        Image updateImg = img.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        imgLabel.setIcon(updateIcon);

        imgLabel.setBounds(86, 85, 154, 159);
        frame.getContentPane().add(imgLabel);
    }
}
