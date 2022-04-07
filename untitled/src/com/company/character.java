package com.company;

import acm.graphics.*;
/*Paul i Albert */

public class character {


    int mover_x = 1;//Coordenadas de movimiento x
    int mover_y = -1;//Coordenadas de movimiento Y

    int size_x = 80;//Tamaño del personaje
    int size_y = 80;//

    //Posicion inicial no definida
    protected int pos_x;
    protected int pos_y;

    private final String path = "C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";
    GImage image;

    protected boolean is_zombie=false;

    //metodo constructor de personaje
    public character(String name){
        image = new GImage(path+name+".png");//Atribuimos una imagen al personaje

        if (name.equals("zombi")){//Si el nombre es zombi actualizamos el status del personaje a zombi
            is_zombie=true;

        }

        if (is_zombie) {//Si es un zombi le ponemos la imagen de zombi
            image = new GImage(path+"zombi.gif");

        }
        image.setSize(size_x, size_y);//Atribuimos el tamaño de la imagen
    }

    public GImage getImage() {
        return image;
    }//Retorna la imagen del personage

    public void setZombie(){//Metodo para convertir en zombi

        image.setImage(path+"zombi.gif");
        image.setSize(size_x, size_y);
    }

    public void move() {//Metodo de mover el caracter


        this.pos_x = this.pos_x + mover_x;
        this.pos_y = this.pos_y + mover_y;

        check_wall(this.pos_x, this.pos_y);//Metodo para comprovar si hay colision con el borde del juego


    }

    public boolean getStatus() {
        return is_zombie;
    }//Metodo que nos devuelve el estado del personaje

    public void setStatus(){//Para convertir el personaje en zombi
        this.is_zombie=true;
    }

    public void setPos() {//Metodo para atribuir la posicion de forma aleatoria a los personajes en el area de juego
        this.pos_x = getRandomNumber(0, 930);
        this.pos_y = getRandomNumber(80, 580);//Por capcelera en canvas
    }

    public int getPos_x() {//Nos retorna la posicion actual del personaje

        if (pos_x <= 0) {//Comprobacion de choque contra pared izquierda y que no pase de alli
            pos_x = 0;
        }
        if (pos_x >= 930 - size_x) {//Comprobacion de choque de pared derecho y que no pase de alli
            pos_x =930 - size_x;
        }

        return pos_x;
    }

    public int getPos_y() {

        if (pos_y <= 90) {//Comprobacion de choque de pared superior y que no pase de alli
            pos_y = 90;
        }
        if (pos_y >= 600 - size_y) {//Comprobacion de choque de pared y que no pase de alli
            pos_y = 600 - size_y;
        }

        return pos_y;
    }

    public void check_wall(int pos_x, int pos_y) {//Metodo para cambiar la direccion de movimiento del personaje

        if (pos_x <= 0) {//Si choca hacia la izquierda
            this.mover_x = 1;//Que valla hacia la derecha
        }
        if (pos_x >= 930 - size_x) {//Si choca a la derecha
            this.mover_x = -1;//Que vaya a la izquierda
        }

        if (pos_y <= 90) {//Si choca arriba
            this.mover_y = 1;//Que vaya a bajo
        }
        if (pos_y >= 600 - size_y) {//Si choca a bajo
            this.mover_y = -1;//Que vaya arriba
        }

    }

    public void check_collision(){//Metodo para cambiar de direccion al personaje cuando choca con otro

        if (mover_x==1){
            mover_x=-1;
        }else if (mover_x==-1){
            mover_x=1;
        }

        if (mover_y==1){
            mover_y=-1;
        }else if (mover_y==-1){
            mover_y=1;
        }

    }



    public int getRandomNumber(int min, int max) {//generar numeros aleatorios
        return (int) ((Math.random() * (max - min)) + min);
    }//Metodo de aleatoriedad
}

