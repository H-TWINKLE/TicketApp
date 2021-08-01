package com.gy.ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.ticket.java.Film_JsonParse;
import com.gy.ticket.java.InitString;
import com.gy.ticket.user.FilmInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class FilmActivity extends AppCompatActivity {
    private LinearLayout film_loading;
    private ListView filmnews;
    private List<FilmInfo> filmInfo;
    private TextView film;
    private TextView film1;
    private TextView film2;
    private TextView film3;
    private Toolbar tb_film;

    private SmartImageView filmsiv,filmsiv1,filmsiv2,filmsiv3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        initView();
        fillData();
    }

    private void initView() {
        film_loading = (LinearLayout)findViewById(R.id.film_loading);
        filmnews = (ListView)findViewById(R.id.film_news);
        tb_film = (Toolbar)findViewById(R.id.tb_film);
        tb_film.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void fillData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(InitString.news_url,new AsyncHttpResponseHandler(){
            public void onSuccess(int i, org.apache.http.Header[] headers,byte[] bytes){
                try{
                    String json = new String(bytes, "utf-8");

                    filmInfo = Film_JsonParse.getFilmInfo(json);
                    if(filmInfo==null){
                        Toast.makeText(FilmActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    }else{
                        film_loading.setVisibility(View.INVISIBLE);
                        filmnews.setAdapter(new NewsAdapter());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            public void onFailure(int i,org.apache.http.Header[] headers,
                                  byte[]bytes,Throwable throwable){
                Toast.makeText(FilmActivity.this,"请求失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class NewsAdapter extends BaseAdapter {
        public int getCount(){

            return 1;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(FilmActivity.this, R.layout.activity_film_item, null);
            filmsiv = (SmartImageView) view.findViewById(R.id.film_icon);
            filmsiv1 = (SmartImageView) view.findViewById(R.id.film_icon1);
            filmsiv2 = (SmartImageView) view.findViewById(R.id.film_icon2);
            filmsiv3 = (SmartImageView) view.findViewById(R.id.film_icon3);

            film = (TextView) view.findViewById(R.id.film);
            film1 = (TextView) view.findViewById(R.id.film1);
            film2 = (TextView) view.findViewById(R.id.film2);
            film3 = (TextView) view.findViewById(R.id.film3);




            filmsiv.setImageUrl(filmInfo.get(position).getImg(),R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);
            filmsiv1.setImageUrl(filmInfo.get(position+1).getImg(),R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);
            filmsiv2.setImageUrl(filmInfo.get(position+2).getImg(),R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);
            filmsiv3.setImageUrl(filmInfo.get(position+3).getImg(),R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);

           /* filmsiv5.setImageUrl(filmInfo.getImg(),R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);*/
            film.setText(filmInfo.get(position).getName());
            film1.setText(filmInfo.get(position+1).getName());
            film2.setText(filmInfo.get(position+2).getName());
            film3.setText(filmInfo.get(position+3).getName());

          /*  film5.setText(filmInfo.getName());*/

            return view;
        }

        public Object getItem(int position){
            return null;
        }
        public long getItemId(int position){
            return 0;
        }
    }
}
