package com.company;

import acm.graphics.*;
/*Paul i Albert */

public class character {


    int mover_x = 1;
    int mover_y = -1;

    int size_x = 80;
    int size_y = 80;

    protected int pos_x;
    protected int pos_y;
    public final String path = "C:\\Users\\wade079\\Desktop\\emogisdead\\img\\";
    GImage image;

    protected boolean is_zombie=false;

    public character(String name){
        image = new GImage(path+name+".png");

        if (name.equals("zombi")){
            is_zombie=true;

        }

        if (is_zombie) {
            image = new GImage(path+"zombi.png");

        }
        image.setSize(size_x, size_y);
    }

    public GImage getImage() {
        return image;
    }

    public void setZombie(){

        image.setImage(path+"zombi.png");
        image.setSize(size_x, size_y);
    }

    public void move() {


        this.pos_x = this.pos_x + mover_x;
        this.pos_y = this.pos_y + mover_y;

        check_wall(this.pos_x, this.pos_y);


    }

    public boolean getStatus() {
        return is_zombie;
    }
    public void setStatus(){
        this.is_zombie=true;
    }

    public void setPos() {
        this.pos_x = getRandomNumber(0, 930);
        this.pos_y = getRandomNumber(80, 580);//Por capcelera en canvas
    }

    public int getPos_x() {

        if (pos_x <= 0) {
            pos_x = 0;
        }
        if (pos_x >= 930 - size_x) {
            pos_x =930 - size_x;
        }

        return pos_x;
    }

    public int getPos_y() {

        if (pos_y <= 90) {
            pos_y = 90;
        }
        if (pos_y >= 600 - size_y) {
            pos_y = 600 - size_y;
        }

        return pos_y;
    }

    public void check_wall(int pos_x, int pos_y) {

        if (pos_x <= 0) {
            this.mover_x = 1;
        }
        if (pos_x >= 930 - size_x) {
            this.mover_x = -1;
        }

        if (pos_y <= 90) {
            this.mover_y = 1;
        }
        if (pos_y >= 600 - size_y) {
            this.mover_y = -1;
        }

    }

    public void check_collision(){

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
    }
}

