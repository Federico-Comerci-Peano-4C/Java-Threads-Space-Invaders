/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author comerci.federico
 */
public class Intro extends Space {

    private int i = 0;
    private BufferStrategy strategy;
    private Point stelle1[] = new Point[222];
    private Point stelle2[] = new Point[222];

    private Point proiettili[] = new Point[99];
    private int proiettiliN = 0; // numero di proiettili nello schermo
    private boolean reuse = false;

    void run() throws InterruptedException {
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        //creo i 2 cieli stellati
        //due vettori ognuno con le coordinate delle 222 stelle
        for (int i = 0; i < 222; i++) {
            Random r = new Random();
            stelle1[i] = new Point();
            stelle1[i].x = Math.abs(r.nextInt()) % w;
            stelle1[i].y = Math.abs(r.nextInt()) % h;
        }

        for (int i = 0; i < 222; i++) {
            Random r = new Random();
            stelle2[i] = new Point();
            stelle2[i].x = Math.abs(r.nextInt()) % w;
            stelle2[i].y = Math.abs(r.nextInt()) % h;
        }

        // fase 1: muovi scritte e alieni
        for (i = 98; i > 0; i -= 1) {
            paint(i, true);
            Thread.sleep(10);
        }
//        //Thread.sleep(100);
//        for(i=0;i<10;i+=1) {
//            paint(i);
//            //Thread.sleep(10);
//        }

        // fase 2: muovi solo alieni
        while (true) {
            //muovi verso destra
            for (i = 0; i < 98; i++) {
                paint(i, false);
                Thread.sleep(59);
            }
            // muovi verso sinistra
            for (i = 98; i > 0; i--) {
                paint(i, false);
                Thread.sleep(59);
            }
        }
    }

    void paint(int n, boolean scritte) {
        //chiediamo il buffer in cui comporre la nuova immagine
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        //cancelliamo l'immagine precedente coprendola con un rettangolo nero
        g.setColor(Color.black);
        g.fillRect(0, 0, w, h);

        //metà del cielo non brilla
        Color stelleColor = new Color(222, 222, 222);
        g.setColor(stelleColor);
        for (int i = 0; i < 111; i++) {
            g.drawLine(stelle1[i].x, stelle1[i].y, stelle1[i].x, stelle1[i].y);
            g.drawLine(stelle2[i].x, stelle2[i].y, stelle2[i].x, stelle2[i].y);
        }

        //vario il colore delle stelle ad ogni fotogramma
        //in modo che le stelle del cielo stellato 1 da bianche
        //gradualmente diventino nere
        //e viceversa. Il colore delle stelle del cielo stellato 2
        //gradualmente da nere diventino bianche
        //cielo stellato1
        int rgb;
        if (n > 50) {
            rgb = (n % 50) * 5;
        } else {
            rgb = (((n - 1) % 50) - 50) * -5;
        }

        stelleColor = new Color(rgb, rgb, rgb);
        g.setColor(stelleColor);
        for (int i = 111; i < 222; i++) {
            g.drawLine(stelle1[i].x, stelle1[i].y, stelle1[i].x, stelle1[i].y);
        }

        //cielo stellato2
        if (n < 50) {
            rgb = (n % 50) * 5;
        } else {
            rgb = (((n + 1) % 50) - 50) * -5;
        }

        stelleColor = new Color(rgb, rgb, rgb);
        g.setColor(stelleColor);
        for (int i = 111; i < 222; i++) {
            g.drawLine(stelle2[i].x, stelle2[i].y, stelle2[i].x, stelle2[i].y);
        }

        int j;
        if (scritte) {
            j = i;
        } else {
            j = 0;
        }
        // scrivi nel buffer la scritta in Rosso 
        g.setColor(Color.red);
        g.setFont(new Font("Bold", Font.PLAIN, 50 + j));
        g.drawString("Space Invaders", 100 + j, 100 + j);
        g.setFont(new Font("Bold", Font.PLAIN, 10 + j));
        g.drawString("          by Federico Comerci", 115 + j + j, 115 + j + j);
        H += 1; //altezza alieni
        if (H > 500) { // una volta raggiunto il fondo dello schermo
            H = 0;     // riporta gli alieni nella parte alta dello schermo
        }
        for (int k = 0; k < 400; k += 40) { // numero di alieni da disegnare
            g.drawImage(alien, n * 4 + k, H, 50, 50, null);
        }
        if (leftPressed) {
            Sx -= 10;
        }
        if (rightPressed) {
            Sx += 10;
        }
        if (Sx > 750) {
            Sx = 750;
        }
        if (Sx < 0) {
            Sx = 0;
        }

        for (int bn = 0; bn < proiettiliN; bn++) {
            int updatedY = proiettili[bn].y - 10;
            g.drawLine(proiettili[bn].x, proiettili[bn].y, proiettili[bn].x, updatedY);
            proiettili[bn].y = updatedY;
        }
        if (firePressed) {
            System.out.println(proiettiliN + " : " + Sx);
            if (reuse == false) {
                proiettili[proiettiliN] = new Point();
            }
            proiettili[proiettiliN].x = Sx + 25;
            proiettili[proiettiliN].y = 550; //posizione verticale iniziale proiettile
            proiettiliN++;
            if (proiettiliN >= 99) {
                proiettiliN = 0;
                reuse = true;
            }
            firePressed = false;
        }

        g.drawImage(ship, Sx, 550, 50, 50, null);
        //rilascia risorse create per la composizione di questa immagine
        g.dispose();
        //visualizza la nuova immagine sullo schermo
        strategy.show();
    }
}
