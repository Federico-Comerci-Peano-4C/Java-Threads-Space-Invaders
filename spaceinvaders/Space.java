/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author comerci.federico
 */
public class Space extends Canvas {

    protected final int w = 800;
    protected final int h = 600;

    public BufferedImage alien, ship;
    public int H = 0;
    public static boolean firePressed = false;
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;
    public int Sx = 0; //ship X posizione

    public Space() {
        System.out.println("Setting canvas background and dimensions ");

        setBackground(Color.BLACK);
        setSize(800, 600);
        ImageCanvas();
        addKeyListener((KeyListener) new KeyInputHandler());
        requestFocus();
    }

    public void ImageCanvas() {
        try {
            alien = ImageIO.read(new File("alien.png"));
            ship = ImageIO.read(new File("ship.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
