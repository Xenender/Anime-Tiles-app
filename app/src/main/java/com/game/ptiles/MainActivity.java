package com.game.ptiles;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Variables globales

    ImageView ac_main_img;
    Boolean musiquePlaying;
    MediaPlayer musique1,musique2,effetTransition;
    int length;
    int musique=-1;
    Boolean pause=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisation des variables

        pause=true;


        musique1 = MediaPlayer.create(MainActivity.this, R.raw.musique_attente1);
        musique2 = MediaPlayer.create(MainActivity.this, R.raw.musique_attente2);
        effetTransition = MediaPlayer.create(MainActivity.this, R.raw.effet_transition);

        ac_main_img = findViewById(R.id.ac_main_img);
        musiquePlaying=true;
        musique1.start();



        //Si on clique sur l'écran on passe directement à la prochaine activité
        ac_main_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                effetTransition.start();
                musiquePlaying=false;
                if(musique1.isPlaying()){musique1.pause();length=musique1.getCurrentPosition();musique=0;}
                else{musique2.pause();length=musique2.getCurrentPosition();musique=1;}



                Intent listLvl = new Intent(MainActivity.this, listLvl.class);
                startActivity(listLvl);
                finish();

                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        });

        //recommencer la musique
        musique1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(musiquePlaying) musique2.start();

            }
        });

        musique2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (musiquePlaying) musique1.start();
            }
        });




    }

    //On sauvegarde là où on en était pour recommencer la musique au meme endroit lorsque l'on reviens sur le menu
    @Override
    protected void onPause() {
        super.onPause();
        if(musique1.isPlaying()){
            musique1.pause();
            length = musique1.getCurrentPosition();
            musique =0;
        }
        else{
            musique2.pause();
            length = musique2.getCurrentPosition();
            musique =1;
        }
    }


    @Override
    protected void onResume() {

        super.onResume();

        if(pause) { //Si pause=true on est dans le onCreate et pas onResume
            if (musique == 0) {

                musique1.seekTo(length);
                musique1.start();

            } else if(musique==1) {

                musique2.seekTo(length);
                musique2.start();
            }
            else{

                musique1.seekTo(length);
                musique1.start();

            }
        }
    }



}