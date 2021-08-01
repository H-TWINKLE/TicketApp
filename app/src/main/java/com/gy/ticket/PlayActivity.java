package com.gy.ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.ticket.java.InitString;
import com.gy.ticket.java.Play_JsonParse;
import com.gy.ticket.user.PlayInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

public class PlayActivity extends AppCompatActivity {
    private LinearLayout play_loading;
    private ListView playnews;
    private List<PlayInfo> playInfos;
    private TextView play_item_title;
    private TextView play_item_content;
    private TextView play_item_nature;
    private PlayInfo playInfo;

    private Toolbar tb_play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
        fillData();
    }

    private void initView() {
        play_loading = (LinearLayout) findViewById(R.id.play_loading);
        playnews = (ListView) findViewById(R.id.play_news);
        tb_play = (Toolbar)findViewById(R.id.tb_play);
        tb_play.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fillData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(InitString.play, new AsyncHttpResponseHandler() {
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    json = "[ " + json + " ]";
                    playInfos = Play_JsonParse.getPlayInfo(json);
                    if (playInfos == null) {
                        Toast.makeText(PlayActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        play_loading.setVisibility(View.INVISIBLE);
                        playnews.setAdapter(new NewsAdapter());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int i, org.apache.http.Header[] headers,
                                  byte[] bytes, Throwable throwable) {
                Toast.makeText(PlayActivity.this, "请求失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class NewsAdapter extends BaseAdapter {

        public int getCount() {
            return playInfos.get(0).getSceneryPrices().get(0).getChannelPriceModelEntityList().size()-2;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(PlayActivity.this, R.layout.activity_play_item, null);
            play_item_title = (TextView) view.findViewById(R.id.play_item_title);
            play_item_content = (TextView) view.findViewById(R.id.play_item_content);
            play_item_nature = (TextView) view.findViewById(R.id.play_item_nature);
            playInfo = playInfos.get(0);
            play_item_title.setText(""+playInfo.getSceneryPrices().get(0).getChannelPriceModelEntityList().get(position).getChannelPriceEntityList().get(0).getTicketName());
            play_item_content.setText(""+playInfo.getSceneryPrices().get(0).getChannelPriceModelEntityList().get(position).getChannelPriceEntityList().get(0).getPriceTimeLimit());
            play_item_nature.setText(""+playInfo.getSceneryPrices().get(0).getChannelPriceModelEntityList().get(position).getChannelPriceEntityList().get(0).getConsumersTypeName());

            return view;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }
    }
}
