package com.game.ptiles;

import android.animation.AnimatorSet;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class level_mha5 extends MainActivity{

    //Le debut de toutes les classes representant des niveaux est repetitif : recuperation des paramètres du constructeur pour initialiser les variables de classe
    //et presence de la procédure : Fall() qui permet de parcourir 1 element du tableau des niveaux et de lui appliquer l'animation qui le fera tomber de haut en bas

    public Button note1,note2,note3,note4,note11,note22,note33,note44,note111,note222,note333,note444;
    public Button note1Img,note2Img,note3Img,note4Img,note11Img,note22Img,note33Img,note44Img,note111Img,note222Img,note333Img,note444Img;

    public Button long1,long2,long3,long4;
    public Button long1Img,long2Img,long3Img,long4Img;

    public RelativeLayout mainLayout2;
    public ConstraintLayout mainLayout;


    public AnimatorSet animSetXY1,animSetXY2,animSetXY3,animSetXY4,
            animSetXY11,animSetXY22,animSetXY33,animSetXY44,
            animSetXY111,animSetXY222,animSetXY333,animSetXY444,
            animSetXYL1,animSetXYL2,animSetXYL3,animSetXYL4;


    public MediaPlayer musique;

    public int speedNote;
    public int speedLong;
    public float multiplier;

    public int indice;
    public int[] tab_mha5;
    public boolean[] arret;
    public Animation transition;
    public boolean repetB;
    public int duration_transition;


    public level_mha5(Button note1, Button note11, Button note111, Button note2, Button note22, Button note222, Button note3, Button note33, Button note333, Button note4, Button note44, Button note444,
                      Button note1Img, Button note11Img, Button note111Img, Button note2Img, Button note22Img, Button note222Img, Button note3Img, Button note33Img, Button note333Img, Button note4Img, Button note44Img, Button note444Img,
                      Button long1, Button long2, Button long3, Button long4,
                      Button long1Img, Button long2Img, Button long3Img, Button long4Img,
                      RelativeLayout mainLayout2,
                      ConstraintLayout mainLayout,
                      AnimatorSet animSetXY1, AnimatorSet animSetXY2, AnimatorSet animSetXY3, AnimatorSet animSetXY4,
                      AnimatorSet animSetXY11, AnimatorSet animSetXY22, AnimatorSet animSetXY33, AnimatorSet animSetXY44,
                      AnimatorSet animSetXY111, AnimatorSet animSetXY222, AnimatorSet animSetXY333, AnimatorSet animSetXY444,
                      AnimatorSet animSetXYL1, AnimatorSet animSetXYL2, AnimatorSet animSetXYL3, AnimatorSet animSetXYL4,

                      int speedNote,
                      int speedLong,
                      float multiplier,
                      int indice,
                      int[] tab_mha5,
                      boolean[] arret, Animation transition, MediaPlayer musique,boolean repetB


    ){

        this.note1 = note1;this.note2= note2;this.note3= note3;this.note4= note4;this.note11= note11;this.note22= note22;this.note33= note33;this.note44= note44;this.note111= note111;this.note222= note222;this.note333= note333;this.note444= note444;

        this.note1Img = note1Img;this.note2Img= note2Img;this.note3Img= note3Img;this.note4Img= note4Img;this.note11Img= note11Img;this.note22Img= note22Img;this.note33Img= note33Img;this.note44Img= note44Img;this.note111Img= note111Img;this.note222Img= note222Img;this.note333Img= note333Img;this.note444Img= note444Img;

        this.long1 = long1;this.long2= long2;this.long3= long3;this.long4= long4;
        this.long1Img = long1Img;this.long2Img= long2Img;this.long3Img= long3Img;this.long4Img= long4Img;
        this.mainLayout = mainLayout;this.mainLayout2 = mainLayout2;

        this.animSetXY1=animSetXY1;this.animSetXY2=animSetXY2;this.animSetXY3=animSetXY3;this.animSetXY4=animSetXY4;
        this.animSetXY11=animSetXY11;this.animSetXY22=animSetXY22;this.animSetXY33=animSetXY33;this.animSetXY44=animSetXY44;
        this.animSetXY111=animSetXY111;this.animSetXY222=animSetXY222;this.animSetXY333=animSetXY333;this.animSetXY444=animSetXY444;
        this.animSetXYL1=animSetXYL1;this.animSetXYL2=animSetXYL2;this.animSetXYL3=animSetXYL3;this.animSetXYL4=animSetXYL4;

        this.speedNote = speedNote;this.speedLong=speedLong;this.multiplier=multiplier;this.indice=indice;
        this.tab_mha5 = tab_mha5;this.arret = arret;this.transition = transition;this.musique = musique;
        this.repetB=repetB;this.duration_transition=350;
    }

    public void setSpeedNote(int speed){
        this.speedNote = speed;
    }
    public void setSpeedLong(int speed){
        this.speedLong = speed;
    }
    public void setMultiplier(float speed){
        this.multiplier = speed;
    }
    public int getSpeedNote(){
        return this.speedNote;
    }
    public int getSpeedLong(){
        return this.speedLong;
    }
    public float getMultiplier(){
        return this.multiplier;
    }


    public void fall(int[] tab){

        if(!arret[0]) {//Si l'indice est 1 : on fait tomber la note 1 sauf si elle est déjà en train de tomber
            //dans ce cas on fait tomber la deuxième note 1 : son nom est 11 ; pareil pour 111;
            //Il y a 3 notes par colonnes qui se relayent


            if(tab[indice]==1144) {
                long1Img.setVisibility(Button.VISIBLE);
                long1.setVisibility(Button.VISIBLE);
                long4Img.setVisibility(Button.VISIBLE);
                long4.setVisibility(Button.VISIBLE);
                animSetXYL1.start();
                animSetXYL4.start();
            }


            if(tab[indice]==2244) {
                long2Img.setVisibility(Button.VISIBLE);
                long2.setVisibility(Button.VISIBLE);
                long4Img.setVisibility(Button.VISIBLE);
                long4.setVisibility(Button.VISIBLE);
                animSetXYL2.start();
                animSetXYL4.start();
            }


            if(tab[indice]==1133) {
                long1Img.setVisibility(Button.VISIBLE);
                long1.setVisibility(Button.VISIBLE);
                long3Img.setVisibility(Button.VISIBLE);
                long3.setVisibility(Button.VISIBLE);
                animSetXYL1.start();
                animSetXYL3.start();
            }




            if (tab[indice] == 1) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
            } else if (tab[indice] == 2) {
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();


                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
            } else if (tab[indice] == 3) {
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
            } else if (tab[indice] == 4) {
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 12) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
            } else if (tab[indice] == 13) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
            } else if (tab[indice] == 14) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 23) {
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
            } else if (tab[indice] == 24) {
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 34) {
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 123) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
            } else if (tab[indice] == 124) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 134) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else { note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            }
            else if (tab[indice] == 234) {
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 1234) {
                if (!animSetXY1.isStarted()) {
                    note1Img.setVisibility(Button.VISIBLE);
                    note1.setVisibility(Button.VISIBLE);
                    animSetXY1.start();
                } else {
                    if (!animSetXY11.isStarted()) {
                        note11Img.setVisibility(Button.VISIBLE);
                        note11.setVisibility(Button.VISIBLE);
                        animSetXY11.start();
                    } else {
                        note111Img.setVisibility(Button.VISIBLE);
                        note111.setVisibility(Button.VISIBLE);
                        animSetXY111.start();
                    }
                }
                if (!animSetXY2.isStarted()) {
                    note2Img.setVisibility(Button.VISIBLE);
                    note2.setVisibility(Button.VISIBLE);
                    animSetXY2.start();
                } else {
                    if (!animSetXY22.isStarted()) {
                        note22Img.setVisibility(Button.VISIBLE);
                        note22.setVisibility(Button.VISIBLE);
                        animSetXY22.start();
                    } else {
                        note222Img.setVisibility(Button.VISIBLE);
                        note222.setVisibility(Button.VISIBLE);
                        animSetXY222.start();
                    }
                }
                if (!animSetXY3.isStarted()) {
                    note3Img.setVisibility(Button.VISIBLE);
                    note3.setVisibility(Button.VISIBLE);
                    animSetXY3.start();
                } else {
                    if (!animSetXY33.isStarted()) {
                        note33Img.setVisibility(Button.VISIBLE);
                        note33.setVisibility(Button.VISIBLE);
                        animSetXY33.start();
                    } else {
                        note333Img.setVisibility(Button.VISIBLE);
                        note333.setVisibility(Button.VISIBLE);
                        animSetXY333.start();
                    }
                }
                if (!animSetXY4.isStarted()) {
                    note4Img.setVisibility(Button.VISIBLE);
                    note4.setVisibility(Button.VISIBLE);
                    animSetXY4.start();
                } else {
                    if (!animSetXY44.isStarted()) {
                        note44Img.setVisibility(Button.VISIBLE);
                        note44.setVisibility(Button.VISIBLE);
                        animSetXY44.start();
                    } else {
                        note444Img.setVisibility(Button.VISIBLE);
                        note444.setVisibility(Button.VISIBLE);
                        animSetXY444.start();
                    }
                }
            } else if (tab[indice] == 11) {
                if (!animSetXYL1.isStarted()) {
                    long1Img.setVisibility(Button.VISIBLE);
                    long1.setVisibility(Button.VISIBLE);
                    animSetXYL1.start();
                }
            } else if (tab[indice] == 22) {
                if (!animSetXYL2.isStarted()) {
                    long2Img.setVisibility(Button.VISIBLE);
                    long2.setVisibility(Button.VISIBLE);
                    animSetXYL2.start();
                }
            } else if (tab[indice] == 33) {
                if (!animSetXYL3.isStarted()) {
                    long3Img.setVisibility(Button.VISIBLE);
                    long3.setVisibility(Button.VISIBLE);
                    animSetXYL3.start();
                }
            } else if (tab[indice] == 44) {
                if (!animSetXYL4.isStarted()) {
                    long4Img.setVisibility(Button.VISIBLE);
                    long4.setVisibility(Button.VISIBLE);
                    animSetXYL4.start();
                }
            }
            //if tab [indice] == 9999 on passe au suivant : permet d'attendre plus longtemps avant la prochaine note

            indice = indice + 1;
            if (indice == tab.length) { //protection au cas ou la taille du tableau est dépassée
                indice = 0;
            }

        }


    }
    public void start(){

        //Niveau


        /*
        Un niveau est toujours constitué de la meme manière : à l'aide des Objets : CountDownTimer et des handler
        Les Handler :
            Ils permettent simplement d'attendre le temps donné en parametre à la fin de celui-ci : 300 = 300millisecondes
        Les countDownTimer:
            Ils font la même chose mais en plus permette de réaliser une action à un intervalle régulier pendant l'attente :

            new CountDownTimer((long)(1500/multiplier),(long)(500/multiplier)) {

                 @Override
                 public void onTick(long millisUntilFinished) {
                     fall(ac_tab);
                 }

          ------>>> va faire tomber une note toutes les 500 millisecondes et pendant 1500 millisecondes


          quand le niveau est fini, on le repete en augmentant la vitesse : en augmentant la variable : 'multiplier' et speedNote / speedLong


        */

        animSetXY1.setDuration(speedNote);
        animSetXY2.setDuration(speedNote);
        animSetXY3.setDuration(speedNote);
        animSetXY4.setDuration(speedNote);

        animSetXY11.setDuration(speedNote);
        animSetXY22.setDuration(speedNote);
        animSetXY33.setDuration(speedNote);
        animSetXY44.setDuration(speedNote);

        animSetXY111.setDuration(speedNote);
        animSetXY222.setDuration(speedNote);
        animSetXY333.setDuration(speedNote);
        animSetXY444.setDuration(speedNote);

        animSetXYL1.setDuration(speedLong);
        animSetXYL2.setDuration(speedLong);
        animSetXYL3.setDuration(speedLong);
        animSetXYL4.setDuration(speedLong);

        indice=0;


        musique.start();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {


            new CountDownTimer((long)(6500/multiplier), (long)(406/multiplier)) {

                @Override
                public void onTick(long millisUntilFinished) {
                    fall(tab_mha5);
                }

                @Override
                public void onFinish() {
                    indice=17;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {public void run() {


                        new CountDownTimer((long)(7300/multiplier), (long)(405/multiplier)) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                fall(tab_mha5);
                            }

                            @Override
                            public void onFinish() {
                                indice=36;

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {public void run() {

                                    new CountDownTimer((long)(6900/multiplier), (long)(363/multiplier)) {

                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            fall(tab_mha5);
                                        }

                                        @Override
                                        public void onFinish() {
                                            mainLayout2.setBackgroundResource(R.drawable.degrad_red);
                                            mainLayout2.startAnimation(transition);
                                            Handler handler1 = new Handler();
                                            handler1.postDelayed(new Runnable() {public void run() {
                                                mainLayout.setBackgroundResource(R.drawable.degrad_red);
                                            }
                                            }, (long)(duration_transition/multiplier));

                                            indice =56;
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {public void run() {


                                                new CountDownTimer((long)(7000/multiplier), (long)(437/multiplier)) {

                                                    @Override
                                                    public void onTick(long millisUntilFinished) {
                                                        fall(tab_mha5);
                                                    }

                                                    @Override
                                                    public void onFinish() {

                                                        indice=73;

                                                        handler.postDelayed(new Runnable() {public void run() {

                                                            new CountDownTimer((long)(7500/multiplier), (long)(375/multiplier)) {

                                                                @Override
                                                                public void onTick(long millisUntilFinished) {
                                                                    fall(tab_mha5);
                                                                }

                                                                @Override
                                                                public void onFinish() {

                                                                    indice=94;

                                                                    Handler handler = new Handler();
                                                                    handler.postDelayed(new Runnable() {public void run() {


                                                                        new CountDownTimer((long)(12600/multiplier), (long)(420/multiplier)) {

                                                                            @Override
                                                                            public void onTick(long millisUntilFinished) {

                                                                                fall(tab_mha5);
                                                                            }

                                                                            @Override
                                                                            public void onFinish() {

                                                                                indice=125;
                                                                                Handler handler = new Handler();
                                                                                handler.postDelayed(new Runnable() {public void run() {


                                                                                    new CountDownTimer((long)(1000/multiplier), (long)(500/multiplier)) {

                                                                                        @Override
                                                                                        public void onTick(long millisUntilFinished) {
                                                                                            fall(tab_mha5);
                                                                                        }

                                                                                        @Override
                                                                                        public void onFinish() {
                                                                                            indice =128;
                                                                                            mainLayout2.setBackgroundResource(R.drawable.degrad_yellow_pink);
                                                                                            mainLayout2.startAnimation(transition);
                                                                                            Handler handler1 = new Handler();
                                                                                            handler1.postDelayed(new Runnable() {public void run() {
                                                                                                mainLayout.setBackgroundResource(R.drawable.degrad_yellow_pink);
                                                                                            }
                                                                                            }, (long)(duration_transition/multiplier));
                                                                                            Handler handler = new Handler();
                                                                                            handler.postDelayed(new Runnable() {public void run() {


                                                                                                new CountDownTimer((long)(1500/multiplier),(long)(500/multiplier)) {

                                                                                                    @Override
                                                                                                    public void onTick(long millisUntilFinished) {
                                                                                                        fall(tab_mha5);
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onFinish() {
                                                                                                        indice=132;

                                                                                                        Handler handler = new Handler();
                                                                                                        handler.postDelayed(new Runnable() {public void run() {

                                                                                                            new CountDownTimer((long)(4500/multiplier),(long)(450/multiplier)) {  //DOUBLES RAPIDES

                                                                                                                @Override
                                                                                                                public void onTick(long millisUntilFinished) {
                                                                                                                    fall(tab_mha5);
                                                                                                                }

                                                                                                                @Override
                                                                                                                public void onFinish() {
                                                                                                                    indice =143;
                                                                                                                    Handler handler = new Handler();
                                                                                                                    handler.postDelayed(new Runnable() {public void run() {

                                                                                                                        new CountDownTimer((long)(7950/multiplier),(long)(441/multiplier)) {

                                                                                                                            @Override
                                                                                                                            public void onTick(long millisUntilFinished) {
                                                                                                                                fall(tab_mha5);
                                                                                                                            }

                                                                                                                            @Override
                                                                                                                            public void onFinish() {
                                                                                                                                indice=162;
                                                                                                                                Handler handler = new Handler();
                                                                                                                                handler.postDelayed(new Runnable() {public void run() {

                                                                                                                                    new CountDownTimer((long)(4000/multiplier),(long)(444/multiplier)) {

                                                                                                                                        @Override
                                                                                                                                        public void onTick(long millisUntilFinished) {
                                                                                                                                            fall(tab_mha5);
                                                                                                                                        }

                                                                                                                                        @Override
                                                                                                                                        public void onFinish() {
                                                                                                                                            indice=172;

                                                                                                                                            Handler handler = new Handler();
                                                                                                                                            handler.postDelayed(new Runnable() {public void run() {

                                                                                                                                                new CountDownTimer((long)(8800/multiplier),(long)(488/multiplier)) {

                                                                                                                                                    @Override
                                                                                                                                                    public void onTick(long millisUntilFinished) {

                                                                                                                                                        fall(tab_mha5);
                                                                                                                                                    }

                                                                                                                                                    @Override
                                                                                                                                                    public void onFinish() {
                                                                                                                                                        indice=190;
                                                                                                                                                        Handler handler = new Handler();
                                                                                                                                                        handler.postDelayed(new Runnable() {public void run() {

                                                                                                                                                            new CountDownTimer((long)(6200/multiplier), (long)(442/multiplier)) {

                                                                                                                                                                @Override
                                                                                                                                                                public void onTick(long millisUntilFinished) {
                                                                                                                                                                    fall(tab_mha5);
                                                                                                                                                                }

                                                                                                                                                                @Override
                                                                                                                                                                public void onFinish() {

                                                                                                                                                                    Handler handler = new Handler();
                                                                                                                                                                    handler.postDelayed(new Runnable() {
                                                                                                                                                                        @RequiresApi(api = Build.VERSION_CODES.M)
                                                                                                                                                                        public void run() {

                                                                                                                                                                            if(!arret[0]) {
                                                                                                                                                                                setMultiplier(getMultiplier() + (0.05f));
                                                                                                                                                                                musique.setPlaybackParams(musique.getPlaybackParams().setSpeed(multiplier));
                                                                                                                                                                                setSpeedNote((int) (getSpeedNote() / getMultiplier()));
                                                                                                                                                                                setSpeedLong((int) (getSpeedLong() / getMultiplier()));
                                                                                                                                                                                mainLayout2.setBackgroundResource(R.drawable.degrad_pink_blue);
                                                                                                                                                                                mainLayout2.startAnimation(transition);
                                                                                                                                                                                Handler handler = new Handler();
                                                                                                                                                                                handler.postDelayed(new Runnable() {public void run() {
                                                                                                                                                                                    mainLayout.setBackgroundResource(R.drawable.degrad_pink_blue);
                                                                                                                                                                                }
                                                                                                                                                                                }, (long)(duration_transition/multiplier));

                                                                                                                                                                                level_mha5 recommencer = new level_mha5(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
                                                                                                                                                                                        note1Img, note11Img, note111Img, note2Img, note22Img, note222Img, note3Img, note33Img, note333Img, note4Img, note44Img, note444Img,
                                                                                                                                                                                        long1, long2, long3, long4,
                                                                                                                                                                                        long1Img, long2Img, long3Img, long4Img,
                                                                                                                                                                                        mainLayout2,
                                                                                                                                                                                        mainLayout,
                                                                                                                                                                                        animSetXY1, animSetXY2, animSetXY3, animSetXY4,
                                                                                                                                                                                        animSetXY11, animSetXY22, animSetXY33, animSetXY44,
                                                                                                                                                                                        animSetXY111, animSetXY222, animSetXY333, animSetXY444,
                                                                                                                                                                                        animSetXYL1, animSetXYL2, animSetXYL3, animSetXYL4,

                                                                                                                                                                                        speedNote,
                                                                                                                                                                                        speedLong,
                                                                                                                                                                                        multiplier,
                                                                                                                                                                                        indice,
                                                                                                                                                                                        tab_mha5,arret,transition,musique,repetB);

                                                                                                                                                                                recommencer.start();

                                                                                                                                                                            }


                                                                                                                                                                        }
                                                                                                                                                                    }, (long)(6500));
                                                                                                                                                                }

                                                                                                                                                            }.start();

                                                                                                                                                        }
                                                                                                                                                        }, (long)(450/multiplier));

                                                                                                                                                    }

                                                                                                                                                }.start();

                                                                                                                                            }
                                                                                                                                            }, (long)(1000/multiplier));

                                                                                                                                        }

                                                                                                                                    }.start();

                                                                                                                                }
                                                                                                                                }, (long)(1300/multiplier));

                                                                                                                            }

                                                                                                                        }.start();


                                                                                                                    }
                                                                                                                    }, (long)(600/multiplier));

                                                                                                                }

                                                                                                            }.start();


                                                                                                        }
                                                                                                        }, (long)(800/multiplier));

                                                                                                    }

                                                                                                }.start();


                                                                                            }
                                                                                            }, (long)(450/multiplier));

                                                                                        }

                                                                                    }.start();


                                                                                }
                                                                                }, (long)(1100/multiplier));


                                                                            }

                                                                        }.start();

                                                                    }
                                                                    }, (long)(100/multiplier));



                                                                }

                                                            }.start();


                                                        }
                                                        }, (long)(300/multiplier));


                                                    }

                                                }.start();



                                            }
                                            }, (long)(400/multiplier));


                                        }

                                    }.start();


                                }
                                }, (long)(500/multiplier));
                            }

                        }.start();

                    }
                    }, (long)(50/multiplier));


                }

            }.start();

        }
        }, (long)(50/multiplier));


    }

}