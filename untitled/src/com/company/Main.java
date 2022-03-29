package com.company;
import acm.graphics.*;
import acm.program.*;

public class Main extends GraphicsProgram {

    public static void main(String[] args) {
        new Main().start(args);
    }

    public void run() {

        setSize(900, 630);//tamaño del canvas
        GImage fondo = new GImage("emogisdead\\img\\fondo.png");
        fondo.setSize(350,75);
        add(fondo, 0,  0);

        GImage fondo1 = new GImage("emogisdead\\img\\fondo.png");
        fondo1.setSize(350,75);
        GImage vs = new GImage("emogisdead\\img\\vs.png" );
        vs.setSize(200,80);

        add(fondo1, 550,  0);
        add(vs, 350,  0);






        for (int i = 0; i < 900; i++) {
            for (int j = 0; j < 1; j++) {

                GLabel label = new GLabel("-");
                label.setFont("SansSerif-30");
                double x = (getWidth());
                double y = (getHeight());
                x = x + i;
                y = y + j * 80;
                add(label, x, y);
            }
        }







    }
    public int getHeight() {
        return 80;
    }
    public int getWidth() {
        return 0;
    }
}