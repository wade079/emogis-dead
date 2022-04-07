package com.company;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;

public class Main extends GraphicsProgram {

    private final String[] names = {"caitlyn", "derpsuo", "jinx", "kindred", "lee_sin", "lux", "taric", "teemo", "vi", "ziggs", "zombi"};
    private final String path = "C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";
    private final String path_music = "C:\\Users\\wade079\\Desktop\\emogisdead\\music\\";


    public static void main(String[] args) {//main
        new Main().start(args);
    }

    public void run() {//metode run
        canvas();
        ini();
        maping();
        lanzallamas();
        damage();
        musicfondo();
        fin_partida();
    }

    private void canvas() {//metode per definir mida al canvas

        setSize(960, 680);//tamaño del canvas
    }

    private void ini() {//metode "ini" inici de partida
        GImage game_ini = new GImage(path + "ini.gif");//definim la ruta i el nom del gif
        game_ini.setSize(960, 680);//definim mida del gif
        add(game_ini, 0, 0);//definim posicio gif

        SoundClip start = new SoundClip(path_music + "startup.wav");//audio inici
        start.setVolume(1);//volum audio inici
        start.play();//play a l'audio
        try {
            Thread.sleep(14500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game_ini.setSize(0, 0);//per treure el gif inicial
        add(game_ini, 80, 0);//
        start.stop();//parar l'audio

    }

    private void maping() {//metode per grafics de la partida
        GImage cielo = new GImage(path + "cielo.jpg");//imatge
        cielo.setSize(400, 73);//mida imatge
        add(cielo, 0, 0);//posicio imatge

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

    private void lanzallamas() {//metode per limits del mapa
        for (int i = 0; i < 43; i++) {//bucle per rodejar el mapa
            GImage fuego_izq = new GImage(path + "fuego.gif");//ruta+nom imatge
            GImage fuego_der = new GImage(path + "fuego.gif");
            GImage fuego_arr = new GImage(path + "fuego.gif");
            GImage fuego_aba = new GImage(path + "fuego.gif");
            int fuego_ancho = 180;//amplada del mapa
            int fuego_alto = 50;//altura del mapa

            int y = 0;
            y = y + i * 40;
            add(fuego_izq, 840, y + 55);//posicio imatge
            fuego_izq.setSize(fuego_ancho, fuego_alto);//mida imatge foc
            add(fuego_der, -80, y + 55);
            fuego_der.setSize(fuego_ancho, fuego_alto);
            add(fuego_arr, y - 840, 55);
            fuego_arr.setSize(fuego_ancho, fuego_alto);
            add(fuego_aba, y - 80, 574);
            fuego_aba.setSize(fuego_ancho, fuego_alto);
        }
    }

    private void musicfondo() {//metode per la musica

        SoundClip musicfondo = new SoundClip(path_music + "background_music.wav");//ruta +nom
        musicfondo.setVolume(0.2);//volum musica
        musicfondo.play();//inici musica
        caracter();//crida metode caracter per instanciar-los

        musicfondo.stop();//para la musica
    }

    private void caracter() {//metode caracter

        //Instanciamos los personajes
        character[] emojis = new character[names.length];
        for (int i = 0; i < emojis.length; i++) {//bucle per crear els objectes characters
            emojis[i] = new character(names[i]);
            emojis[i].setPos();//apareixen a la posicio aleatoria del metode setPos
        }
        int cont_zombie = 1;//variables
        int ciclo = 0;
        int contador_sanos;
        int contador_zombi;

        contador_sanos = names.length - cont_zombie;
        GLabel sanos = new GLabel(toString(contador_sanos));//comptador per persones sanes

        contador_zombi = cont_zombie;
        GLabel zombi = new GLabel(toString(contador_zombi));//contador per zombies

        while (cont_zombie <= emojis.length - 1) {// mentres hi hagi persones sanes

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
            add(sanos, 410, 55);//afegim el contador a la posicio

            zombi.setLabel(toString(contador_zombi));
            zombi.setFont("SansSerif-50");
            add(zombi, 550, 55);

        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void damage() {//metode pel soroll de contagi
        SoundClip damage = new SoundClip(path_music + "damage.wav");//ruta + nom arxiu
        damage.setVolume(0.5);//volum
        damage.play();//inici audio

    }

    private void fin_partida() {//fi de la partida

        SoundClip fin = new SoundClip(path_music + "final.wav");
        SoundClip game_over = new SoundClip(path_music + "game_over.wav");
        fin.setVolume(0.5);
        game_over.setVolume(0.5);

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

