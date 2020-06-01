/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Federico
 */
public class KeyInputHandler extends KeyAdapter {

    private int pressCount = 1;

    public boolean waitingForKeyPress = true;

    /**
     * Notification from AWT that a key has been pressed. Note that a key being
     * pressed is equal to being pushed down but *NOT* released. Thats where
     * keyTyped() comes in.
     *
     * @param e The details of the key that was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // if we're waiting for an "any key" typed then we don't 
        // want to do anything with just a "press"
        if (waitingForKeyPress) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("LEFT");
            Space.leftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("RIGHT");
            Space.rightPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("FIRE");
            Space.firePressed = true;
        }
    }

    /**
     * Notification from AWT that a key has been released.
     *
     * @param e The details of the key that was released
     */
    @Override
    public void keyReleased(KeyEvent e) {

        if (waitingForKeyPress) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Space.leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Space.rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Space.firePressed = false;
        }
    }

    /**
     * Notification from AWT that a key has been typed. Note that typing a key
     * means to both press and then release it.
     *
     * @param e The details of the key that was typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {

        if (waitingForKeyPress) {
            if (pressCount == 1) {

                waitingForKeyPress = false;

                pressCount = 0;
            } else {
                pressCount++;
            }
        }

        if (e.getKeyChar() == 27) {
            System.exit(0);
        }
    }

}
