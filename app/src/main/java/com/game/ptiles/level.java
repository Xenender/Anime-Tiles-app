package com.game.ptiles;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class level extends AppCompatActivity {

    //Fichier principal de tous les niveaux, il contient les fonctions qui permettent aux niveaux de fonctionner


    //Variables globales
    Button startBtn;
    Boolean vibraB;
    Boolean repetB;


    Button note1,note2,note3,note4,note11,note22,note33,note44,note111,note222,note333,note444;
    Button note1Img,note2Img,note3Img,note4Img,note11Img,note22Img,note33Img,note44Img,note111Img,note222Img,note333Img,note444Img;

    Button long1,long2,long3,long4;
    Button long1Img,long2Img,long3Img,long4Img;



    TextView score;
    ProgressBar progress_bar;
    LinearLayout layoutFail;
    RelativeLayout mainLayout2;
    ConstraintLayout mainLayout;




    AnimatorSet animSetXY1,animSetXY2,animSetXY3,animSetXY4,
            animSetXY11,animSetXY22,animSetXY33,animSetXY44,
            animSetXY111,animSetXY222,animSetXY333,animSetXY444,
            animSetXYL1,animSetXYL2,animSetXYL3,animSetXYL4;


    ObjectAnimator animX1,animY1,animX2,animY2,animX3,animY3,animX4,animY4,
            animX11,animY11,animX22,animY22,animX33,animY33,animX44,animY44,
            animX111,animY111,animX222,animY222,animX333,animY333,animX444,animY444,
            animXL1,animYL1,animXL2,animYL2,animXL3,animYL3,animXL4,animYL4;


    private  MediaPlayer musique;

    int speedNote;
    int speedLong;
    float multiplier;

    int indice;

    //definiton des tableaux contenant les notes de chaque niveaux : exemple : 1=une note sur la premiere collone, 2 = deuxieme collone, 11 = Une longue sur la premiere collone, 13 = note 1 et note 3 en meme temps ...


    int[] snk1_tab = {1,4,2,3,2,2,1,4,3,2,2,1,4,2,3,2,4,3,4,1,3,2,3,4,22  //[25] = coup 1
            ,4,3,2,1,2,1,2,1,4,3,4,3,11,3,4,3,4,2,1,4,2,1,3,44,1,2,3,4,2,2,4,3,1,2,2,3,1,3,1,4,2,11,3,3,4,2,1,4,3,1,3,2,4,3,2,4,2,3//57/58
            ,1,1,1,3,3,2,2,2,4,4,4 //12
            ,1,2,3,4,4,3,2,1,4,1,3,2,4,5,4,3,1,33,1,1,2,1,4,1,1,2,3,3,4,2,1,3,4,3,2,1,3,11,4,3,2,1,2,3,1,2,2,1,4,33,1,4,2,3,1,22,4,2,1,2,3,4,4,3,2,1,4,1,3,2,4,5,4,3,1,33,1,1,2,1,4,1,1,2,3,3,4,2,1,3,4,3,2,1,3,11,4,3,2,1,2,3,1,22,4,1,4,3,1,4,2,33 //120
    };

    int[] tab_demonSlayer1 = {1,2,1,9999, //0
            2,3,2,9999,//3 4
            3,4,3,4,22,9999,9999,9999,2,9999,//6 8
            1,2,3,9999,//15 18
            3,2,1,9999,//18 22
            4,4,22,9999,//21 26
            14//24 30
            ,1,3,2,4,13,2,4,3,2,14,2,3,2,1,24,1,2,3,44,9999,9999,
            1,2,4,2,33,1,1,9999,//46 52
            13,24,13,24,13,9999,//53 60
            1,4,22,9999,13,2,3,24,1,2,33,9999,9999//58 66
            ,1,3,2,4,13,2,4,3,2,14,2,3,2,1,24,1,2,3,44,9999,2,2,1,22,9999,2,1,9999,//70 79
            13,24,13,24,13,9999,//97 107
            1,2,3,2,3,9999,//102 113
            13,24,13,24,13,9999,//107 119
            1,2,3,4,1,2,4,3,2,11,9999,4,3,2,2,3,1,2,3,2,3,14,9999,//112 125
            1,2,1,9999,//134 148
            2,3,2,9999,//137 152
            3,4,3,4,22,9999,9999,9999,2,9999,//140 156
            4,3,2,9999,//149 166
            2,3,4,9999,//152 170
            2,2,33,9999,9999//155 174
            ,1,3,2,4,13,2,4,3,2,14,2,3,2,1,24,1,2,3,44,9999, // =20 notes
            1,2,3,4,1,2,4,3,2,11,9999,4,3,2,2,3,1,2,3,2,3,14, //=22 notes
            1,3,2,4,13,2,4,3,2,14,2,3,2,1,24,1,2,3,44,2,2,3,2,4,13,2,1,3,14,11,9999,4,14,2,1,3,2,4,1,2,3,4,3,2,14,2,3,14,1,3,2,14,9999 // =27 notes
    };

    int[] snk7_tab = {1,4,2,3,9999,//0-4
            3,2,9999,//5-7
            3,3,3,9999,//8,11
            1144,9999,//12-13
            2,2,3,9999,//14-17
            1,3,3,2,4,4,1,3,3,2,3,3,1,4,4,3,1,1,4,2,2,3,9999, //18-40
            3,9999,4,4,9999,3,3,9999,1,1,9999,3,3,9999,2,2,9999,1,1,9999,//41-60
            1133,9999,2,1,4,3,1,33,1,4,2,2,1,1,4,9999,//61-76
            9999,9999,9999,1,1,1,9999,3,4,3,4,9999,3,3,3,1,2,1,2,9999,//77-96
            4,1,2,1,3,4,1,22,4,3,1,2,4,3,22,9999,//97-112
            9999,9999,9999,3,3,3,1,2,1,2,1,2,9999,14,14,14,9999,//113-129
            23,4,1,3,2,3,1,9999,1,2,24,2,9999,3,2,4,3,2,9999,1,2,3,2,4,2,3,4,3,2,1,2,4,1,22,4,4,3,1,4,1,4,24,1,2,9999,//130-174
            2,2,4,4,3,3,1,1,23,14,9999,//175-185
            9999,1144,9999,2,4,3,1,33,9999,3,1,2,4,11,9999,2,4,22,9999,2,1,1,22,9999,1,1,44,9999,1,1,2,4,9999,//186-218
            9999,9999,22,9999,4,3,2,1,22,9999,4,4,3,4,11,3,4,2,1,3,4,1,2,3,1,4,2,1133,9999,//219-247
            14,14,14,9999,//248-251
            9999,9999,23,9999,14,14,3,3,2,2,4,4,23,14,23,1,1,4,4,1,2,3,4,3,3,1,1,3,3,24,24,24//252-


    };

    int[] tab_cowboy_bebop={1,4,2,3,4,11,9999,// 0
            4,1,3,2,9999,//7
            3,1,2,3,4,1,3,9999,9999,9999,4,3,2,1,3,9999,//12
            1,2,3,44,9999,9999,9999,9999,4,3,2,11,9999,9999,9999,9999,1,2,3,44,9999,9999,9999,9999,4,3,2,11,9999,//28
            2,4,3,1,4,2,1,4,2,33,4,1,3,22,9999,//52
            4,3,2,11,9999,14,4,3,2,11,9999,2,4,1,3,2,4,1,1,22,9999,//66
            1,2,3,44,9999,1,11,9999,4,3,2,11,9999,4,3,4,1,2,3,44,9999,11,9999,9999,4,3,2,11,4,3,44,9999,//86
            2,4,3,1,4,2,1,4,2,9999,11,44,9999,9999,9999,22,33,9999,//117
            1,2,3,2,3,4,9999,//136
            4,3,2,3,2,11,9999,1,9999,//142
            1,4,1,4,1,4,1,4,1,4,1,4,9999,1144,9999//150
    };

    int[] tab_mha9={11,44,9999,//0
            1,4,2,3,9999,3,2,4,1,9999,1,4,2,3,9999,2,4,1,33,9999,//3
            44,9999,33,9999,22,3,2,1,44,9999,33,9999,22,3,2,1,4,9999,//23 mais erreur dans le fichier que j'avais pas vu du coup j'ai adapté ne pas changer svp
            11,9999,2,3,44,//41
            44,9999,3,2,3,2,11,9999,//45
            33,9999,2,3,2,3,2,3,11,9999,//54
            11,9999,2,3,4,4,3,2,11,9999,//64
            2,4,1,33,9999,3,2,1,44,9999,13,24,13,2244,9999,2,3,4,11,9999,4,1,2,33,9999,1,2,2,11,9999,4,3,3,44,9999,24,13,24,1133,9999,9999,//74
            4,1,3,2,2,3,1,44,9999,4,3,2,1,1,2,3,44,9999,1,4,3,2,3,22,9999,//115
            13,24,9999,13,24,9999,13,24,9999,1144,9999,9999,//140
            2,1,22,9999,4,3,44,9999,1,4,2,3,2,4,1,9999,4,33,9999,4,3,22,9999,//152
            1,2,3,2,3,4,33,9999,2,11,9999,9999,1,2,33,9999,2,3,4,1,2,3,4,11,9999,//174
            1,2,4,33,9999,9999,4,3,2,1,22,9999,//200
            4,44,9999,3,33,9999,2,22,9999,1,2,3,44,9999,9999//212
    };
    int[] tab_mha5={1,2,2,2,1,4,3,3,4,2,3,2,3,2,1,4,9999,//0
            1,4,1,3,2,33,9999,1,44,9999,9999,3,2,3,1,1,4,2,9999,//17
            1,2,33,9999,2,4,3,1,4,2,2,4,3,1,2,1,3,2,4,9999,//36
            1,3,2,4,2,1,4,3,3,4,1,2,4,2,3,1,9999,//56
            1,2,3,4,2,3,2,3,4,1,3,2,4,1,4,3,2,1,4,1,9999,//73
            3,1,4,3,1,2,2,4,1,24,13,24,13,24,13,9999,2,1,4,3,3,4,2,3,1,3,4,1,4,22,9999,//94
            1,44,9999,//125
            11,33,22,9999,//128
            3,1,4,2,3,2,1,3,4,11,9999,//132
            1,2,3,4,11,3,2,3,22,33,9999,11,9999,3,2,4,1,44,9999,//143
            3,1,3,4,2,1,4,2,11,9999,//162
            3,1,4,2,11,3,2,4,1,2,4,33,9999,4,2,3,11,9999,//172
            1,4,1,3,2,3,4,1,4,2,1,3,2,33,9999//190
    };

    int[] tab_eva={11,44,22,4,1,22,33,1,4,9999,1,11,44,22,33,11,44,22,33,9999,//0
            3,2,3,4,1,2,3,2,3,4,1,2,3,2,3,2,14,23,14,23,9999,//20
            2,22,9999,4,3,4,9999,3,2,3,2,1,44,9999,//41
            1,2,3,4,9999,2,3,2,3,9999,4,3,2,11,9999,9999,33,9999,4,44,9999,//54
            4,33,9999,1,2,11,9999,4,1,2,3,1,2,44,9999,//76
            1,2,11,9999,4,3,44,9999,4,3,3,2,1,1,2,3,4,9999,9999,//91
            2,2,1,4,3,2,4,4,1,3,2,4,22,9999,//110
            13,24,13,24,9999,//124
            1,2,3,2,3,2,1,2,2,4,4,1,11,9999,//129
            2,3,2,9999,//143
            4,2,1,4,2,3,3,1,3,4,2,1,4,2,1,1,9999,//147
            2,13,4,4,1,3,2,1,2,1,9999,3,2,4,3,9999,//164
            1,4,1,4,1,4,1,9999,//180
            2,2,1,3,2,4,4,3,1,3,3,4,2,9999,//188
            3,2,3,2,3,9999//202


    };

    int [] tab_ac={ 1,4,1,4,1,4,1,9999, //7 0-7
            2,3,2,9999, //3 8-11
            3,1,3,9999, //3 12-15
            9999,1,1,9999,4,4,9999 //6 16-22
            ,3,2,3,9999, //3 23-26
            1,3,2,9999,//3  27-30
            3,1,3,4,9999,//4  31-35
            2,3,2,9999//3  36-39
            ,1,3,2,4,1,9999,4,1,9999, //8 40-48
            2,3,2,9999,//3 49-52
            1,4,3,1,2,4,1,9999,//7 53-60
            4,4,4,9999,//3 61-64
            22,9999,9999,//2 65-67
            4,4,9999,3,3,9999,1,1,9999,3,1,2,9999,4,4,9999,2,2,9999,1,4,9999,2,3,1,1,4,9999//26  68-95
            ,2,3,1,4,2,3,1,3,2,1,4,3,4,2,1,2,9999//16   96-112
            ,4,2,1,2,3,9999, //3    113-117   1 en plus
            2,2,9999,9999//3 118-121
            ,3,3,1,2,3,1,4,3,1,3,4,2,4,3,1,2,4,3,2,3,4,1,2,1,4,3,2,1,3,2,3,4,2,1,3,2,3,2,9999,9999,//39   122-161
            1,1,2,4,3,4,3,2,1,2,4,3,1,4,1,2,4,3,2,1,2,1,2,3,1,4,3,1,2,3,1,2,3,4,1,2,1,9999,9999,//38    162-200
            4,4,1,3,2,1,3,4,3,4,1,2,3,4,2,1,3,4,3,2,1,4,2,1,3,2,1,4,3,2,1,2,3,1,2,3,2,4,9999//38  201-239
            ,2,3,2,3,//4   240-244
            1,4,2,3,1,4,2,3//8   245-253           !!! ne pas corriger avec indice   !!!
            ,9999,9999,9999,9999,   // 254-259
            2,3,2,3

    };

    int[] tab_snk4={11,44,22,33,9999,11,44,22,33,9999,11,44,22,33,9999,11,44,22,9999,//0
            3,1,2,4,33,9999,//19
            2,3,22,9999,//25
            4,3,3,44,9999,9999,9999,1,2,2,11,9999,9999,2,1,33,9999,9999,//29
            4,3,22,9999,9999,9999,9999,1,2,3,44,9999,9999,1,22,9999,9999,11,33,22,9999,//47
            11,44,22,9999,1,33,22,44,11,9999,4,22,33,11,44,9999,1,33,22,44,9999,9999,//68
            1,4,22,9999,44,9999,9999,4,2,2,33,9999,9999,9999,9999,1,2,33,2,11,9999,9999,1,2,3,44,9999,9999,//90
            22,33,9999,9999,11,3,44,9999,9999,11,3,2,44,9999,//118
            3,22,4,11,9999,9999,4,11,4,22,9999,9999,//132
            11,3,22,9999//144
    };


    //LONG VAR
    final double[] firstTouch1 = {0.},firstTouch2 = {0.},firstTouch3 = {0.},firstTouch4 = {0.};
    final boolean[] longHold1={false},longHold2={false},longHold3={false},longHold4={false};



    final boolean[] arret={false};

    Animation transition;
    Vibrator vibrator;


    //VERIFIER QUE LES NOTES SONT TOUTES CLIQUES, type = tableau final à 1 element de boullean -> pour que les valeurs puisse être modifiés à l'interieur des fonctions.
    final Boolean[] note1cliqued={false},note2cliqued={false},note3cliqued={false},note4cliqued={false},note11cliqued={false},note22cliqued={false},note33cliqued={false},note44cliqued={false},
            note111cliqued={false},note222cliqued={false},note333cliqued={false},note444cliqued={false},long1cliqued={false},long2cliqued={false},long3cliqued={false},long4cliqued={false};


    String niveau;

    Boolean launched = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        //Initialisation des variables

        startBtn = findViewById(R.id.startBtn);

        note1=findViewById(R.id.note1);
        note2=findViewById(R.id.note2);
        note3=findViewById(R.id.note3);
        note4=findViewById(R.id.note4);
        note11=findViewById(R.id.note11);
        note22=findViewById(R.id.note22);
        note33=findViewById(R.id.note33);
        note44=findViewById(R.id.note44);
        note111 = findViewById(R.id.note111);
        note222 = findViewById(R.id.note222);
        note333 = findViewById(R.id.note333);
        note444 = findViewById(R.id.note444);

        note1Img=findViewById(R.id.note1Img);
        note2Img=findViewById(R.id.note2Img);
        note3Img=findViewById(R.id.note3Img);
        note4Img=findViewById(R.id.note4Img);
        note11Img=findViewById(R.id.note11Img);
        note22Img=findViewById(R.id.note22Img);
        note33Img=findViewById(R.id.note33Img);
        note44Img=findViewById(R.id.note44Img);
        note111Img=findViewById(R.id.note111Img);
        note222Img=findViewById(R.id.note222Img);
        note333Img=findViewById(R.id.note333Img);
        note444Img=findViewById(R.id.note444Img);



        long1=findViewById(R.id.long1);
        long2=findViewById(R.id.long2);
        long3=findViewById(R.id.long3);
        long4=findViewById(R.id.long4);

        long1Img=findViewById(R.id.long1Img);
        long2Img=findViewById(R.id.long2Img);
        long3Img=findViewById(R.id.long3Img);
        long4Img=findViewById(R.id.long4Img);


        score = findViewById(R.id.score);
        progress_bar = findViewById(R.id.progress_bar);

        layoutFail = findViewById(R.id.layoutFail);
        mainLayout = findViewById(R.id.mainLayout);
        mainLayout2=findViewById(R.id.mainLayout2);


        speedNote=1000;   //VITESSE DE BASE
        speedLong = 1388; //VITESSE DE BASE
        multiplier = 1.0f;



        int baseHeight = 2270;


        final boolean[] start = {false};


        //GET SCREEN SIZE

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int height = metrics.heightPixels; //LONGUEUR


        //Affichage de test ...
        //Toast.makeText(level.this,String.valueOf(height),Toast.LENGTH_SHORT).show();




        transition = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation150);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



//RECUPERATION INTENT (recupération des information qui ont été envoyés de la dernière activité vers celle-là)
        Intent recup = getIntent();
        niveau = recup.getStringExtra("niveau");
        vibraB = recup.getBooleanExtra("vibraB",true);
        repetB = recup.getBooleanExtra("repetB",true);



        //Creation des animations et de leurs spécificités pour les notes et les longues : "PropertyName" = "y" veux dire une translation de haut en bas


        animX1 = ObjectAnimator.ofFloat(note1, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY1 = ObjectAnimator.ofFloat(note1Img, "y", -1000,height);
        animSetXY1 = new AnimatorSet();
        animSetXY1.playTogether(animX1, animY1);
        animSetXY1.setDuration(2500);
        animSetXY1.setInterpolator(new LinearInterpolator());


        animX2 = ObjectAnimator.ofFloat(note2, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY2 = ObjectAnimator.ofFloat(note2Img, "y", -1000,height);
        animSetXY2 = new AnimatorSet();
        animSetXY2.playTogether(animX2, animY2);
        animSetXY2.setInterpolator(new LinearInterpolator());

        animX3 = ObjectAnimator.ofFloat(note3, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY3 = ObjectAnimator.ofFloat(note3Img, "y", -1000,height);
        animSetXY3 = new AnimatorSet();
        animSetXY3.playTogether(animX3, animY3);
        animSetXY3.setInterpolator(new LinearInterpolator());


        animX4 = ObjectAnimator.ofFloat(note4, "y", -1200,height-200);//height = longueur ecran donc parcourstout l'ecran
        animY4 = ObjectAnimator.ofFloat(note4Img, "y", -1000,height);
        animSetXY4 = new AnimatorSet();
        animSetXY4.playTogether(animX4, animY4);
        animSetXY4.setInterpolator(new LinearInterpolator());


        //SECONDES NOTES PAR LIGNES

        animX11 = ObjectAnimator.ofFloat(note11, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY11 = ObjectAnimator.ofFloat(note11Img, "y", -1000,height);
        animSetXY11 = new AnimatorSet();
        animSetXY11.playTogether(animX11, animY11);
        animSetXY11.setInterpolator(new LinearInterpolator());


        animX22 = ObjectAnimator.ofFloat(note22, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY22 = ObjectAnimator.ofFloat(note22Img, "y", -1000,height);
        animSetXY22 = new AnimatorSet();
        animSetXY22.playTogether(animX22, animY22);
        animSetXY22.setInterpolator(new LinearInterpolator());

        animX33 = ObjectAnimator.ofFloat(note33, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY33 = ObjectAnimator.ofFloat(note33Img, "y", -1000,height);
        animSetXY33 = new AnimatorSet();
        animSetXY33.playTogether(animX33, animY33);
        animSetXY33.setInterpolator(new LinearInterpolator());

        animX44 = ObjectAnimator.ofFloat(note44, "y", -1200,height-200);//height = longueur ecran donc parcours tout l'ecran
        animY44 = ObjectAnimator.ofFloat(note44Img, "y", -1000,height);
        animSetXY44 = new AnimatorSet();
        animSetXY44.playTogether(animX44, animY44);
        animSetXY44.setInterpolator(new LinearInterpolator());


        //TROISIEME PAR LIGNES

        animX111 = ObjectAnimator.ofFloat(note111, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY111 = ObjectAnimator.ofFloat(note111Img, "y", -1000,height);
        animSetXY111 = new AnimatorSet();
        animSetXY111.playTogether(animX111, animY111);
        animSetXY111.setInterpolator(new LinearInterpolator());


        animX222 = ObjectAnimator.ofFloat(note222, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY222 = ObjectAnimator.ofFloat(note222Img, "y", -1000,height);
        animSetXY222 = new AnimatorSet();
        animSetXY222.playTogether(animX222, animY222);
        animSetXY222.setInterpolator(new LinearInterpolator());

        animX333 = ObjectAnimator.ofFloat(note333, "y", -1200,height-200);//height = longueur ecran donc parcour tout l'ecran
        animY333 = ObjectAnimator.ofFloat(note333Img, "y", -1000,height);
        animSetXY333 = new AnimatorSet();
        animSetXY333.playTogether(animX333, animY333);
        animSetXY333.setInterpolator(new LinearInterpolator());

        animX444 = ObjectAnimator.ofFloat(note444, "y", -1200,height-200);//height = longueur ecran donc parcours tout l'ecran
        animY444 = ObjectAnimator.ofFloat(note444Img, "y", -1000,height);
        animSetXY444 = new AnimatorSet();
        animSetXY444.playTogether(animX444, animY444);
        animSetXY444.setInterpolator(new LinearInterpolator());


        //LONGUES

        animXL1 = ObjectAnimator.ofFloat(long1, "y", (-1*height),height);//height = longueur ecran donc parcours tout l'ecran
        animYL1 = ObjectAnimator.ofFloat(long1Img, "y", -1*height,height);
        animSetXYL1 = new AnimatorSet();
        animSetXYL1.playTogether(animXL1, animYL1);
        animSetXYL1.setInterpolator(new LinearInterpolator());

        animXL2 = ObjectAnimator.ofFloat(long2, "y", (-1*height),height);//height = longueur ecran donc parcours tout l'ecran
        animYL2 = ObjectAnimator.ofFloat(long2Img, "y", -1*height,height);
        animSetXYL2 = new AnimatorSet();
        animSetXYL2.playTogether(animXL2, animYL2);
        animSetXYL2.setInterpolator(new LinearInterpolator());

        animXL3 = ObjectAnimator.ofFloat(long3, "y", (-1*height),height);//height = longueur ecran donc parcours tout l'ecran
        animYL3 = ObjectAnimator.ofFloat(long3Img, "y", -1*height,height);
        animSetXYL3 = new AnimatorSet();
        animSetXYL3.playTogether(animXL3, animYL3);
        animSetXYL3.setInterpolator(new LinearInterpolator());

        animXL4 = ObjectAnimator.ofFloat(long4, "y", (-1*height),height);//height = longueur ecran donc parcours tout l'ecran
        animYL4 = ObjectAnimator.ofFloat(long4Img, "y", -1*height,height);
        animSetXYL4 = new AnimatorSet();
        animSetXYL4.playTogether(animXL4, animYL4);
        animSetXYL4.setInterpolator(new LinearInterpolator());



        //Fonctions de bases : listener de fin d'animation et listener de clic sur une note

        animSetXY1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                note1Img.setAlpha(1.0f);;

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {

                restartAnimNote(note1,note1Img,note1cliqued);
            }

        });



        animSetXY2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {

                note2Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note2,note2Img,note2cliqued);

            }
        });



        animSetXY3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {

                note3Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note3,note3Img,note3cliqued);

            }

        });




        animSetXY4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                note4Img.setAlpha(1.0f);

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note4,note4Img,note4cliqued);

            }
        });

        //


        animSetXY11.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                note11Img.setAlpha(1.0f);

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note11,note11Img,note11cliqued);
            }

        });



        animSetXY22.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {

                note22Img.setAlpha(1.0f);

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note22,note22Img,note22cliqued);
            }
        });



        animSetXY33.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {

                note33Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note33,note33Img,note33cliqued);
            }

        });




        animSetXY44.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                note44Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note44,note44Img,note44cliqued);
            }
        });
        //TROISIEME


        animSetXY111.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {

                note111Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note111,note111Img,note111cliqued);
            }

        });



        animSetXY222.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {


                note222Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note222,note222Img,note222cliqued);
            }
        });



        animSetXY333.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                note333Img.setAlpha(1.0f);

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note333,note333Img,note333cliqued);
            }

        });




        animSetXY444.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                note444Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimNote(note444,note444Img,note444cliqued);
            }
        });


        //LONG

        animSetXYL1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                long1Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {

                restartAnimLong(long1,long1Img,longHold1[0],long1cliqued,firstTouch1[0]);

            }
        });


        animSetXYL2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                long2Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimLong(long2,long2Img,longHold2[0],long2cliqued,firstTouch2[0]);

            }
        });


        animSetXYL3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                long3Img.setAlpha(1.0f);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimLong(long3,long3Img,longHold3[0],long3cliqued,firstTouch3[0]);

            }
        });


        animSetXYL4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {

                long4Img.setAlpha(1.0f);

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animator) {
                restartAnimLong(long4,long4Img,longHold4[0],long4cliqued,firstTouch4[0]);
            }
        });


        //Evenement de clic sur une note


        note1.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note1,note1Img,note1cliqued);
                }
                return false;
            }
        });


        note2.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note2,note2Img,note2cliqued);
                }
                return false;
            }
        });

        note3.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note3,note3Img,note3cliqued);
                }
                return false;
            }
        });

        note4.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note4,note4Img,note4cliqued);
                }
                return false;
            }
        });

        //SECOND

        note11.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note11,note11Img,note11cliqued);
                }
                return false;
            }
        });

        note22.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note22,note22Img,note22cliqued);
                }
                return false;
            }
        });

        note33.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note33,note33Img,note33cliqued);
                }
                return false;
            }
        });

        note44.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note44,note44Img,note44cliqued);
                }
                return false;
            }
        });

        //TROISIEME

        note111.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note111,note111Img,note111cliqued);
                }
                return false;
            }
        });

        note222.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note222,note222Img,note222cliqued);
                }
                return false;
            }
        });

        note333.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note333,note333Img,note333cliqued);
                }
                return false;
            }
        });

        note444.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                    noteTouch(note444,note444Img,note444cliqued);
                }
                return false;
            }
        });


        //LONG

        long1.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                longTouch(event,long1Img,long1cliqued,longHold1,firstTouch1,animSetXYL1);

                return false;
            }
        });

        long2.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                longTouch(event,long2Img,long2cliqued,longHold2,firstTouch2,animSetXYL2);
                return false;
            }
        });

        long3.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                longTouch(event,long3Img,long3cliqued,longHold3,firstTouch3,animSetXYL3);
                return false;
            }
        });

        long4.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                longTouch(event,long4Img,long4cliqued,longHold4,firstTouch4,animSetXYL4);
                return false;
            }
        });


        //si on clique sur le fond est que la musique est lancé = game over

        layoutFail.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if(start[0]) {
                    gameOver();                }
            }
        });


        //Bouton start, recupere la musique choisie, créé le bon objet (representant un niveau) representant le bon niveau et le lance : .start()

        startBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(!start[0]) {
                    start[0] = true;
                    startBtn.setVisibility(Button.GONE);
                    launched = true;


                    switch (niveau){
                        case "evangelion":
                            musique = MediaPlayer.create(level.this, R.raw.evangelion);
                            progress_bar.setMax(musique.getDuration());

                            level_evangelion evangelion = new level_evangelion (note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    tab_eva,arret,transition,musique,repetB);


                            evangelion.start();

                            //Si la repetition n'est pas activé alors quand la musique est fini on affiche l'écran de fin : gameOver()

                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });
                            break;

                        case "snk1":
                            musique = MediaPlayer.create(level.this, R.raw.snk1);
                            progress_bar.setMax(musique.getDuration());

                            level_snk1 snk1 = new level_snk1(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    snk1_tab,arret,transition,musique,repetB);


                            snk1.start();

                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });
                            break;

                        case "demon_slayer1":
                            musique = MediaPlayer.create(level.this, R.raw.demon_slayer1);
                            progress_bar.setMax(musique.getDuration());


                            level_demon_slayer1 dm = new level_demon_slayer1(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    tab_demonSlayer1,arret,transition,musique,repetB);


                            dm.start();
                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });

                            break;

                        case "mha5":
                            musique=MediaPlayer.create(level.this,R.raw.mha5);
                            progress_bar.setMax(musique.getDuration());

                            level_mha5 mha5 = new level_mha5(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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


                            mha5.start();
                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });

                            break;
                        case "mha9":
                            musique=MediaPlayer.create(level.this,R.raw.mha9);
                            progress_bar.setMax(musique.getDuration());

                            level_mha9 mha9 = new level_mha9(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    tab_mha9,arret,transition,musique,repetB);


                            mha9.start();
                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });

                            break;

                        case "snk7":
                            musique=MediaPlayer.create(level.this,R.raw.op7);
                            progress_bar.setMax(musique.getDuration());

                            level_snk7 snk7 = new level_snk7(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    snk7_tab,arret,transition,musique,repetB);


                            snk7.start();
                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });

                            break;
                        case "cowboy":
                            musique=MediaPlayer.create(level.this,R.raw.cowboy);
                            progress_bar.setMax(musique.getDuration());

                            level_cowboy_bebop cowboy_bebop = new level_cowboy_bebop(note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    tab_cowboy_bebop,arret,transition,musique,repetB);


                            cowboy_bebop.start();


                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });

                            break;




                        case "ac":
                            musique = MediaPlayer.create(level.this, R.raw.ac4);
                            progress_bar.setMax(musique.getDuration());

                            level_ac ac = new level_ac (note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    tab_ac,arret,transition,musique,repetB);


                            ac.start();
                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });
                            break;


                        case "snk4":
                            musique = MediaPlayer.create(level.this, R.raw.snk4);
                            progress_bar.setMax(musique.getDuration());

                            level_snk4 snk4 = new level_snk4 (note1, note11, note111, note2, note22, note222, note3, note33, note333, note4, note44, note444,
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
                                    tab_snk4,arret,transition,musique,repetB);


                            snk4.start();
                            musique.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    if(!repetB){
                                        gameOver();

                                    }
                                }
                            });
                            break;

                    }

                }
            }
        });


        //permet d'actualiser la barre de progression en fonction de l'avancement de la musique
        Handler mHandler = new Handler();
        level.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(musique != null){
                    int mCurrentPosition = musique.getCurrentPosition();
                    progress_bar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 100);
            }
        });


    }
    //Si on quitte l'app alors on perd
    @Override
    protected void onPause() {

        super.onPause();
        if(launched) gameOver();

    }

    @Override
    protected void onResume() {

        super.onResume();

    }


    //Les fonctions principales


    //Ajoute le score et met la variable cliqued[0] à vrai
    void noteTouch(Button note,Button noteImg,Boolean[] clicked) {
        if (!clicked[0]) {
            int intscore = parseInt(score.getText().toString()) + 100;
            score.setText(String.valueOf(intscore));
            noteImg.setAlpha(0.3f);
            clicked[0] = true;
            note.setVisibility(Button.GONE);


            if(vibraB==true) {
                vibrator.vibrate(50); //VIBRATE
            }
        }
    }


    //meme principe que pour les notes +
    //Calcule le temps passé, appuyé sur la longue et ajoute des points en conséquence avec la procédure : addLongPoint
    void longTouch(MotionEvent event,Button longImg,Boolean[] cliqued, boolean[] hold,double[] firstTouch,AnimatorSet animSet){

        cliqued[0] = true;


        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            hold[0]=true;
            firstTouch[0] = System.currentTimeMillis();
            longImg.setBackgroundResource(R.color.grey);


        } else if (event.getAction()==MotionEvent.ACTION_UP) {

            longImg.startAnimation(transition);
            longImg.setBackgroundResource(R.drawable.degrad_black_blue);
            longImg.setAlpha(0.3f);
            hold[0] = false;
            if(animSet.isStarted()){
                addLongPoint(System.currentTimeMillis()-firstTouch[0]);
            }
        }


    }

    void restartAnimNote(Button note,Button noteImg,Boolean[] cliqued){
        if(!cliqued[0]){
            gameOver(); //SI LA NOTE N'EST PAS CLIQUE QUAND L'ANIMATION FINI : elle est passé donc c'est perdu
        }
        cliqued[0] = false;

        note.setVisibility(Button.GONE);
        noteImg.setVisibility(Button.GONE);
    }

    void restartAnimLong(Button longue,Button longImg,Boolean hold,Boolean[] cliqued,double firstTouch){

        if(hold) {   //On prend le deuxieme temps
            addLongPoint(System.currentTimeMillis()-firstTouch);
        }

        if(!cliqued[0]){
            gameOver();                }
        cliqued[0] = false;
        longue.setVisibility(Button.GONE);
        longImg.setVisibility(Button.GONE);

    }


    void gameOver(){

        if(!arret[0]) {

            musique.stop(); //arret de la musique

            arret[0] = true;
            Intent i = new Intent(level.this, game_over.class);
            String s = score.getText().toString();
            i.putExtra("score", s);
            i.putExtra("niveau",niveau);
            i.putExtra("progress",musique.getCurrentPosition());
            i.putExtra("vibraB",vibraB);
            i.putExtra("repetB",repetB);

            //changement d'activité vers l'activité game over
            startActivity(i);
            finish();

            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        }
    }




    void addLongPoint(double seconds){
        double dbscore = parseInt(score.getText().toString())+(seconds/5);
        int intscore = (int) dbscore;
        score.setText(String.valueOf(intscore));

    }


}




/*

CREATION NIVEAU :

Handler handler = new Handler();
handler.postDelayed(new Runnable() {public void run() {

}
}, (long)(2000/multiplier));




new CountDownTimer((long)(5000/multiplier),(long)(5000/multiplier)) {

            @Override
            public void onTick(long millisUntilFinished) {
                // do something after 1s
            }

            @Override
            public void onFinish() {


            }

        }.start();



        ANIMATOR TOGETHER

        ObjectAnimator animX = ObjectAnimator.ofFloat(myView, "x", 50f);
ObjectAnimator animY = ObjectAnimator.ofFloat(myView, "y", 100f);
AnimatorSet animSetXY = new AnimatorSet();
animSetXY.playTogether(animX, animY);
animSetXY.start();


 */
