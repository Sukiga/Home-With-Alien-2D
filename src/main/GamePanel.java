package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 pixels by 16 pixels, this is the tile size I draw with
    final int scale = 4; // this is a scale, so figures look four times bigger
    
    final int tileSize = originalTileSize * scale; // 64 x 64 tile, this is the actual tile size on screen
    final int maxScreenCol = 16; // the maximum columns on the screen is 16 tiles
    final int maxScreenRow = 12; // the maximum column on the screen is 12 tiles, which makes 16 x 12 tiles on screen
    final int screenWidth = tileSize * maxScreenCol; // 1024 pixels horizontally
    final int screenHeight = tileSize * maxScreenRow; // 768 pixels vertically

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // thread is like a game clock that you can start and stop.

    // set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() { // constructor

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // game panel can focus on receiving keys

    }

    public void startGameThread() {
        gameThread = new Thread(this); // instantiating a thread(clock) by passing this class
        gameThread.start();
    }

    @Override
    public void run() { // when we start gameThread, it automatically calls this method

        // I used the delta method for the game loop

        double drawInterval = 1000000000 / FPS; // one second(a billion nano seconds) over 60
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) { // this is a game loop, the unit is fps

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/ drawInterval; // time passed over one over 60, then add it to delta
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();

                repaint(); // this is how you call paintComponent();

                delta --; // reset delta

                drawCount ++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount); // display fps
                drawCount = 0;
                timer = 0;
            }
            
        }
        
    }

    public void update() {

        // positions: the further it is right and downwards, the bigger the number
        if (keyH.upPressed == true) {
            playerY -= playerSpeed; 
        }
        if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // turning g into a more controllable sophisticated object

        g2.setColor(Color.white); // we set a color to use for drawing objects

        g2.fillRect(playerX, playerY, tileSize, tileSize); // draw a rectangle in the size of a tile

        g2.dispose(); // dispose of this graphics context and release any 

    }


}
 