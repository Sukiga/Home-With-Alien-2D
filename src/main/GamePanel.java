package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 pixels by 16 pixels, this is the tile size I draw with
    final int scale = 4; // this is a scale, so figures look four times bigger
    
    final int tileSize = originalTileSize * scale; // 64 x 64 tile, this is the actual tile size on screen
    final int maxScreenCol = 16; // the maximum columns on the screen is 16 tiles
    final int maxScreenRow = 12; // the maximum column on the screen is 12 tiles, which makes 16 x 12 tiles on screen
    final int screenWidth = tileSize * maxScreenCol; // 1024 pixels horizontally
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels vertically

    public GamePanel() { // constructor

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }
}
 