package com.company;
import acm.graphics.*;
/*Paul i Albert */

public class character {


    int min = -15;
    int max = 15;

    int size_x=80;
    int size_y=80;

    protected int pos_x;
    protected int pos_y;

    GImage image;

    private boolean is_zombie;

    public character(String name){
        image = new GImage("img\\"+name+".png");
        image.setSize(size_x,size_y);
    }

    public GImage getImage(){
        return image;
    }

    public void move(){

        int mover_x = getRandomNumber(min,max);
        int mover_y = getRandomNumber(min,max);

        this.pos_x=this.pos_x+mover_x;
        this.pos_y=this.pos_y+mover_y;

    }

    public boolean getStatus(){
        return is_zombie;
    }

    public void setPos(){
        this.pos_x=getRandomNumber(0,850);
        this.pos_y=getRandomNumber(80,580);//Por capcelera en canvas
    }

    public int getPos_x() {

        if (pos_x <=0){
            pos_x=0;
        }
        if (pos_x>=850){
            pos_x=850;
        }

        return pos_x;
    }
    public int getPos_y(){

        if (pos_y<=75){
            pos_y=75;
        }
        if (pos_y>=520){
            pos_y=520;
        }

        return pos_y;
    }

    public int getSize_x(){
        return size_x;
    }
    public int getSize_y(){
        return size_y;
    }



    public int getRandomNumber(int min, int max) {//generar numeros aleatorios
        return (int) ((Math.random() * (max - min)) + min);
    }
}

