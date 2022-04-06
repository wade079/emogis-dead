package com.company;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;

public class Main extends GraphicsProgram {

    private final String[] names = {"caitlyn", "derpsuo", "jinx", "kindred", "lee_sin", "lux", "taric", "teemo", "vi", "ziggs", "zombi"};
    private final String path = "C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";
    private final String path_music = "C:\\Users\\wade079\\Desktop\\emogisdead\\music\\";

    public static void main(String[] args) {
        new Main().start(args);
    }


    public void run() {
        canvas();
        ini();
        maping();
        lanzallamas();
        musicfondo();
        damage();
        fin_partida();
    }

    private void canvas() {

        setSize(960, 680);//tamaÃ±o del canvas
    }

    private void ini() {
        GImage game_ini = new GImage(path + "ini.gif");
        game_ini.setSize(960, 680);//
        add(game_ini, 0, 0);

        SoundClip start = new SoundClip(path_music + "startup.wav");
        start.setVolume(1);
        start.play();
        try {
            Thread.sleep(14500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game_ini.setSize(0, 0);//
        add(game_ini, 80, 0);
        start.stop();

    }

    private void maping() {


        GImage cielo = new GImage(path + "cielo.jpg");
        cielo.setSize(400, 73);
        add(cielo, 0, 0);

        GImage cielo_red = new GImage(path + "cielo_red.jpg");
        cielo_red.setSize(350, 73);
        add(cielo_red, 605, 0);

        GImage fondo = new GImage(path + "fondo_sanos.png");
        fondo.setSize(350, 75);
        add(fondo, 0, 0);
        GImage fondo1 = new GImage(path + "fondo.png");
        fondo1.setSize(350, 75);
        add(fondo1, 595, 0);
        GImage vs = new GImage(path + "vs.gif");
        vs.setSize(70, 70);
        add(vs, 466, 0);
        GImage terreno = new GImage(path + "terreno.jpg");
        terreno.setSize(950, 550);
        add(terreno, 0, 70);

    }

    private void lanzallamas() {

              for (int i = 0; i < 43; i++) {
            GImage fuego_isq = new GImage(path + "fuego.gif");
            GImage fuego_der = new GImage(path + "fuego.gif");
            GImage fuego_arr = new GImage(path + "fuego.gif");
            GImage fuego_ava = new GImage(path + "fuego.gif");
            int fuego_ancho = 180;
            int fuego_alto = 50;

            int y = 0;
            y = y + i * 40;
            add(fuego_isq, 840, y + 55);
            fuego_isq.setSize(fuego_ancho, fuego_alto);
            add(fuego_der, -80, y + 55);
            fuego_der.setSize(fuego_ancho, fuego_alto);
            add(fuego_arr, y - 840, 55);
            fuego_arr.setSize(fuego_ancho, fuego_alto);
            add(fuego_ava, y - 80, 574);
            fuego_ava.setSize(fuego_ancho, fuego_alto);
        }
    }

    private void musicfondo() {
        SoundClip musicfondo = new SoundClip(path_music + "background_music.wav");
        musicfondo.setVolume(0.2);
        musicfondo.play();
        caracter();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        musicfondo.stop();
    }

    private void caracter() {

        //Instanciamos los personajes
        character[] emojis = new character[names.length];
        for (int i = 0; i < emojis.length; i++) {
            emojis[i] = new character(names[i]);
            emojis[i].setPos();
        }

        int cont_zombie = 1;
        int ciclo = 0;
        int contador_sanos;
        int contador_zombi;

        contador_sanos = names.length - cont_zombie;
        GLabel sanos = new GLabel(toString(contador_sanos));

        contador_zombi = cont_zombie;
        GLabel zombi = new GLabel(toString(contador_zombi));

        while (cont_zombie <= emojis.length - 1) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (character emoji : emojis) {

                add(emoji.getImage(), emoji.getPos_x(), emoji.getPos_y());
                emoji.move();
            }

            if (ciclo >= 5) {
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
                                emojis[k].setStatus();
                                emojis[k].setZombie();
                                emojis[k].check_collision();
                                cont_zombie++;
                                contador_sanos--;
                                contador_zombi++;
                                damage();

                            }
                            if (!emojis[j].getStatus() && emojis[k].getStatus()) {
                                emojis[j].setStatus();
                                emojis[j].setZombie();
                                emojis[k].check_collision();
                                cont_zombie++;
                                contador_zombi++;
                                contador_sanos--;
                                damage();

                            }
                        }
                    }
                    ciclo = 0;
                }
            }
            ciclo++;
            sanos.setLabel(toString(contador_sanos));
            sanos.setFont("SansSerif-50");
            add(sanos, 410, 55);

            zombi.setLabel(toString(contador_zombi));
            zombi.setFont("SansSerif-50");
            add(zombi, 550, 55);

        }
    }

    private void damage() {
        SoundClip damage = new SoundClip(path_music + "damage.wav");
        damage.setVolume(0.5);
        damage.play();

    }

    private void fin_partida() {

        SoundClip fin = new SoundClip(path_music + "final.wav");
        SoundClip game_over = new SoundClip(path_music + "game_over.wav");
        fin.setVolume(0.5);
        game_over.setVolume(1);

        fin.play();
        game_over.play();
        GImage game_overi = new GImage(path + "game_over.gif");
        game_overi.setSize(960, 680);//
        add(game_overi, 0, 0);
    }

    private String toString(int contador_sanos) {
        return String.valueOf(contador_sanos);
    }

}