package com.company;
import acm.graphics.*;
import acm.program.*;

public class Main extends GraphicsProgram {

    public static void main(String[] args) {
        new Main().start(args);
    }

    public void run() {
        int ancho = 80;
        int alto = 80;

        setSize(900, 630);//tamaño del canvas

        GImage cielo = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\cielo.jpg" );
        cielo.setSize(400,73);
        add(cielo, 0,  0);

        GImage cielo_red = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\cielo_red.jpg" );
        cielo_red.setSize(400,73);
        add(cielo_red, 510,  0);



        GImage fondo = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\fondo_sanos.png");
        fondo.setSize(350,75);
        add(fondo, 0,  0);
        GImage fondo1 = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\fondo.png");
        fondo1.setSize(350,75);
        add(fondo1, 550,  0);
        GImage vs = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\vs.png" );
        vs.setSize(200,80);
        add(vs, 350,  0);

        GImage terreno = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\terreno.jpg" );
        terreno.setSize(900,500);
        add(terreno, 0,  73);




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
        GImage caitlyn = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\caitlyn.png");
        caitlyn.setSize(alto,ancho);
        add(caitlyn, 0,  85);

        GImage derpsuo = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\derpsuo.png");
        derpsuo.setSize(alto,ancho);
        add(derpsuo, 80,  85);

        GImage jinx = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\jinx.png");
        jinx.setSize(alto,ancho);
        add(jinx, 160,  85);

        GImage kindred = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\kindred.png");
        kindred.setSize(alto,ancho);
        add(kindred, 230,  85);

        GImage lee_sin = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\lee_sin.png");
        lee_sin.setSize(alto,ancho);
        add(lee_sin, 310,  85);

        GImage lux = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\lux.png");
        lux.setSize(alto,ancho);
        add(lux, 390,  85);

        GImage taric = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\taric.png");
        taric.setSize(alto,ancho);
        add(taric, 470,  85);

        GImage teemo = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\teemo.png");
        teemo.setSize(alto,ancho);
        add(teemo, 550,  85);

        GImage vi = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\vi.png");
        vi.setSize(alto,ancho);
        add(vi, 630,  85);

        GImage ziggs = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\ziggs.png");
        ziggs.setSize(alto,ancho);
        add(ziggs, 700,  85);

        GImage zombi = new GImage("C:\\Users\\wade079\\Desktop\\emogisdead\\img\\zombi.png");
        zombi.setSize(alto,ancho);
        add(zombi, 400,  480);






    }
    public int getHeight() {
        return 80;
    }
    public int getWidth() {
        return 0;
    }
}