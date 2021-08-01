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

import com.gy.ticket.java.Aq_JsonParse;
import com.gy.ticket.java.InitString;
import com.gy.ticket.user.AqInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

public class AquariumActivity extends AppCompatActivity {

    private LinearLayout aq_loading;
    private ListView aqnews;
    private List<AqInfo> aqInfos;
    private TextView aq_item_title;
    private TextView aq_item_content;
    private TextView aq_item_nature;
    private AqInfo aqInfo;
    private Toolbar tb_aqua;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquarium);
        initView();
        fillData();
    }

    private void initView() {
        aq_loading = (LinearLayout) findViewById(R.id.aq_loading);
        aqnews = (ListView) findViewById(R.id.aq_news);
        tb_aqua = (Toolbar)findViewById(R.id.tb_aqua);
        tb_aqua.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fillData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(InitString.film, new AsyncHttpResponseHandler() {
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    json = "[ " + json + " ]";
                    aqInfos = Aq_JsonParse.getAqInfo(json);
                    if (aqInfos == null) {
                        Toast.makeText(AquariumActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        aq_loading.setVisibility(View.INVISIBLE);
                        aqnews.setAdapter(new NewsAdapter());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int i, org.apache.http.Header[] headers,
                                  byte[] bytes, Throwable throwable) {
                Toast.makeText(AquariumActivity.this, "请求失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class NewsAdapter extends BaseAdapter {
        int num;


        public NewsAdapter() {
           num = aqInfos.get(0).getSceneryPrices().get(0).
                    getChannelPriceModelEntityList().size()-2;

        }

        public int getCount() {
            return num;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(AquariumActivity.this, R.layout.activity_aquarium_item, null);

                aq_item_title = (TextView) view.findViewById(R.id.aq_item_title);
                aq_item_content = (TextView) view.findViewById(R.id.aq_item_content);
                aq_item_nature = (TextView) view.findViewById(R.id.aq_item_nature);
                aq_item_title.setText(""+aqInfos.get(0).getSceneryPrices().get(0).
                        getChannelPriceModelEntityList().get(position).getChannelPriceEntityList().get(0).getTicketName());
                aq_item_content.setText(""+aqInfos.get(0).getSceneryPrices().get(0).
                        getChannelPriceModelEntityList().get(position).getChannelPriceEntityList().get(0).getContainedItems());
                aq_item_nature.setText(""+aqInfos.get(0).getSceneryPrices().get(0).
                        getChannelPriceModelEntityList().get(position).getChannelPriceEntityList().get(0).getBeginDate());



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
