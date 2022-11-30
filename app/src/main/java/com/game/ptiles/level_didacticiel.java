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
import android.os.CountDownTimer;
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

public class level_didacticiel extends AppCompatActivity {


    /*
    Le didacticiel est un niveau particulié,
    les animations restent les mêmes mais les évènements qui arrivent lorsqu'on clique sur une note ne sont pas les mêmes. On ne peut pas perdre et  les notes ne donnent pas de points
    ...

     */

    TextView didact_text_main,text_long,text_short;
    Button didact_long1Img,didact_note4Img;
    final Boolean[] terminate = {false};





    Button startBtn;

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
    int[] didact_tab = {2,3,2,
            22,9999,9999,33,9999,9999,22,
            11,9999,2,33,9999,4,22,9999,1
    };











    //LONG VAR
    final double[] firstTouch1 = {0.},firstTouch2 = {0.},firstTouch3 = {0.},firstTouch4 = {0.};
    final boolean[] longHold1={false},longHold2={false},longHold3={false},longHold4={false};



    final boolean[] arret={false};

    Animation transition;
    Vibrator vibrator;


    //VERIFIER QUE LES NOTES SONT TOUTES CLIQUES, type = tableau à 1 element de boulean -> pour que les valeurs puisse être modifiés à l'interieur des fonctions.
    final Boolean[] note1cliqued={false},note2cliqued={false},note3cliqued={false},note4cliqued={false},note11cliqued={false},note22cliqued={false},note33cliqued={false},note44cliqued={false},
            note111cliqued={false},note222cliqued={false},note333cliqued={false},note444cliqued={false},long1cliqued={false},long2cliqued={false},long3cliqued={false},long4cliqued={false};


    String niveau;
    Animation fadeout,fadein;

    Boolean fail,pause=false;

    int length;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level); //On recupère quand meme le meme fichier xml que pour les niveaux


        didact_text_main = findViewById(R.id.didact_text_main);
        text_long = findViewById(R.id.textLong);
        text_short = findViewById(R.id.textShort);

        didact_long1Img = findViewById(R.id.didact_long1Img);
        didact_note4Img = findViewById(R.id.didact_note4Img);




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



        final boolean[] start = {false};


        //GET SCREEN SIZE

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int height = metrics.heightPixels; //LONGUEUR



        transition = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation150);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

        progress_bar.setVisibility(ProgressBar.GONE);
        score.setVisibility(TextView.GONE);
        didact_text_main.setVisibility(TextView.VISIBLE);



        fail = false;
        pause =false;




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



        Intent recup = getIntent();
        length = recup.getIntExtra("length_musique",0);
        musique = MediaPlayer.create(level_didacticiel.this, R.raw.musique_menu);
        musique.seekTo(length);
        musique.start();



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                explications();

            }
        });


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


        layoutFail.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if(terminate[0]){ //Pour quitter le didacticiel à la fin

                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();

        musique.pause();
        length = musique.getCurrentPosition();

    }

    @Override
    protected void onResume() {

        super.onResume();

        if (pause) { //Si pause est vrai on est bien dans le onResume

            musique.seekTo(length);
            musique.start();

        }
    }


    public void fall(int[] tab){

        if(!arret[0]) {


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



    void noteTouch(Button note,Button noteImg,Boolean[] clicked) {
        if (!clicked[0]) {
            int intscore = parseInt(score.getText().toString()) + 100;
            score.setText(String.valueOf(intscore));
            noteImg.setAlpha(0.3f);
            clicked[0] = true;
            note.setVisibility(Button.GONE);



            progress_bar.setProgress(intscore); //maj progress_bar
            vibrator.vibrate(50); //VIBRATE
        }
    }

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

        fail = true;

    }




    void addLongPoint(double seconds){
        double dbscore = parseInt(score.getText().toString())+(seconds/5);
        int intscore = (int) dbscore;
        score.setText(String.valueOf(intscore));
        progress_bar.setProgress(intscore); //Maj progress bar

    }

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





         La différence pour le level didacticiel étant qu'on affiche des texte à la place de faire tomber des notes


        */


    void explications(){


        startBtn.setVisibility(Button.GONE);

        didact_text_main.startAnimation(fadeout);
        didact_text_main.setVisibility(TextView.INVISIBLE);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {

            didact_text_main.setText("AnimeTiles est un jeu de rythme");
            didact_text_main.startAnimation(fadein);
            didact_text_main.setVisibility(TextView.VISIBLE);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {public void run() {

                didact_text_main.startAnimation(fadeout);
                didact_text_main.setVisibility(TextView.INVISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {public void run() {


                    didact_text_main.setText("des notes vont traverser l'écran de haut en bas au rythme de la musique");
                    didact_text_main.startAnimation(fadein);
                    didact_text_main.setVisibility(TextView.VISIBLE);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {public void run() {

                        didact_text_main.startAnimation(fadeout);
                        didact_text_main.setVisibility(TextView.INVISIBLE);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {public void run() {


                            didact_text_main.setText("Votre objectif est de terminer la musique en ayant touché toutes les notes");
                            didact_text_main.startAnimation(fadein);
                            didact_text_main.setVisibility(TextView.VISIBLE);

                            text_long.startAnimation(fadein);
                            text_long.setVisibility(TextView.VISIBLE);

                            text_short.startAnimation(fadein);
                            text_short.setVisibility(TextView.VISIBLE);

                            didact_long1Img.startAnimation(fadein);
                            didact_long1Img.setVisibility(Button.VISIBLE);

                            didact_note4Img.startAnimation(fadein);
                            didact_note4Img.setVisibility(Button.VISIBLE);


                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {public void run() {

                                didact_text_main.startAnimation(fadeout);
                                didact_text_main.setVisibility(TextView.INVISIBLE);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {public void run() {


                                    didact_text_main.setText("Si vous cliquez à coté ou laissez passer une note, la partie est perdue !");
                                    didact_text_main.startAnimation(fadein);
                                    didact_text_main.setVisibility(TextView.VISIBLE);

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {public void run() {

                                        didact_text_main.startAnimation(fadeout);
                                        didact_text_main.setVisibility(TextView.INVISIBLE);

                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {public void run() {


                                            didact_text_main.setText("à vous de jouer !");
                                            didact_text_main.startAnimation(fadein);
                                            didact_text_main.setVisibility(TextView.VISIBLE);

                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {public void run() {

                                                didact_text_main.startAnimation(fadeout);
                                                didact_text_main.setVisibility(TextView.INVISIBLE);

                                                didact_text_main.startAnimation(fadeout);
                                                didact_text_main.setVisibility(TextView.GONE);

                                                didact_long1Img.startAnimation(fadeout);
                                                didact_long1Img.setVisibility(Button.GONE);

                                                text_long.startAnimation(fadeout);
                                                text_long.setVisibility(TextView.GONE);

                                                text_short.startAnimation(fadeout);
                                                text_short.setVisibility(TextView.GONE);

                                                didact_note4Img.startAnimation(fadeout);
                                                didact_note4Img.setVisibility(Button.GONE);


                                                entrainement();


                                            }
                                            }, (long)(3000));



                                        }
                                        }, (long)(1000));

                                    }
                                    }, (long)(5000));


                                }
                                }, (long)(1000));

                            }
                            }, (long)(5000));


                        }
                        }, (long)(1000));

                    }
                    }, (long)(5000));




                }
                }, (long)(1000));

            }
            }, (long)(2000));


        }
        }, (long)(1000));

    }

    void entrainement(){


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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {

            didact_text_main.setText("voici quelques notes ...");
            didact_text_main.startAnimation(fadein);
            didact_text_main.setVisibility(TextView.VISIBLE);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {public void run() {

                didact_text_main.startAnimation(fadeout);
                didact_text_main.setVisibility(TextView.INVISIBLE);
                entrainement_notes1();

            }
            }, (long)(3000));

        }
        }, (long)(1000));

    }

    void entrainement_notes1(){
        fail=false;
        indice = 0;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {

            new CountDownTimer((long)(1100),(long)(400)) {

                @Override
                public void onTick(long millisUntilFinished) {
                    fall(didact_tab);
                }

                @Override
                public void onFinish() {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {public void run() {


                    if(fail){
                        didact_text_main.setText("Recommencez");
                        didact_text_main.startAnimation(fadein);
                        didact_text_main.setVisibility(TextView.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {public void run() {

                            didact_text_main.startAnimation(fadeout);
                            didact_text_main.setVisibility(TextView.INVISIBLE);
                            entrainement_notes1();

                        }
                        }, (long)(2000));
                    }
                    else{
                        didact_text_main.setText("Bravo !");
                        didact_text_main.startAnimation(fadein);
                        didact_text_main.setVisibility(TextView.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {public void run() {

                            didact_text_main.startAnimation(fadeout);
                            didact_text_main.setVisibility(TextView.INVISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {public void run() {


                                didact_text_main.setText("Maintenant, les notes longues : restez appuyé le plus longtemps possible pour gagner plus de points !");
                                didact_text_main.startAnimation(fadein);
                                didact_text_main.setVisibility(TextView.VISIBLE);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {public void run() {

                                    didact_text_main.startAnimation(fadeout);
                                    didact_text_main.setVisibility(TextView.INVISIBLE);
                                    entrainement_long();


                                }
                                }, (long)(5000));



                            }
                            }, (long)(1000));



                        }
                        }, (long)(2000));

                    }

                    }
                    }, (long)(1000));

                }

            }.start();

        }
        }, (long)(1000));
    }

    void entrainement_long(){
    fail=false;
    indice =3;
    Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {

        new CountDownTimer((long)(2700),(long)(400)) {

            @Override
            public void onTick(long millisUntilFinished) {
                fall(didact_tab);
            }

            @Override
            public void onFinish() {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {public void run() {


                    if(fail){
                        didact_text_main.setText("Recommencez");
                        didact_text_main.startAnimation(fadein);
                        didact_text_main.setVisibility(TextView.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {public void run() {

                            didact_text_main.startAnimation(fadeout);
                            didact_text_main.setVisibility(TextView.INVISIBLE);
                            entrainement_long();

                        }
                        }, (long)(2700));
                    }
                    else{

                        didact_text_main.setText("Bravo !");
                        didact_text_main.startAnimation(fadein);
                        didact_text_main.setVisibility(TextView.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {public void run() {

                            didact_text_main.startAnimation(fadeout);
                            didact_text_main.setVisibility(TextView.INVISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {public void run() {


                                didact_text_main.setText("Maintenant les deux ensemble");
                                didact_text_main.startAnimation(fadein);
                                didact_text_main.setVisibility(TextView.VISIBLE);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {public void run() {

                                    didact_text_main.startAnimation(fadeout);
                                    didact_text_main.setVisibility(TextView.INVISIBLE);
                                    entrainement_short_long();

                                }
                                }, (long)(5000));



                            }
                            }, (long)(1000));



                        }
                        }, (long)(2000));

                    }

                }
                }, (long)(2500));

            }

        }.start();

    }
    }, (long)(1000));
}

    void entrainement_short_long(){
        fail=false;
        indice =10;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {public void run() {

            new CountDownTimer((long)(3500),(long)(400)) {

                @Override
                public void onTick(long millisUntilFinished) {
                    fall(didact_tab);
                }

                @Override
                public void onFinish() {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {public void run() {


                        if(fail){
                            didact_text_main.setText("Recommencez");
                            didact_text_main.startAnimation(fadein);
                            didact_text_main.setVisibility(TextView.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {public void run() {

                                didact_text_main.startAnimation(fadeout);
                                didact_text_main.setVisibility(TextView.INVISIBLE);
                                entrainement_short_long();

                            }
                            }, (long)(2700));
                        }
                        else{

                            didact_text_main.setText("Bravo !");
                            didact_text_main.startAnimation(fadein);
                            didact_text_main.setVisibility(TextView.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {public void run() {

                                didact_text_main.startAnimation(fadeout);
                                didact_text_main.setVisibility(TextView.INVISIBLE);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {public void run() {


                                    terminate[0]=true;
                                    didact_text_main.setText("Vous avez fini le didacticiel\ncliquez pour continuer...");
                                    didact_text_main.startAnimation(fadein);
                                    didact_text_main.setVisibility(TextView.VISIBLE);



                                }
                                }, (long)(1000));



                            }
                            }, (long)(2000));

                        }

                    }
                    }, (long)(2500));

                }

            }.start();

        }
        }, (long)(1000));
    }




}


/*
didact_text_main.setText("des notes vont traverser l'écran de haut en bas");
                        didact_text_main.setVisibility(TextView.VISIBLE);


CREATION NIVEAU :

Handler handler = new Handler();
handler.postDelayed(new Runnable() {public void run() {

}
}, (long)(2000));




new CountDownTimer((long)(5000),(long)(5000)) {

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



