package io.leeple.network.Swing;

import io.leeple.network.SQL.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Panel extends JFrame{

    public JPanel createPanel(String albumName, String imagePath, String smallImagePath, int width, int height, int loop, Connection connection,
                               String dbName, String column, String tableName, String getNumColumn) {
        // 컴포넌트를 층으로 배치할 JLayeredPane 생성
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));

        // 메인 이미지 라벨 생성
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        imageLabel.setBounds(0, 0, width, height);
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        // 작은 이미지 라벨 생성
        if (DataBase.getInt(column, tableName, connection, dbName, getNumColumn, loop) == 1) {
            JLabel smallImageLabel = new JLabel(new ImageIcon(smallImagePath));
            int smallImageWidth = 50; // 작은 이미지 너비 조절
            int smallImageHeight = 50; // 작은 이미지 높이 조절
            int x = width - smallImageWidth; // 오른쪽 정렬
            int y = 0; // 상단 정렬
            smallImageLabel.setBounds(x, y, smallImageWidth, smallImageHeight);
            layeredPane.add(smallImageLabel, JLayeredPane.PALETTE_LAYER); // 작은 이미지를 메인 이미지 위에 배치
        }

        // 층을 포함하는 패널 생성
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(layeredPane, BorderLayout.CENTER);

        // 앨범 이름이 너무 길면 조정
        if (albumName.length() >= 9) {
            albumName = albumName.substring(0, 9) + "...";
        }

        // 앨범 이름 라벨 추가
        JLabel nameLabel = new JLabel(albumName, SwingConstants.CENTER);
        panel.add(nameLabel, BorderLayout.SOUTH);

        return panel;
    }
}
