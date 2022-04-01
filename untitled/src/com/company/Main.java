package com.company;
import acm.graphics.*;
import acm.program.GraphicsProgram;


public class Main extends GraphicsProgram {

    private final String [] names = {"caitlyn","derpsuo","jinx","kindred","lee_sin","lux","taric","teemo","vi","ziggs","zombi"};

    public static void main(String[] args) {
        new Main().start(args);
    }

    public void run() {
      
        //Instanciamos los personajes
        character[] emojis = new character[names.length];
        for (int i = 0; i < emojis.length; i++) {
            emojis[i]=new character(names[i]);
            emojis[i].setPos();

        }

        setSize(960, 680);//tamaÃ±o del canvas

        String path = "C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";
        GImage cielo = new GImage(path +"cielo.jpg" );
        cielo.setSize(400,73);
        add(cielo, 0,  0);

        GImage cielo_red = new GImage(path +"cielo_red.jpg" );
        cielo_red.setSize(350,73);
        add(cielo_red, 605,  0);

        GImage fondo = new GImage(path +"fondo_sanos.png");
        fondo.setSize(350,75);
        add(fondo, 0,  0);
        GImage fondo1 = new GImage(path +"fondo.png");
        fondo1.setSize(350,75);
        add(fondo1, 595,  0);
        GImage vs = new GImage(path +"vs.gif" );
        vs.setSize(260,80);
        add(vs, 375,  0);
        GImage terreno = new GImage(path +"terreno.jpg" );
        terreno.setSize(950,550);
        add(terreno, 0,  70);

        for (int i = 0; i < 50; i++) {
            GImage fuego_isq = new GImage(path + "pinchos.gif");
            GImage fuego_der = new GImage(path + "pinchos.gif");
            GImage fuego_arr = new GImage(path + "pinchos.gif");
            GImage fuego_ava = new GImage(path + "pinchos.gif");
            int fuego_ancho = 180;
            int fuego_alto = 50;

            int y = 0;
            y = y + i * 40;
          add(fuego_isq, 840, y + 55);
            fuego_isq.setSize(fuego_ancho, fuego_alto);
           add(fuego_der, -80, y + 55);
            fuego_der.setSize(fuego_ancho, fuego_alto);
            add(fuego_arr, y +-840,  55);
            fuego_arr.setSize(fuego_ancho, fuego_alto);
          add( fuego_ava, y +-80,  574);
            fuego_ava.setSize(fuego_ancho, fuego_alto);
        }



        /*
        contador de caracter buenos
         */

        int cont_zombie=1;

        int ciclo = 0;
        while (cont_zombie<= emojis.length-1) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < emojis.length; i++) {

                add(emojis[i].getImage(), emojis[i].getPos_x(), emojis[i].getPos_y());

            }
            for (int i = 0; i < emojis.length; i++) {
                emojis[i].move();
            }

            if (ciclo == 5) {
                for (int j = 0; j < emojis.length - 1; j++) {
                    for (int k = j + 1; k < emojis.length; k++) {

                        if ((emojis[j].getPos_x() >= emojis[k].getPos_x()) && (emojis[j].getPos_x() <= emojis[k].getPos_x() + 80)
                                &&
                                (emojis[j].getPos_y() >= emojis[k].getPos_y()) && (emojis[j].getPos_y() <= emojis[k].getPos_y() + 80)) {


                            if (emojis[j].getStatus() && emojis[k].getStatus()) {
                                emojis[k].check_collision();

                            }

                            if (!emojis[j].getStatus() && !emojis[k].getStatus()) {
                                emojis[k].check_collision();
                            }

                            if (emojis[j].getStatus() && !emojis[k].getStatus()) {
                                emojis[k].setStatus(true);
                                emojis[k].setZombie();
                                emojis[k].check_collision();
                                cont_zombie++;

                            }
                            if (!emojis[j].getStatus() && emojis[k].getStatus()) {
                                emojis[j].setStatus(true);
                                emojis[j].setZombie();
                                emojis[k].check_collision();
                                cont_zombie++;
                            }
                        }
                    }
                    ciclo = 0;
                }
            }
            ciclo++;
        }
    }

    public int getHeight() {
        return 105;
    }
    public int getWidth() {
        return 0;
    }



}