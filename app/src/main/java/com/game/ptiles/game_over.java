package com.game.ptiles;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class game_over extends AppCompatActivity {

    //variables globales
    RelativeLayout over_Layout_score;
    ConstraintLayout over_Layout;
    CircleImageView over_circle_image;
    ImageView over_img_menu,over_img_rejouer;
    TextView over_score,over_new_best;
    ProgressBar over_Progress_bar;
    String best;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //Initialisation des variables

        over_circle_image = findViewById(R.id.over_circle_image);

        over_Layout = findViewById(R.id.over_Layout);
        over_Layout_score = findViewById(R.id.over_Layout_score);
        over_Progress_bar = findViewById(R.id.over_Progress_bar);
        over_img_menu = findViewById(R.id.over_img_menu);
        over_img_rejouer = findViewById(R.id.over_img_rejouer);
        over_new_best = findViewById(R.id.over_new_best);

        over_score = findViewById(R.id.over_score);


        //RECUPERATION INTENT (recupération des information qui ont été envoyés de la dernière activité vers celle-là)

        Intent i = getIntent();
        String score,niveau;
        int progress;
        Boolean vibraB,repetB;
        score = i.getStringExtra("score");
        niveau = i.getStringExtra("niveau");
        progress = i.getIntExtra("progress",0);
        vibraB = i.getBooleanExtra("vibraB",true);
        repetB = i.getBooleanExtra("repetB",false);

        over_score.setText(score);

        //Initialisation des "Shared Preferences"
        SharedPreferences sharedPreferences = getSharedPreferences("sharedVariables",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Un cas pour chaque niveaux
        switch (niveau){
            case "snk1":
                over_circle_image.setImageResource(R.drawable.snk1_img);
                MediaPlayer musique1= MediaPlayer.create(game_over.this,R.raw.snk1);
                over_Progress_bar.setMax(musique1.getDuration());

                best = sharedPreferences.getString("hs_snk1","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_snk1",score);
                    editor.apply();
                }

                break;

            case "demon_slayer1":
                over_circle_image.setImageResource(R.drawable.demon_slayer1_img);
                MediaPlayer musique2= MediaPlayer.create(game_over.this,R.raw.demon_slayer1);
                over_Progress_bar.setMax(musique2.getDuration());
                //recupération du score maximal qui est sauvegardé dans les sharedPreferences
                best = sharedPreferences.getString("hs_demon_slayer1","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_demon_slayer1",score); //Le nouveau meilleur score est sauvegardé
                    editor.apply();
                }

                break;

            case "snk7":
                over_circle_image.setImageResource(R.drawable.snk7_img);
                MediaPlayer musique3= MediaPlayer.create(game_over.this,R.raw.op7);
                over_Progress_bar.setMax(musique3.getDuration());

                best = sharedPreferences.getString("hs_snk7","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_snk7",score);
                    editor.apply();
                }

                break;
            case "cowboy":
                over_circle_image.setImageResource(R.drawable.cowboy_bebop_img);
                MediaPlayer musique4= MediaPlayer.create(game_over.this,R.raw.cowboy);
                over_Progress_bar.setMax(musique4.getDuration());

                best = sharedPreferences.getString("hs_cowboy","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_cowboy",score);
                    editor.apply();
                }
                break;

            case "mha9":
                over_circle_image.setImageResource(R.drawable.mha9_img);
                MediaPlayer musique5= MediaPlayer.create(game_over.this,R.raw.mha9);
                over_Progress_bar.setMax(musique5.getDuration());

                best = sharedPreferences.getString("hs_mha9","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_mha9",score);
                    editor.apply();
                }
                break;

            case "evangelion":
                over_circle_image.setImageResource(R.drawable.evangelion_img);
                MediaPlayer musique6= MediaPlayer.create(game_over.this,R.raw.evangelion);
                over_Progress_bar.setMax(musique6.getDuration());
                best = sharedPreferences.getString("hs_evangelion","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_evangelion",score);
                    editor.apply();
                }
                break;


            case "ac":
                over_circle_image.setImageResource(R.drawable.ac_img);
                MediaPlayer musique7= MediaPlayer.create(game_over.this,R.raw.ac4);
                over_Progress_bar.setMax(musique7.getDuration());

                best = sharedPreferences.getString("hs_ac","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_ac",score);
                    editor.apply();
                }

                break;

            case "snk4":
                over_circle_image.setImageResource(R.drawable.snk4_img);
                MediaPlayer musique8= MediaPlayer.create(game_over.this,R.raw.snk4);
                over_Progress_bar.setMax(musique8.getDuration());
                best = sharedPreferences.getString("hs_snk4","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_snk4",score);
                    editor.apply();
                }
                break;

            case "mha5":
                over_circle_image.setImageResource(R.drawable.mha5_img);
                MediaPlayer musique9= MediaPlayer.create(game_over.this,R.raw.mha5);
                over_Progress_bar.setMax(musique9.getDuration());
                best = sharedPreferences.getString("hs_mha5","0"); //recuperation de l'ancien meilleur score : 0 si il existe pas

                if(parseInt(score) > parseInt(best)){
                    over_new_best.setVisibility(TextView.VISIBLE);
                    editor.putString("hs_mha5",score);
                    editor.apply();
                }
                break;


        }
        over_Progress_bar.setProgress(progress);


        over_img_rejouer.setOnClickListener(new View.OnClickListener() { //Bouton rejouer
            @Override
            public void onClick(View view) {
                Intent j = new Intent(game_over.this, level.class);
                j.putExtra("niveau",niveau);
                j.putExtra("vibraB",vibraB);
                j.putExtra("repetB",repetB);
                startActivity(j);
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        over_img_menu.setOnClickListener(new View.OnClickListener() { //Bouton menu
            @Override
            public void onClick(View view) {
                //On ferme simplement l'activité pour revenir à la précédente
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });



    }

}