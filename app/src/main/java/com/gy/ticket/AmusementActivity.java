package com.gy.ticket;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AmusementActivity extends AppCompatActivity {

    private ImageView drama;
    private ImageView sight;
    private ImageView play;
    private ImageView film;
    private Toolbar tb_amuse;
    private ImageView iv_amusement_aquarium;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amusement);

        tb_amuse = (Toolbar)findViewById(R.id.tb_amuse);
        tb_amuse.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        drama = (ImageView)findViewById(R.id.iv_amusement_drama);
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmusementActivity.this,SingActivity.class);
                startActivity(intent);



            }
        });
        sight = (ImageView)findViewById(R.id.iv_amusement_sight);
        sight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmusementActivity.this,SceneryActivity.class);
                startActivity(intent);
            }
        });
         play = (ImageView)findViewById(R.id.iv_amusement_park);
         play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmusementActivity.this,PlayActivity.class);
                startActivity(intent);
            }
         });
        film = (ImageView)findViewById(R.id.iv_amusement_film);
        film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmusementActivity.this,FilmActivity.class);
                startActivity(intent);
            }
        });


        iv_amusement_aquarium = (ImageView)findViewById(R.id.iv_amusement_aquarium);
        iv_amusement_aquarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmusementActivity.this,AquariumActivity.class);
                startActivity(intent);
            }
        });

    }
}
