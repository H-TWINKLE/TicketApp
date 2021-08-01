package com.gy.ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.ticket.java.InitString;
import com.gy.ticket.java.JsonParse;
import com.gy.ticket.java.Scenery_JsonParse;
import com.gy.ticket.user.SceneryInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class SceneryActivity extends AppCompatActivity {
    private LinearLayout scenery_loading;
    private ListView scenerynews;
    private List<SceneryInfo> sceneryInfos;
    private TextView scenery_item_title;
    private TextView scenery_item_content;
    private TextView scenery_item_nature;
    private SceneryInfo sceneryInfo;
    private SmartImageView scenerysiv;
    private Toolbar tl_scenery_play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);
        initView();
        fillData();
    }
    private void initView(){
        scenery_loading = (LinearLayout)findViewById(R.id.scenery_loading);
        scenerynews = (ListView)findViewById(R.id.scenery_news);
        tl_scenery_play = (Toolbar)findViewById(R.id.tl_scenery_play);
        tl_scenery_play.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void fillData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(InitString.scenery,new AsyncHttpResponseHandler(){
            public void onSuccess(int i, org.apache.http.Header[] headers,byte[] bytes){
                try{
                    String json = new String(bytes, "utf-8");
                    json = "[ " +json + " ]";
                    sceneryInfos = Scenery_JsonParse.getSceneryInfo(json);
                    if(sceneryInfos==null){
                        Toast.makeText(SceneryActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    }else{
                        scenery_loading.setVisibility(View.INVISIBLE);
                        scenerynews.setAdapter(new NewsAdapter());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            public void onFailure(int i,org.apache.http.Header[] headers,
                                  byte[]bytes,Throwable throwable){
                Toast.makeText(SceneryActivity.this,"请求失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class NewsAdapter extends BaseAdapter{
        public int getCount(){

            return sceneryInfos.get(0).getSceneryList().size();
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View view = View.inflate(SceneryActivity.this,R.layout.activity_scenery_item,null);
            scenerysiv =(SmartImageView) view.findViewById(R.id.scenery_icon) ;
            scenery_item_title = (TextView) view.findViewById(R.id.scenery_item_title);
            scenery_item_content = (TextView) view.findViewById(R.id.scenery_item_content);
            scenery_item_nature = (TextView) view.findViewById(R.id.scenery_item_nature);

            Log.i("size",""+sceneryInfos.size());

            sceneryInfo = sceneryInfos.get(0);
            Log.i("size",""+sceneryInfo.getSceneryList().size());
            scenerysiv.setImageUrl(sceneryInfo.getSceneryList().get(position).getImgPath(),R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);
            scenery_item_title.setText(sceneryInfo.getSceneryList().get(position).getSceneryName());
            scenery_item_content.setText(sceneryInfo.getSceneryList().get(position).getSummary());
            scenery_item_nature.setText(sceneryInfo.getSceneryList().get(position).getCountryName());
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
