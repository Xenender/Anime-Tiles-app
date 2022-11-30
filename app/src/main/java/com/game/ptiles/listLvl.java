package com.game.ptiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class listLvl extends AppCompatActivity {
    //Variables globales
    ConstraintLayout paraLayout;
    Button param;
    ImageView para;
    Switch vibra;
    Switch repet;
    Button layout_para;



    //Variables utiles pour la liste qui contient les niveaux
    ListView lv;
    String[] difficulte = {"Tutoriel","Facile","Facile","Facile","Facile","Moyen","Moyen","Moyen","Moyen","Difficile"};
    String[] ListName = {"Didacticiel","A Cruel Angel's Thesis","Guren no yumiya","Red Swan","Tank!","Bye Bye yesterday", "Gurenge","Make my story","Merry Go Round", "Rumbling"};
    String[] nameHs={"","hs_evangelion","hs_snk1","hs_snk4","hs_cowboy","hs_ac","hs_demon_slayer1","hs_mha5","hs_mha9","hs_snk7"};
    int[] ListImage = {
            R.drawable.tuto_img,
            R.drawable.evangelion_img,
            R.drawable.snk1_img,
            R.drawable.snk4_img,
            R.drawable.cowboy_bebop_img,
            R.drawable.ac_img,
            R.drawable.demon_slayer1_img,
            R.drawable.mha5_img,
            R.drawable.mha9_img,
            R.drawable.snk7_img

    };

    int tailleLv = ListName.length;
    String[] hs = new String[tailleLv];

    Boolean vibraB,repetB;


    Boolean ouvert;
    Boolean musiquePlaying;
    MediaPlayer musique_menu,musique_transition;
    Boolean pause = false;
    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialisation variables
        setContentView(R.layout.activity_list_lvl);
        paraLayout = findViewById(R.id.paraLayout);
        param = findViewById(R.id.param);
        vibra = findViewById(R.id.vibra);
        repet=findViewById(R.id.repet);
        para = findViewById(R.id.para);

        layout_para=findViewById(R.id.layout_para);

        musiquePlaying = false;
        ouvert=false;
        pause = true;

        Animation AnimOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate45deg_left);
        Animation AnimClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate45deg_right);


        //Pour recuperer les meilleurs scores
        SharedPreferences sharedPreferences = getSharedPreferences("sharedVariables",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //remplissage de la liste des high scores
        for(int i = 0;i<tailleLv;i++){
            hs[i] = sharedPreferences.getString(nameHs[i],"");

        }


        //Sauvegarde des paramètres
        vibra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("vibraB",vibra.isChecked());
                editor.apply();
            }
        });
        repet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("repetB",repet.isChecked());
                editor.apply();
            }
        });

        //On récupère les parametres sauvegardés si ils existent
        vibraB =sharedPreferences.getBoolean("vibraB",true);
        repetB = sharedPreferences.getBoolean("repetB",false);

        vibra.setChecked(vibraB);
        repet.setChecked(repetB);


        //creation de l'adapter pour remplir la listview avec toutes les informations nécessaires
        lv = (ListView) findViewById(R.id.lv);
        MyAdapter myAdapter = new MyAdapter(listLvl.this, ListName,difficulte, ListImage,hs);
        lv.setAdapter(myAdapter);

        //lancement de la musique
        musique_transition = MediaPlayer.create(listLvl.this,R.raw.effet_transition);

        musique_menu = MediaPlayer.create(listLvl.this, R.raw.musique_menu);
        musique_menu.setLooping(true);
        if(!musiquePlaying) {
            musique_menu.start();
            musiquePlaying = true;
        }

        //bouton parametres
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                paraLayout.setVisibility(RelativeLayout.VISIBLE);
                layout_para.setVisibility(Button.VISIBLE);

                para.startAnimation(AnimOpen);

                ouvert=true;




            }

        });

        //Pour que ce layout soit reconnu comme par dessus le précédent on doit definir un on click listener meme si il est vide
        paraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        layout_para.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                paraLayout.setVisibility(RelativeLayout.GONE);
                layout_para.setVisibility(Button.GONE);
                para.startAnimation(AnimClose);
                ouvert=false;




            }

        });

        //recommencer la musique
        musique_menu.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (musiquePlaying) musique_menu.start();
            }
        });


        //Pour le lancement des niveaux
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(ouvert==false){

                    //On defini l'itent qui permettra de changer d'activité
                    Intent level = new Intent(listLvl.this, level.class);
                    level.putExtra("vibraB", vibra.isChecked());
                    level.putExtra("repetB", repet.isChecked());
                    musique_transition.start();
                    switch (ListName[i]) {

                        case "Didacticiel":
                            //Pour le didacticiel on se déplace vers sa propre activité

                            Intent didacticiel = new Intent(listLvl.this, level_didacticiel.class);

                            didacticiel.putExtra("length_musique",musique_menu.getCurrentPosition()+500);
                            startActivity(didacticiel);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                            break;
                        case "A Cruel Angel's Thesis":
                            //Pour les autres niveaux on se déplace vers "level" mais toujours avec des paramètres différents (putExtra())
                            level.putExtra("niveau", "evangelion");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            break;

                        case "Bye Bye yesterday":
                            level.putExtra("niveau", "ac");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            break;



                        case "Guren no yumiya":
                            level.putExtra("niveau", "snk1");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            break;

                        case "Gurenge":
                            level.putExtra("niveau", "demon_slayer1");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            break;

                        case "Make my story":

                            level.putExtra("niveau","mha5");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                            break;

                        case "Merry Go Round":
                            level.putExtra("niveau", "mha9");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            break;

                        case "Rumbling":
                            level.putExtra("niveau", "snk7");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                            break;

                        case "Tank!":

                            level.putExtra("niveau","cowboy");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                            break;

                        case "Red Swan":
                            level.putExtra("niveau", "snk4");

                            startActivity(level);
                            overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                            break;
                    }

                }
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();

        musique_menu.pause();
        length = musique_menu.getCurrentPosition();

    }

    @Override
    protected void onResume() {

        super.onResume();

        if (pause) { //Si pause est vrai on est bien dans le onResume

            musique_menu.seekTo(length);
            musique_menu.start();

            //Mise à jour des scores
            SharedPreferences sharedPreferences = getSharedPreferences("sharedVariables",MODE_PRIVATE);
            for(int i = 0;i<tailleLv;i++){
                hs[i] = sharedPreferences.getString(nameHs[i],"");  //remplissage de la liste des high scores

            }
            MyAdapter myAdapter = new MyAdapter(listLvl.this, ListName,difficulte, ListImage,hs);
            lv.setAdapter(myAdapter);

        }
    }
}