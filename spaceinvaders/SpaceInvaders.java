/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author comerci.federico
 */
/**
 * @param args the command line arguments
 */

// creo un JFrame che contiene il gioco
//permette di avere la barra per il titolo e i classici pulsanti -ox
public class SpaceInvaders extends JFrame {

    // Lo spazio immenso (il Canvas in cui dipingere la scena del gioco)
    public SpaceInvaders() throws InterruptedException {
        super("Space Invaders"); //diamo un titolo alla finestra di gioco
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // quando premo la X della finestra voglio terminare il gioco/programma

        // aggiungo lo spazio profondo alla finestra di gioco
        this.setResizable(false);
        setIgnoreRepaint(true);

        // avvia l'introduzione
        Intro intro = new Intro();
        this.add(intro);
        // impacchetto il tutto e rendo visibile
        this.pack();
        this.setVisible(true);
        intro.run();
    }

    public static void main(String[] args) throws InterruptedException {
        SpaceInvaders si = new SpaceInvaders();

    }
}
