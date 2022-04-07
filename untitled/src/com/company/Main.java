package com.company;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.SoundClip;

public class Main extends GraphicsProgram {

    private final String[] names = {"caitlyn", "derpsuo", "jinx", "kindred", "lee_sin", "lux", "taric", "teemo", "vi", "ziggs", "zombi"};//Lista de personages presentes en la pantalla
    private final String path_image = "C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";//Directorio de las imagenes
    private final String path_music = "C:\\Users\\wade079\\Desktop\\emogisdead\\music\\";//Directorio de las pistas de audio


    public static void main(String[] args) {//main
        new Main().start(args);
    }

    public void run() {//Donde se arranca el juego e implementamos sonido, imagenes con Acm Graphics

        //metodos usados
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
        GImage game_ini = new GImage(path_image + "ini.gif");//definim la ruta i el nom del gif
        game_ini.setSize(960, 680);//definim mida del gif
        add(game_ini, 0, 0);//definim posicio gif

        SoundClip start = new SoundClip(path_music + "startup.wav");//audio inici
        start.setVolume(1);//volum audio inici
        start.play();//play a l'audio
        try {
            Thread.sleep(14500);//Sleep entre la transicion de la pantalla de carga y el inicio del juego
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game_ini.setSize(0, 0);//per treure el gif inicial
        add(game_ini, 80, 0);//
        start.stop();//parar l'audio

    }

    private void maping() {

        //metode per grafics de la partida
        GImage cielo = new GImage(path_image + "cielo.jpg");//imagen del cielo azul
        cielo.setSize(400, 73);//mida imatge
        add(cielo, 0, 0);//posicio imatge

        //Imagen del cielo rojo (zombis)
        GImage cielo_red = new GImage(path_image + "cielo_red.jpg");
        cielo_red.setSize(350, 73);
        add(cielo_red, 605, 0);

        //Fondo al lado del contador de los sanos
        GImage fondo = new GImage(path_image + "fondo_sanos.png");
        fondo.setSize(350, 75);
        add(fondo, 0, 0);

        //Fondo al lado del contador de zombis
        GImage fondo1 = new GImage(path_image + "fondo.png");
        fondo1.setSize(350, 75);
        add(fondo1, 595, 0);

        //Imagen del "versus" animado
        GImage vs = new GImage(path_image + "vs.gif");
        vs.setSize(70, 70);
        add(vs, 466, 0);

        //Imagen de fondo por donde se desplazan los personajes
        GImage terreno = new GImage(path_image + "terreno.jpg");
        terreno.setSize(950, 550);
        add(terreno, 0, 70);

    }

    private void lanzallamas() {

        //metode per limits del mapa
        for (int i = 0; i < 43; i++) {//bucle per rodejar el mapa de flames
            GImage fuego_izq = new GImage(path_image + "fuego.gif");//ruta+nom imatge
            GImage fuego_der = new GImage(path_image + "fuego.gif");
            GImage fuego_arr = new GImage(path_image + "fuego.gif");
            GImage fuego_aba = new GImage(path_image + "fuego.gif");
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
        int cont_zombie = 1;//contador de zombis inicializado a 1 porque empezamos con 1 zombi
        int ciclo = 0;//contador de ciclos para eficiencia del programa
        int contador_sanos;//contador de personajes sanos

        int contador_zombi;//el adri lo explicara

        contador_sanos = names.length - cont_zombie;//inicializamos el contador de sanos

        GLabel sanos = new GLabel(toString(contador_sanos));//mostrem comptador per persones sanes

        contador_zombi = cont_zombie;
        GLabel zombi = new GLabel(toString(contador_zombi));//mostrem contador per zombies

        while (cont_zombie <= emojis.length - 1) {// mentres hi hagi persones sanes

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //actualizamos las posiciones de todos los personajes en la pantalla eso genera el efecto de movimiento
            for (character emoji : emojis) {

                add(emoji.getImage(), emoji.getPos_x(), emoji.getPos_y());
                emoji.move();
            }

            //cada 5 ciclos verificamos colisiones
            if (ciclo >= 5) {
                //Se comparan todos los personajes para ver si alguno toca con otro
                for (int j = 0; j < emojis.length - 1; j++) {
                    for (int k = j + 1; k < emojis.length; k++) {

                        //Verificamos si colisiona sobre un margen de coordenadas x e Y ej. x hasta x+80 (80 que es la anchura de la imagen)
                        if ((emojis[j].getPos_x() >= emojis[k].getPos_x()) && (emojis[j].getPos_x() <= emojis[k].getPos_x() + 80)
                                &&
                                (emojis[j].getPos_y() >= emojis[k].getPos_y()) && (emojis[j].getPos_y() <= emojis[k].getPos_y() + 80)) {

                            //Si hay colision comparamos quien ha tocado

                            //Si son 2 sanos
                            if (emojis[j].getStatus() && emojis[k].getStatus()) {
                                emojis[k].check_collision();//Hace cambiar la direccion de movimiento
                            }

                            //Si son 2 zombis
                            if (!emojis[j].getStatus() && !emojis[k].getStatus()) {
                                emojis[k].check_collision();//Hace cambiar la direccion de movimiento
                            }

                            //Si es un sano y un zombi
                            if (emojis[j].getStatus() && !emojis[k].getStatus()) {
                                emojis[k].setStatus();//El personaje sano se convierte en zombi
                                emojis[k].setZombie();//Actualizamos la imagen por la del zombi
                                emojis[k].check_collision();//Cambiamos direccion de movimiento

                                cont_zombie++;//incrementamos zombis
                                contador_sanos--;//quitamos un sano
                                contador_zombi++;//Eso es del Adri

                                damage();//Sonido de cuando uno se convierte en zombi
                            }

                            //Si es un zombi y un sano
                            if (!emojis[j].getStatus() && emojis[k].getStatus()) {
                                emojis[j].setStatus();//El personaje sano se convierte en zombi
                                emojis[j].setZombie();//Actualizamos la imagen por la del zombi
                                emojis[k].check_collision();//Cambiamos direccion de movimiento
                                cont_zombie++;//incrementamos zombis
                                contador_zombi++;//quitamos un sano
                                contador_sanos--;//Eso es del Adri

                                damage();//Sonido de cuando uno se convierte en zombi
                            }
                        }
                    }
                    ciclo = 0;//Reseteamos los ciclos
                }
            }
            ciclo++;//Incrementamos un ciclo

            //Contador de sanos
            sanos.setLabel(toString(contador_sanos));
            sanos.setFont("SansSerif-50");
            add(sanos, 410, 55);//afegim el contador a la posicio


            //Contador de infectados
            zombi.setLabel(toString(contador_zombi));
            zombi.setFont("SansSerif-50");
            add(zombi, 550, 55);

        }//Final del while


        //Paramos el juego durante 1500 millis
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

        SoundClip fin = new SoundClip(path_music + "final.wav");//Sonido de fin
        SoundClip game_over = new SoundClip(path_music + "game_over.wav");//Sonido game over
        fin.setVolume(0.5);
        game_over.setVolume(0.5);

        fin.play();
        game_over.play();
        GImage game_overi = new GImage(path_image + "game_over.gif");//Mostramos la pantalla final
        game_overi.setSize(960, 680);//
        add(game_overi, 0, 0);
    }

    private String toString(int contador_sanos) {
        return String.valueOf(contador_sanos);
    }
}

