package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;

    }

    public void update() {

        // positions: the further it is right and downwards, the bigger the number
        if (keyH.upPressed == true) {
            y -= speed; 
        }
        if (keyH.downPressed == true) {
            y += speed;
        }
        if (keyH.leftPressed == true) {
            x -= speed;
        }
        if (keyH.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.white); // we set a color to use for drawing objects

        g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize()); // draw a rectangle in the size of a tile

    }

}
