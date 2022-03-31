package com.company;
import acm.graphics.*;
import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram {

    private String path ="C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";
    private String [] names = {"caitlyn","derpsuo","jinx","kindred","lee_sin","lux","taric","teemo","vi","ziggs","zombi"};

    public static void main(String[] args) {
        new Main().start(args);
    }

    public void run() {
        int ancho = 80;
        int alto = 80;

        //Instanciamos los personajes
        character[] emojis = new character[names.length];
        for (int i = 0; i < emojis.length; i++) {
            emojis[i]=new character(names[i]);
            emojis[i].setPos();

        }

        setSize(900, 630);//tamaÃ±o del canvas

        GImage cielo = new GImage(path+"cielo.jpg" );
        cielo.setSize(400,73);
        add(cielo, 0,  0);

        GImage cielo_red = new GImage(path+"cielo_red.jpg" );
        cielo_red.setSize(400,73);
        add(cielo_red, 510,  0);

        GImage fondo = new GImage(path+"fondo_sanos.png");
        fondo.setSize(350,75);
        add(fondo, 0,  0);
        GImage fondo1 = new GImage(path+"fondo.png");
        fondo1.setSize(350,75);
        add(fondo1, 550,  0);
        GImage vs = new GImage(path+"vs.png" );
        vs.setSize(200,80);
        add(vs, 350,  0);
        GImage terreno = new GImage(path+"terreno.jpg" );
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

        while (true){

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < emojis.length; i++) {

                add(emojis[i].getImage(),emojis[i].getPos_x(),emojis[i].getPos_y());
                emojis[i].move();
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