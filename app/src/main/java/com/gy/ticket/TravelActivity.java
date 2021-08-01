package com.gy.ticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class TravelActivity extends AppCompatActivity {

    private ImageView iv_travel_airplane,iv_travel_train,iv_travel_bus,iv_travel_boat;
    private Toolbar tb_travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        tb_travel = (Toolbar)findViewById(R.id.tb_travel);
        tb_travel.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView iv_travel_train = (ImageView)findViewById(R.id.iv_travel_train);
        iv_travel_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TravelActivity.this,DestineActivity.class);

                startActivity(i);

            }
        });


    }
}
